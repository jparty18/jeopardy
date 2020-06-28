package com.jeopardy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Util {
  public static final int INPUT_HANDLER(String question) {
    int userInput = 0;
    boolean validInput = false;
    while (!validInput) {
      try{
        System.out.print(question);
        Scanner wait = new Scanner(System.in);
        userInput = wait.nextInt();
        validInput = true;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid integer value.");
      }
    }

    return userInput;
  }

  public static final int INPUT_HANDLER(List<String> questions) {
    int userInput = 0;
    boolean validInput = false;
    while (!validInput) {
      try{
        for (String question : questions) {
          System.out.print(question);
        }
        Scanner wait = new Scanner(System.in);
        userInput = wait.nextInt();
        validInput = true;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid integer value.");
      }
    }

    return userInput;
  }

  public static final Stream<String> TEXT_READER(String fileName) {
    Stream<String> result = null;
    try {
      result = Files.lines(Paths.get("sample",fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static final void STREAM_DISPLAY(Stream<String> stream) {
    System.out.print("|\t");
    stream.forEach(line -> {
      System.out.print(line + "\t|" + "\t");
    });
    System.out.print("\n");
  }

  public static final void CLEAR_SCREEN() {
    try {
      final String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
