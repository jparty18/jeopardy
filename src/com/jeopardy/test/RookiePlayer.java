package com.jeopardy.test;

public class RookiePlayer extends Player {

  public RookiePlayer(String name, Expertise level) {
    super(name, level);
  }

  @Override//implement superClass abstract method
  public int calculateFinalScore() {
    int finalScore = 0;
    /*
    calculate final score for each rookiePlayer
    correct questions: add points
    incorrect questions: no points deducted
     */
    return finalScore;
  }

  @Override//implement interface abstract method
  public int askForHelp() {
    int result = 0;
    System.out.println("Skip this question and value of this question automatically added!");
    /*
    Skip this question for rookiePlayer
    points automatically added "for free"
     */
    return result;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
