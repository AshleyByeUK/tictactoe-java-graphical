package uk.ashleybye.tictactoe.game;

import java.util.List;

public class MockGame extends Game {
  private List<GameState> gameStates;
  private int numberOfTimesGetStateHasBeenCalled = 0;

  @Override
  public GameState getState() {
    return gameStates.get(numberOfTimesGetStateHasBeenCalled++);
  }

  public void returnGameStatesInOrder(List<GameState> gameStates) {
    this.gameStates = gameStates;
  }
}
