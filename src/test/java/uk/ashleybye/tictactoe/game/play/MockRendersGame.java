package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;

public class MockRendersGame extends RendersGame {
  private int numberOfTimesRenderHasBeenCalled = 0;
  private Game game;

  public MockRendersGame() {
    super(null);
  }

  @Override
  public void render(Game game) {
    numberOfTimesRenderHasBeenCalled++;
    this.game = game;
  }

  public int getNumberOfTimesRenderHasBeenCalled() {
    return numberOfTimesRenderHasBeenCalled;
  }

  public boolean wasLastCalledWith(Game game) {
    return this.game.equals(game);
  }
}
