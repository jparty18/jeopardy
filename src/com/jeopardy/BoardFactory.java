package com.jeopardy;

public class BoardFactory {
  private BoardFactory() { }

  public static Board createBoard(String session, int numberOfPlayers) {
    Board board = new Board(session, numberOfPlayers);
    return board;
  }
}
