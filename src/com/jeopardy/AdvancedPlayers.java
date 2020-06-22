package com.jeopardy;

public class AdvancedPlayers extends Players {

  public AdvancedPlayers () {

  }

  public AdvancedPlayers (String name, Expertise level) {
    super(name, level);
  }

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

