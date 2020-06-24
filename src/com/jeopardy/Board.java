package com.jeopardy;

import com.jeopardy.sample.Contestants;
import com.jeopardy.sample.Questions;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.sql.Array;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
  private int numberOfPlayers = 0;
  private int currentAnswerIndex;
  private List<Player> contestants;
  private List<Question> questions = new ArrayList<>();
  private List<String> answers = new ArrayList<>();

  public int getNumberOfPlayers() { return numberOfPlayers; }
  private void setNumberOfPlayers(int numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
  }

  public List<Player> getContestants() { return contestants; }
  private void setContestants(List<Player> contestants) {
    this.contestants = contestants;
  }

  public List<Question> getQuestions() { return questions; }
  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public List<String> getAnswers() { return answers; }

  public void setAnswers(List<Question> questions) {
    for (Question q : questions) {
      answers.add(q.getAnswer());
    }
  }

  Board(int session, int numberOfPlayers, int difficulty) {
    // DONE: read from csv file using nio api
    try{
      List<Player> contestants = new ArrayList<>();
      Stream<String> namesStream = Files.lines(FileSystems.getDefault().getPath("src","com","jeopardy", "Players.csv"));
      namesStream.forEach(line -> {
        String[] namesArr = line.split(",");
        Expertise expertise = difficulty == 1 ? Expertise.ROOKIE : Expertise.ADVANCED;

        // DONE: pass difficulty to Player ctor
        for (String name : namesArr) {
          if (difficulty == 1) {
            contestants.add(new RookiePlayer(name, expertise));
          } else {
            contestants.add(new AdvancedPlayer(name, expertise));
          }
        }
      });

      // Randomly select contestants from local contestants list
      setContestants(selectContestants(contestants, numberOfPlayers));
      setNumberOfPlayers(numberOfPlayers);


      namesStream.close();

      // set questions and answers
      List<String[]> questions = new ArrayList<>();
      Stream<String> questionsStream = Files.lines(FileSystems.getDefault().getPath("src","com","jeopardy", "Questions.csv"));
      questionsStream.forEach(line -> {
        String[] question = line.split(",");
        questions.add(question);
      });
      questionsStream.close();

      // TODO: refactor to subclasses: TFQuestion & MCQuestion

      for (String[] question : questions) {
        Question temp;
        int category = Integer.parseInt(question[0]);
        String body = question[1];
        int dollarValue = Integer.parseInt(question[2]);
        if (question[3].equals("true") || question[3].equals("false")) {
          boolean answer = Boolean.parseBoolean(question[3]);
          temp = new Question(category, body, dollarValue, answer);
        } else {
          String answer = question[3];
          temp = new Question(category, body, dollarValue, answer);
        }
        if (session == category) {
          this.questions.add(temp);
        }
      }
      setAnswers(getQuestions());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getPlayerName() {
    return contestants.get(new Random().nextInt(getNumberOfPlayers() - 1)).getName();
  }

  public String getAllPlayers() {
    StringBuilder names = new StringBuilder();
    for (Player player : getContestants()) {
      names.append(player.getName() + "\t" + "\t");
    }
    return names.toString();
  }

  public Question getQuestion(int dollarValue) {
    Question result = null;
    boolean set = false;
    for (int i = 0; i < questions.size(); i++) {
      if (questions.get(i).getDollarValue() == dollarValue && !set) {
        result = questions.get(i);
        set = !set;
        questions.remove(i);
      }
    }

    return result;
  }

  public String getAllQuestion() {
    StringBuilder result = new StringBuilder();
    int count = 1;
    for (Question q : questions) {
      result.append(q.getDollarValue() + "\t");
      count ++;
    }
    return result.toString();
  }

  public void processScore(boolean isCorrect, String playerName, int dollarValue){
    //get contestant by name, loop through contestants list
    Player currentPlayer = null;
    for(Player player: getContestants()){
      if(player.getName().equals(playerName)){
        currentPlayer = player;
        break;
      }
    }

    //check isCorrect, increase for correct, decrease for incorrect
    if(isCorrect){
      currentPlayer.addScore(dollarValue);
    }
    else{
      currentPlayer.deductScore(dollarValue);
    }
    System.out.println(currentPlayer.getName() + " won $" + currentPlayer.getScore());
  }


  public void displayScores(){
    StringBuilder scores = new StringBuilder("The scores are: ");

    // append player names and scores
    for(Player player: getContestants()){
      scores.append(" | " + player.getName()+ " : " + player.getScore() + " | ");
    }
    System.out.println(scores);

  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void showAnswerChoices(Question currentQuestion) {
    List<String> choices = new ArrayList<>();
    String answer = currentQuestion.getAnswer();

    int index = new Random().nextInt(3);
    int count = 1;
    for (String a : answers) {
      System.out.print(count + ": " + a + "\t" + "\t");
      if (a.equals(answer)) {
        currentAnswerIndex = count;
      }
      count ++;
    }
    System.out.print("\n" + "Your answer: ");
  }

  public boolean checkAnswer(int answer) {
    boolean result = answer == currentAnswerIndex;
    System.out.print(result ? "Correct!" : "Hmm... I don't think so.");
    return result;
  }

  private List<Player> selectContestants(List<Player> pool, int numberOfPlayers) {
    List<Player> results = new ArrayList<>();

    while(results.size() < numberOfPlayers) {
      int index = new Random().nextInt(pool.size());
      //Done: Check if there is any duplicates
      results.add(pool.get(index));
      pool.remove(index);
    }

    return results;
  }


}

