package uk.ashleybye.tictactoe.game;

import java.util.List;
import uk.ashleybye.tictactoe.game.play.GameOverview;

public class MockGame extends Game {
  private List<GameState> gameStates;
  private int numberOfTimesGetStateHasBeenCalled = 0;
  private GameOverview gameOverview;

  @Override
  public GameState getState() {
    return gameStates.get(numberOfTimesGetStateHasBeenCalled++);
  }

  @Override
  public GameOverview getOverview() {
    return gameOverview;
  }

  public void returnGameStatesInOrder(List<GameState> gameStates) {
    this.gameStates = gameStates;
  }

  public void returnGameOverview(GameOverview gameOverview) {
    this.gameOverview = gameOverview;
  }
}
