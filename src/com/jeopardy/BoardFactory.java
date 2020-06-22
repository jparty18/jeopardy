package com.jeopardy;

import java.util.List;

public class BoardFactory {
  private BoardFactory() { }

  public static Board createBoard(int session, int numberOfPlayers, List<String> contestants) {
    Board board = new Board(session, numberOfPlayers, contestants);
    return board;
  }
}
