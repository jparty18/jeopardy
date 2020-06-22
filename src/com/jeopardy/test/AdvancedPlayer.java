package com.jeopardy.test;

public class AdvancedPlayer extends Player {

  public AdvancedPlayer(String name, Expertise level) {
    super(name, level);
  }

  // business methods
  // int addPoints(int points);
  // setScore(getScore() + points);
  // return getScore();
  // int deductPoints(int points);
  // int finalScore() -> getScore();


  @Override//implement superClass abstract method
  public int calculateFinalScore() {
    int finalScore = 0;
    /*
    calculate final score for each advancedPlayer
    correct questions: add points
    incorrect questions: deduct points
     */
    return finalScore;
  }

  @Override//implement interface abstract method
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

