package com.jeopardy.client;

import com.jeopardy.BoardFactory;

import java.util.Scanner;

public class Jeopardy {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Select a category: ");
    String session = input.nextLine();
    System.out.print("Enter the number of players: ");
    int numberPlayers = input.nextInt();

    BoardFactory.createBoard(session, numberPlayers);

    System.out.println("\nWelcome to the J-PARTY!");

    System.out.println("Tonight's contestants are ");

  }
}
