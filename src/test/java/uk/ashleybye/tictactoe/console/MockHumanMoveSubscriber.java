package uk.ashleybye.tictactoe.console;

import uk.ashleybye.tictactoe.game.HumanMoveSubscriber;

public class MockHumanMoveSubscriber implements HumanMoveSubscriber {

  private int position;

  @Override
  public void notifyMoveMade(int position) {
    this.position = position;
  }

  public int valueOfMoveNotification() {
    return position;
  }
}
