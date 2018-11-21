package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;

public class MockGeneratesGameOverview extends GeneratesGameOverview {
  private GameOverview gameOverview;
  private Game game;

  @Override
  public GameOverview parse(Game game) {
    this.game = game;
    return gameOverview;
  }

  public void returnGameOverview(GameOverview gameOverview) {
    this.gameOverview = gameOverview;
  }

  public boolean wasCalledWith(Game game) {
    return this.game.equals(game);
  }
}
