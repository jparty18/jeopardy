package com.jeopardy;

public class AdvancedPlayer extends Player {

  public AdvancedPlayer(String name) {
    super(name);
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
  public int askForHelp(int currentQuestionValue) {
    int result = 0;
    System.out.println("Help has been called and you are getting nothing for this one!");
    return result;
  }

  @Override
  public String toString() {
    return super.toString();
  }


}

