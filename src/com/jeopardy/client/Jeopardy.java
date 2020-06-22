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
        System.out.println("Select a mode: ");
        System.out.println("1: Easy");
        System.out.println("2: Hard");
        difficulty = input.nextInt();
        validInputs = true;
      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid input: an integer");
      }
    }

    Board newGame = BoardFactory.createBoard(session, numberPlayers, difficulty);

    System.out.println("\nWelcome to the J-PARTY!");

    StringBuilder intro = new StringBuilder("Tonight's contestants are: " + "\n");
    List<Player> contestants = Contestants.getContestants(numberPlayers);
    for (Player p : contestants) {
      intro.append(p.getName() + "\n");
    }
    System.out.println(intro);

    System.out.println("Press enter to begin");
    Scanner wait = new Scanner(System.in);
    wait.nextLine();

    System.out.println("Our first guest is: ");
    String name = contestants.get(new Random().nextInt(numberPlayers - 1)).getName();

    // TODO: display available questions
    // user can choose $ value & question

    System.out.println(name + ", please choose a question.");
    wait.nextLine();


    // attempt: 1, isDailyDouble?

//    for (int i = 0; i < 4; i++) {
//      System.out.print("-----------------------" + "          ");
//      System.out.print("\n" + "|       $100           |" + "          ");
//    }

    // closing comment

    // restart or exit?
  }
}
