package com.jeopardy;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;

import java.util.stream.Stream;

public class Board {
  private int numberOfPlayers = 0;
  private int currentAnswerIndex;
  private List<Player> contestants = new ArrayList<>();
  private List<Question> questions = new ArrayList<>();
  private List<String> answers = new ArrayList<>();

  public Board(int session, int numberOfPlayers, int difficulty) {
    // DONE: read from csv file using nio api
    Stream<String> namesStream = csvReader("Players.csv");
    setNumberOfPlayers(numberOfPlayers);
    setContestants(namesStream, difficulty);

    // set questions and answers
    Stream<String> questionsStream = csvReader("Questions.csv");
    setQuestions(questionsStream, session);
    setAnswers(getQuestions());
  }

  // Accessor methods
  public int getNumberOfPlayers() { return numberOfPlayers; }
  private void setNumberOfPlayers(int numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
  }

  public List<Player> getContestants() { return contestants; }
  private void setContestants(Stream<String> playerStream, int difficulty) {
    List<Player> result = new ArrayList<>();
    playerStream.forEach(line -> {
      String[] namesArr = line.split(",");
      Expertise expertise = difficulty == 1 ? Expertise.ROOKIE : Expertise.ADVANCED;

      // DONE: pass difficulty to Player ctor
      for (String name : namesArr) {
        if (difficulty == 1) {
          result.add(new RookiePlayer(name, expertise));
        } else {
          result.add(new AdvancedPlayer(name, expertise));
        }
      }
    });

    // Randomly select contestants from local contestants list
    contestants = selectContestants(result, getNumberOfPlayers());
  }

  public List<Question> getQuestions() { return questions; }
  private void setQuestions(Stream<String> questionStream, int session) {
    List<String[]> result = new ArrayList<>();

    questionStream.forEach(line -> {
      String[] question = line.split(",");
      result.add(question);
    });

    // TODO: refactor to subclasses: TFQuestion & MCQuestion
    for (String[] question : result) {
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
        questions.add(temp);
      }
    }
  }

  public List<String> getAnswers() { return answers; }
  private void setAnswers(List<Question> questions) {
    for (Question q : questions) {
      answers.add(q.getAnswer());
    }
  }

  // Business methods
  public void start() {
    intro();
    while (getQuestions().size() > 0) {
      String currentPlayer = getPlayerName();
      System.out.println("\n"+ "Our guest is: " + currentPlayer);
      System.out.println(currentPlayer + ", please choose a question.");

      System.out.println(getAllQuestion());
      System.out.print("Choose a dollar value: $");
      int dollarValue = getUserUInput();
      Question currentQuestion = getQuestion(dollarValue);
      currentQuestion.displayQuestion();

      // DONE: display answer choices
      showAnswerChoices(currentQuestion);
      // 1: correct answer 2: tricky answer 3: bs
      int answer = getUserUInput();

      // DONE: process score for the player
      dollarValue = currentQuestion.isDailyDouble() ? dollarValue * 2 : dollarValue;
      processScore(checkAnswer(answer), currentPlayer, dollarValue);


      // DONE: display scores
      displayScores();
    }
    // DONE: display final score
    displayFinalScores();

    // TODO: option to replay or exit
    System.out.println("Thank you for playing. See you next time!");
  }

  private void intro(){
    System.out.println("\nWelcome to the J-PARTY!");

    StringBuilder intro = new StringBuilder("Tonight's contestants are: " + "\n");
    intro.append(getAllPlayers());
    System.out.println(intro);

    System.out.println("Press enter to begin");
    Scanner wait = new Scanner(System.in);
    wait.nextLine();
  }

  private int getUserUInput() {
    Scanner wait = new Scanner(System.in);
    int userInput = wait.nextInt();
    return userInput;
  }

  private String getPlayerName() {
    return contestants.get(new Random().nextInt(getNumberOfPlayers() - 1)).getName();
  }

  private String getAllPlayers() {
    StringBuilder names = new StringBuilder();
    for (Player player : getContestants()) {
      names.append(player.getName() + "\t" + "\t");
    }
    return names.toString();
  }

  private Question getQuestion(int dollarValue) {
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

  private String getAllQuestion() {
    StringBuilder result = new StringBuilder();
    int count = 1;
    for (Question q : questions) {
      result.append(q.getDollarValue() + "\t");
      count ++;
    }
    return result.toString();
  }

  private void processScore(boolean isCorrect, String playerName, int dollarValue){
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
      System.out.println(currentPlayer.getName() + " won $" + currentPlayer.getScore());
    }
    else{
      currentPlayer.deductScore(dollarValue);
    }
  }


  private void displayScores(){
    StringBuilder scores = new StringBuilder("The scores are: ");

    // append player names and scores
    for(Player player: getContestants()){
      scores.append(" | " + player.getName()+ " : " + player.getScore() + " | ");
    }
    System.out.println(scores);
  }

  private void displayFinalScores() {
    for (int i = 0; i < 50; i++) {
      System.out.println("\n");
    }
    System.out.println("\n" + "\u001B[44m \n \u001B[41m \u001B[30m" +
            "*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*  F-I-N-A-L   S-C-O-R-E  *$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*" +
            "\n\u001B[44m" + "\n\u001B[0m" );
    getContestants().stream()
            .sorted(Comparator.comparing(Player::getScore).reversed())
            .forEach(p -> {
              System.out.println(p.getName() +": " + p.getScore());
            });
  }

  private void showAnswerChoices(Question currentQuestion) {
    String answer = currentQuestion.getAnswer();

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

  private boolean checkAnswer(int answer) {
    boolean result = answer == currentAnswerIndex;
    System.out.print(result ? "Correct! " : "Hmm... I don't think so. ");
    System.out.println("\n");
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

  private Stream<String> csvReader (String fileName) {
    Stream<String> result = null;
    try {
      result = Files.lines(Paths.get("sample",fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }
}

