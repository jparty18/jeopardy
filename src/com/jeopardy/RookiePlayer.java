package com.jeopardy;

public class RookiePlayer extends Player {

  public RookiePlayer(String name) {
    super(name);
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
