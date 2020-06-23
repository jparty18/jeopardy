package com.jeopardy.client;

import com.jeopardy.Board;
import com.jeopardy.BoardFactory;
import com.jeopardy.Player;
import com.jeopardy.sample.Contestants;

import java.io.*;
import java.util.*;

public class Jeopardy {

  public static void main(String[] args) {
    int session = 0;
    int numberPlayers = 0;
    int difficulty = 0;
    boolean validInputs = false;

    while (!validInputs) {
      try {
        Scanner input = new Scanner(System.in);
        System.out.print("Select a category: ");
        session = input.nextInt();

        System.out.print("Enter the number of players: ");
        numberPlayers = input.nextInt();

        System.out.println("\n" + "1: Easy" + "\t" + "|" + "\t" + "2: Hard");
        System.out.print("Select a mode: ");
        difficulty = input.nextInt();
        validInputs = true;
      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid input: an integer");
      }
    }

    Board newGame = BoardFactory.createBoard(session, numberPlayers, difficulty);

    // TODO: clear the console window
    newGame.clearScreen();
    System.out.println("\nWelcome to the J-PARTY!");

    StringBuilder intro = new StringBuilder("Tonight's contestants are: " + "\n");
    intro.append(newGame.getAllPlayers());
    System.out.println(intro);

    System.out.println("Press enter to begin");
    Scanner wait = new Scanner(System.in);
    wait.nextLine();



    boolean firstQuestion = true;

    while (newGame.getQuestions().size() > 0) {
      String currentPlayer = newGame.getAPlayerName();
      System.out.println("Our guest is: " + currentPlayer);
      System.out.println(currentPlayer + ", please choose a question.");

      System.out.println(newGame.getAllQuestion());
      int dollarValue = wait.nextInt();
      newGame.getAQuestion(dollarValue).displayQuestion();

      // TODO: display answer choices
      // 1: correct answer 2: tricky answer 3: bs
      int answer = wait.nextInt();

      // DONE: process score for the player
      newGame.processScore(true, currentPlayer, dollarValue);


      // DONE: display scores
      newGame.displayScores();

    }

    // TODO: option to replay or exit
    System.out.println("Thank you for playing. See you next time!");
  }
}
