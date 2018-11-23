package uk.ashleybye.tictactoe.console;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.HumanMovePublisher;

public class ConsoleHumanMovePublisherTest {

  @Test
  void testCorrectlyNotifiesSubscriberWhenMoveMade() {
    MockHumanMoveSubscriber subscriber = new MockHumanMoveSubscriber();
    HumanMovePublisher publisher = new ConsoleHumanMovePublisher();

    publisher.subscribe(subscriber);
    publisher.handleMoveMade(1);

    assertEquals(1, subscriber.valueOfMoveNotification());
  }
}
