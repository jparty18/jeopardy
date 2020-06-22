package com.jeopardy;

import java.util.ArrayList;
import java.util.List;

public class Board {
  Board(int session, int numberOfPlayers, List<String> contestants) {
    List<Player> players = new ArrayList<>();
    for (String contestant : contestants) {
      players.add(new AdvancedPlayer(contestant, Expertise.ADVANCED));
    }
  }
}
