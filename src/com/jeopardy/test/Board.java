package com.jeopardy.test;

import java.util.ArrayList;

import java.util.List;

public class Board {
  Board(int session, int numberOfPlayers, int difficulty) {
    List<Player> players = new ArrayList<>();
    Expertise expertise = difficulty == 1 ? Expertise.ROOKIE : Expertise.ADVANCED;
    for (String contestant : contestants) {
      players.add(new AdvancedPlayer(contestant, expertise));
    }

    // add questions
    // stream filters session == question.session


  }

  private List<String> contestants = new ArrayList<>();

  // Business Methods
  // Select a $ value => print available questions
  // user selects a question => print the question and wait
}

