package com.jeopardy;

import java.util.Scanner;

public class RookiePlayer extends Player {

  public RookiePlayer(String name) {
    super(name);
  }

  public RookiePlayer(String name, Expertise level) {
    this(name);
    setLevel(level);
  }

  @Override//implement interface abstract method
  public int askForHelp(int currentQuestionValue) {
    int result = 0;
    Scanner input = new Scanner(System.in);
    System.out.println("Need any help?");
    if (input.next() == "y") {
      result += currentQuestionValue;
    System.out.println("Skip this question and value of this question automatically added!");
    /*
    Skip this question for rookiePlayer
    points automatically added "for free"
     */}
    return result;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
