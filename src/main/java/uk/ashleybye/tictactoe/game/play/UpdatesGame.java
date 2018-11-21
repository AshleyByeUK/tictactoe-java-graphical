package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.Move;

public class UpdatesGame {
  public Game apply(Move move, Game game) {
    return game.applyMove(move);
  }
}
