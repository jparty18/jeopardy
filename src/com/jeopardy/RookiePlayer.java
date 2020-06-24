package com.jeopardy;

public class RookiePlayer extends Player {

  public RookiePlayer(String name) {
    super(name);
  }

  @Override//implement interface abstract method
  public int askForHelp(int currentQuestionValue) {
    int score = 0;
    score += currentQuestionValue;
    System.out.println("Help has been called and you got free money $" + currentQuestionValue + "!");
    return score;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
