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
    int result = 0;
    System.out.println("Help has been called and no value added or subtracted!");
    this.setNeedHelp();
    return result;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
