package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;

public class TakesPlayersTurn {
  public Move takeTurn(Game game) {
    Move move = game.getNextMove();
    return move;
  }
}
