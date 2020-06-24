package com.jeopardy;

public class RookiePlayer extends Player {

  public RookiePlayer(String name) {
    super(name);
  }

  //business method
  @Override
  public int deductScore(int currentQuestionValue) {
    int score = 0;
    return score;
  }

  @Override //implement interface abstract method
  public int askForHelp(int currentQuestionValue) {
    int score = 0;
    score += currentQuestionValue;
    System.out.println("Help has been called and value of this question automatically added!");
    this.setNeedHelp();
    return score;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
