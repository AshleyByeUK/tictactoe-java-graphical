package uk.ashleybye.tictactoe.core.player;

import uk.ashleybye.tictactoe.core.ClientInterface;

public class MockClientInterface implements ClientInterface {

  private final int positionToReturn;
  private final long delayInMs;

  public MockClientInterface(int positionToReturn, long delayInMs) {
    this.positionToReturn = positionToReturn;
    this.delayInMs = delayInMs;
  }

  @Override
  public int getPlayersMove() {
    try {
      Thread.sleep(delayInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return positionToReturn;
  }
}
