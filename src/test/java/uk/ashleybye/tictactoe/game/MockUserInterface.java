package uk.ashleybye.tictactoe.game;

import uk.ashleybye.tictactoe.game.play.GameOverview;

public class MockUserInterface implements UserInterface {
  private GameOverview gameOverview;

  @Override
  public void update(GameOverview gameOverview) {
    this.gameOverview = gameOverview;
  }

  public boolean updateWasCalledWith(GameOverview gameOverview) {
    return this.gameOverview.equals(gameOverview);
  }
}
