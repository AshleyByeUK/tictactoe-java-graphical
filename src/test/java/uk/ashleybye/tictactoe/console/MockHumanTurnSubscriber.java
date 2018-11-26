package uk.ashleybye.tictactoe.console;

import uk.ashleybye.tictactoe.core.HumanTurnSubscriber;

public class MockHumanTurnSubscriber implements HumanTurnSubscriber {

  private int position;

  @Override
  public void notifyMoveMade(int position) {
    this.position = position;
  }

  @Override
  public String getName() {
    return "Player Name";
  }

  public int valueOfMoveNotification() {
    return position;
  }
}
