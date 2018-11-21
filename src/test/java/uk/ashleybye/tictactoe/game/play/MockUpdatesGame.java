package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.MockGame;

public class MockUpdatesGame extends UpdatesGame {
  private int numberOfTimesApplyHasBeenCalled = 0;
  private Move move;
  private Game game;

  @Override
  public Game apply(Move move, Game game) {
    this.move = move;
    this.game = game;
    numberOfTimesApplyHasBeenCalled++;
    return game;
  }

  public int getNumberOfTimesApplyHasBeenCalled() {
    return numberOfTimesApplyHasBeenCalled;
  }

  public boolean wasLastCalledWith(Move move, Game game) {
    return this.move.equals(move) && this.game.equals(game);
  }
}
