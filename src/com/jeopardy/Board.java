package com.jeopardy;

import java.util.*;

import java.util.stream.Stream;

public class Board {
  public static final int HELP_INPUT = 0;

  private int numberOfPlayers = 0;
  private Mode mode;
  private List<Player> contestants = new ArrayList<>();
  private List<Question> questions = new ArrayList<>();


  public Board(int session, int numberOfPlayers, int difficulty) {
    // DONE: read from csv file using nio api
    Stream<String> namesStream = Util.TEXT_READER("Players.csv");
    setNumberOfPlayers(numberOfPlayers);
    setMode(difficulty);
    setContestants(namesStream);

    // set questions and answers
    Stream<String> questionsStream = Util.TEXT_READER("Questions.csv");
    setQuestions(questionsStream, session);
  }

  // Accessor methods
  public int getNumberOfPlayers() { return numberOfPlayers; }
  private void setNumberOfPlayers(int numberOfPlayers) {
    this.numberOfPlayers = numberOfPlayers;
  }

  public Mode getMode() { return mode; }
  public void setMode(int userInput) {
    Mode mode;
    if (userInput == 1) {
      mode = Mode.EASY;
    } else {
      mode = Mode.HARD;
    }
    this.mode = mode;
  }


  public List<Player> getContestants() { return contestants; }
  private void setContestants(Stream<String> playerStream) {
    List<Player> result = new ArrayList<>();
    playerStream.forEach(line -> {
      String[] namesArr = line.split(",");

      // DONE: pass difficulty to Player ctor
      for (String name : namesArr) {
        if (getMode() == Mode.EASY) {
          result.add(new RookiePlayer(name));
        } else {
          result.add(new AdvancedPlayer(name));
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

    // DONE: refactor to subclasses: TFQuestion & MCQuestion
    for (String[] question : result) {
      int category = Integer.parseInt(question[0]);
      if (session == category) {
        Question temp;

        String body = question[1];
        int dollarValue = Integer.parseInt(question[2]);
        if ("true".equals(question[3]) || "false".equals(question[3])) {
          boolean answer = Boolean.parseBoolean(question[3]);
          temp = new TFQuestion(category, body, dollarValue, answer);
        } else {
          String answer = question[3];
          List<String> answers = new ArrayList<>();
          answers.add(question[4]);
          answers.add(question[5]);
          answers.add(question[6]);
          temp = new MCQuestion(category, body, dollarValue, answer, answers);
        }
        questions.add(temp);
      }
    }
  }

  // Business methods
  public void start() {
    intro();
    while (getQuestions().size() > 0) {
      Util.CLEAR_SCREEN();
      displayScores();
      Player currentPlayer = getPlayer();
      String currentPlayerName = currentPlayer.getName();
      System.out.println("\n"+ "Our guest is: " + currentPlayerName);

      boolean correctDollarValueSelected = false;
      while(!correctDollarValueSelected) {
        try {
          System.out.println(currentPlayerName + ", please choose a question.");
          System.out.println(getAllQuestion());
          int dollarValue = Util.INPUT_HANDLER("Choose a dollar value: $");
          Question currentQuestion = getQuestion(dollarValue);
          currentQuestion.displayQuestion();

          // DONE: display answer choices
          // 1: correct answer 2: tricky answer 3: bs
          int answer = Util.INPUT_HANDLER(currentQuestion.showAnswerChoices());

          // DONE: process score for the player

          if (answer == HELP_INPUT) {
            callHelp(currentPlayer, currentQuestion);
          } else {
            dollarValue = currentQuestion.isDailyDouble() ? dollarValue * 2 : dollarValue;
            processScore(currentQuestion.checkAnswer(answer), currentPlayer, dollarValue);
          }
          correctDollarValueSelected = true;
          System.out.println("\n" + "Press enter to continue...");
          Scanner wait = new Scanner(System.in);
          wait.nextLine();
        } catch (NullPointerException e) {
          System.out.println("Invalid input. Please choose one of dollar values displayed above");
        }
      }
      // DONE: display scores

    }
    // DONE: display final score
    displayFinalScores();

    System.out.println("Thank you for playing. See you next time!");
  }

  private void intro(){
    Stream<String> banner;
    banner = getMode() == Mode.EASY ? Util.TEXT_READER("banner_easy.txt") : Util.TEXT_READER("banner_hard.txt");
    slowMo(banner);

    System.out.println("\n" + "Tonight's contestants are: ");
    slowMo(getContestants().stream().map(p -> p.getName()));

    System.out.println("Press enter to begin");
    Scanner wait = new Scanner(System.in);
    wait.nextLine();
  }

  private void slowMo(Stream<String> textStream) {
    System.out.println("\n");
    textStream.forEach(line -> {
      System.out.println(line);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.println("\n");
  }

  private void slowMo(List<String> textList) {
    System.out.println("\n");
    textList.forEach(line -> {
      System.out.println(line);
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.println("\n");
  }

  private Player getPlayer() {
    int numberOfPlayers = 1;
    if (getNumberOfPlayers() > 1) {
      numberOfPlayers = getNumberOfPlayers() - 1;
    }
    return contestants.get(new Random().nextInt(numberOfPlayers));
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

  private void processScore(boolean isCorrect, Player currentPlayer, int dollarValue){
   //check isCorrect, increase for correct, decrease for incorrect
    if(isCorrect) {
      currentPlayer.addScore(dollarValue);
      System.out.println(currentPlayer.getName() + " won $" + dollarValue);
    } else {
      currentPlayer.deductScore(dollarValue);
      System.out.println(currentPlayer.getName() + " lost $" + dollarValue);
    }
  }

  private void callHelp(Player currentPlayer, Question currentQuestion) {
    System.out.println("\n" + "Jay says: The answer is " + currentQuestion.getAnswer());
    processScore(false, currentPlayer, currentPlayer.askForHelp(currentQuestion.getDollarValue()));
    if (currentQuestion.isDailyDouble()) {
      System.out.println("You missed a daily double chance... :(");
    }
  }


  private void displayScores(){
    StringBuilder scores = new StringBuilder("Current Scores: ");

    // append player names and scores
    for(Player player: getContestants()){
      scores.append(" | " + player.getName()+ " : " + player.getScore() + " | ");
    }
    System.out.println(scores);
  }

  private void displayFinalScores() {
    Util.CLEAR_SCREEN();
    Stream<String> finalScore = Util.TEXT_READER("final.txt");
    slowMo(finalScore);
    List<String> finalResults = new ArrayList<>();
    getContestants().stream()
            .sorted(Comparator.comparing(Player::getScore).reversed())
            .forEach(p -> {
              finalResults.add(p.getName() +": " + p.getScore());
            });
    slowMo(finalResults);
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Board board = (Board) obj;
    return getNumberOfPlayers() == board.getNumberOfPlayers() &&
            getMode() == board.getMode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNumberOfPlayers(), getMode(), getContestants(), getQuestions());
  }
}

