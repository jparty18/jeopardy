package com.jeopardy;

public class AdvancedPlayer extends Player {

  public AdvancedPlayer(String name) {
    super(name);
  }

  // business methods
  @Override
  public int deductScore(int currentQuestionValue) {
    int score = getScore();
    score -= currentQuestionValue;
    setScore(score);
    return score;
  }

  @Override  //implement interface abstract method
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

