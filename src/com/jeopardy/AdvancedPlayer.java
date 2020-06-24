package com.jeopardy;

public class AdvancedPlayer extends Player {

  public AdvancedPlayer(String name) {
    super(name);
  }

  public AdvancedPlayer(String name, Expertise level) {
    this(name);
    setLevel(level);
  }

  // business methods
  //implement interface abstract method
  @Override
  public int deductScore(int currentQuestionValue) {
    int score = getScore();
    score -= currentQuestionValue;
    setScore(score);
    return score;
  }

  @Override
  public int askForHelp() {
    int result = 0;
    System.out.println("Skip this question and no value added or subtracted!");
    /*
    Skip this question for advancedPlayer
    no points add or deducted
     */
    return result;
  }

  @Override
  public String toString() {
    return super.toString();
  }


}

