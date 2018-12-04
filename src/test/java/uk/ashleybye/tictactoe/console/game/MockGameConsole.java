package uk.ashleybye.tictactoe.console.game;

import uk.ashleybye.tictactoe.core.GameReport;

public class MockGameConsole extends GameConsole {

  private int numberOfTimesRenderGameWasCalled = 0;

  public MockGameConsole() {
    super(null);
  }

  public int getNumberOfTimesRenderGameWasCalled() {
    return numberOfTimesRenderGameWasCalled;
  }

  @Override
  public void renderGame(GameReport gameReport) {
    numberOfTimesRenderGameWasCalled++;
  }
}
