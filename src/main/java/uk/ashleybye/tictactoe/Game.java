package uk.ashleybye.tictactoe;

import java.util.List;

public class Game {

  private final Player playerOne;
  private final Player playerTwo;
  private Board board;

  public Game(Player playerOne, Player playerTwo, Mark memptyMark) {

    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    board = new Board(memptyMark);
  }

  public List<Square> listUnmarkedSquares() {
    return board.listUnmarkedSquares();
  }
}
