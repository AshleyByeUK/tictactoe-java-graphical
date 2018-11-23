package uk.ashleybye.tictactoe;

public class MockHumanMovePublisher implements HumanMovePublisher {

  private final int positionToReturn;
  private final long delayInMs;

  public MockHumanMovePublisher(int positionToReturn, long delayInMs) {
    this.positionToReturn = positionToReturn;
    this.delayInMs = delayInMs;
  }

  @Override
  public void subscribe(HumanMoveSubscriber subscriber) {
    try {
      Thread.sleep(delayInMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    subscriber.notifyMoveMade(positionToReturn);
  }
}
