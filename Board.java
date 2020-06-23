package com.jeopardy;

import com.jeopardy.sample.Contestants;
import com.jeopardy.sample.Questions;

import java.io.IOException;
import java.sql.Array;
import java.util.*;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Board {
  private int numberOfPlayers = 0;
  private int currentAnswerindex;
  private List<Player> contestants;
  private List<Question> questions;
  private List<String> answers = new ArrayList<>();
  private boolean helpIsCalled;

  public boolean isHelpIsCalled() {
    return helpIsCalled;
  }
  public void setHelpIsCalled() {
   try {
     Scanner input = new Scanner(System.in);
     if (input.next() == "y")
       helpIsCalled = true;
   } catch (InputMismatchException e) {
     System.out.println("Invalid input for help");
   }
  }

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
    // TODO: pass difficulty to Player ctor
    Expertise expertise = difficulty == 1 ? Expertise.ROOKIE : Expertise.ADVANCED;

    setContestants(Contestants.getContestants(numberOfPlayers));
    setNumberOfPlayers(numberOfPlayers);

    // set questions and answers
    List<Question> questions = Questions.getQuestions(session);
    setQuestions(questions);
    setAnswers(questions);
  }

  public String getAPlayerName() {
    return contestants.get(new Random().nextInt(getNumberOfPlayers() - 1)).getName();
  }

  public String getAllPlayers() {
    StringBuilder names = new StringBuilder();
    for (Player player : getContestants()) {
      names.append(player.getName() + "\t" + "\t");
    }
    return names.toString();
  }

  public Question getAQuestion(int dollarValue) {
    Question result = null;
    boolean set = false;
    for (int i = 0; i < questions.size(); i++) {
      if (questions.get(i).getDollarValue() == dollarValue && !set) {
        if (helpIsCalled) {
          continue;
        } else {
          result = questions.get(i);
          set = !set;
          questions.remove(i);
        }
      }
    }
    return result;
  }

  public String getAllQuestion() {
    StringBuilder result = new StringBuilder();
    int count = 1;
    for (Question q : questions) {
      if (count % 3 == 0) {
        result.append("\n");
      }
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
    System.out.println(currentPlayer.getScore());
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
        currentAnswerindex = count;
      }
      count ++;
    }
    System.out.print("\n" + "Your answer: ");
  }
}

