package com.jeopardy.client;

import com.jeopardy.Board;
import com.jeopardy.BoardFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Jeopardy {
  private static final String COMMA_DELIMITER = ",";

  public static void main(String[] args) {
    int session = 0;
    int numberPlayers = 0;
    boolean validInputs = false;
    BufferedReader names = null;
    BufferedReader questions = null;
    List<String> contestants = new ArrayList<>();

    try {
      names = new BufferedReader(new FileReader("Players.csv"));
      questions = new BufferedReader(new FileReader("Players.csv"));
      String line = "";

      while ((line = names.readLine()) != null)
      {
        String[] contestant = line.split(COMMA_DELIMITER);

        if(contestant.length > 0 )
        {
          String name = contestant[0];
          contestants.add(name);
        }
      }

      for(String name : contestants)
      {
        System.out.println(name);
      }

    } catch (FileNotFoundException e) {
      e.getMessage();
    } catch (IOException ioE) {
      ioE.getStackTrace();
    } finally {
//      try
//      {
//        names.close();
//      }
//      catch(IOException ioE)
//      {
//        System.out.println("Error: Failed to close the BufferedReader");
//        ioE.printStackTrace();
//      }
    }

    while(!validInputs) {
      try {
        Scanner input = new Scanner(System.in);
        System.out.print("Select a category: ");
        session = input.nextInt();
        System.out.print("Enter the number of players: ");
        numberPlayers = input.nextInt();
        validInputs = true;
      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid input: an integer");
      }
    }

    // TODO: delete the manual adding process once csv reader implementation's complete
    contestants.add("Joe");
    contestants.add("Wei");
    contestants.add("Wonil");


    Board newGame = BoardFactory.createBoard(session, numberPlayers, contestants);

    System.out.println("\nWelcome to the J-PARTY!");

    StringBuilder intro = new StringBuilder("Tonight's contestants are ");
    // contestants.append("");
    System.out.println(intro);

  }
}
