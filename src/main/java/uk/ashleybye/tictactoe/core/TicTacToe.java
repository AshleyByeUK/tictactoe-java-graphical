package uk.ashleybye.tictactoe.core;

import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;

public class TicTacToe {

  private final UserInterface userInterface;
  private Game game;

  public TicTacToe(Game game, UserInterface userInterface) {
    this.game = game;
    this.userInterface = userInterface;
  }

  public void play() {
    while (!game.isGameOver())
      playGame();
    userInterface.renderGame(game.generateGameReport());
  }

  private void playGame() {
    try {
      userInterface.renderGame(game.generateGameReport());
      game = game.playNextTurn();
    } catch (InvalidSquareNumber | SquareUnavailable ex) {
      // Do nothing.
    }
  }
}
