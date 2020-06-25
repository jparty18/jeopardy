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
    double score = 0.1 * currentQuestionValue;
    System.out.println("Help has been called and 10% of the current question value deducted!");
    this.setNeedHelp();
    return (int) score;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}

