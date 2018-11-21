package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;

public class MockPlaysGame extends PlaysGame {
  private Game game;

  public MockPlaysGame() {
    super(null, null, null);
  }

  @Override
  public void play(Game game) {
    this.game = game;
  }

  public boolean wasCalledWith(Game game) {
    return this.game.equals(game);
  }
}
