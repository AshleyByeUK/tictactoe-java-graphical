package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.Move;

public class TakesPlayersTurn {
  public Move takeTurn(Game game) {
    Move move = game.getNextMove();
    return move;
  }
}
