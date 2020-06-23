package com.jeopardy;

import com.jeopardy.sample.Contestants;
import com.jeopardy.sample.Questions;

import java.sql.Array;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class Board {
  private int numberOfPlayers = 0;
  private int currentAnswerindex;
  private List<Player> contestants;
  private List<Question> questions;
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
      if (count % 3 == 0) {
        result.append("\n");
      }
      result.append(q.getDollarValue() + "\t");
      count ++;
    }
    return result.toString();
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

