package com.jeopardy.client;

import com.jeopardy.Board;
import com.jeopardy.BoardFactory;
import com.jeopardy.Util;

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
        Util.STREAM_DISPLAY(Util.TEXT_READER("Category.txt"));
        System.out.print("Select a category: ");
        session = input.nextInt();

        System.out.print("Enter the number of players: ");
        numberPlayers = input.nextInt();

        System.out.println("\n" + "1: Easy" + "\t" + "|" + "\t" + "2: Hard");
        System.out.print("Select a mode: ");
        difficulty = input.nextInt();
        validInputs = true;
        Util.CLEAR_SCREEN();
      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid input: an integer");
      }
    }

    Board newGame = BoardFactory.createBoard(session, numberPlayers, difficulty);
    newGame.start();
  }
}
