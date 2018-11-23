package uk.ashleybye.tictactoe.console;

import uk.ashleybye.tictactoe.game.HumanMovePublisher;
import uk.ashleybye.tictactoe.game.HumanMoveSubscriber;

public class ConsoleHumanMovePublisher implements HumanMovePublisher {

  private HumanMoveSubscriber subscriber;

  @Override
  public void subscribe(HumanMoveSubscriber subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void handleMoveMade(int position) {
    subscriber.notifyMoveMade(position);
  }
}
