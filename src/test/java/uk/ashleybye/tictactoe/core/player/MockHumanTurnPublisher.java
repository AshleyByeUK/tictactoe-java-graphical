package uk.ashleybye.tictactoe.core.player;

public class MockHumanTurnPublisher implements HumanTurnPublisher {

  private final int positionToReturn;
  private final long delayInMs;

  public MockHumanTurnPublisher(int positionToReturn, long delayInMs) {
    this.positionToReturn = positionToReturn;
    this.delayInMs = delayInMs;
  }

  @Override
  public void subscribeToTurnNotifications(HumanTurnSubscriber humanPlayer) {
    try {
      Thread.sleep(delayInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    humanPlayer.notifyMoveMade(positionToReturn);
  }

  @Override
  public void handleMoveMade(int position) {

  }
}
