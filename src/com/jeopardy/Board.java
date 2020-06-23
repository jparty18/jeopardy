package com.jeopardy;

import com.jeopardy.sample.Contestants;
import com.jeopardy.sample.Questions;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class Board {
  private int numberOfPlayers = 0;
  private List<Player> contestants;
  private List<Question> questions;

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

  Board(int session, int numberOfPlayers, int difficulty) {
    // TODO: pass difficulty to Player ctor
    Expertise expertise = difficulty == 1 ? Expertise.ROOKIE : Expertise.ADVANCED;

    setContestants(Contestants.getContestants(numberOfPlayers));
    setNumberOfPlayers(numberOfPlayers);

    setQuestions(Questions.getQuestions(session));
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

  // Business Methods
  // Select a $ value => print available questions
  // user selects a question => print the question and wait
}

