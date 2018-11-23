package uk.ashleybye.tictactoe.console;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameConsoleTest {

  private MockIOWrapper ioWrapper;
  private GameConsole gameConsole;
  private MockHumanMoveSubscriber humanPlayer;

  @BeforeEach
  void setUp() {
    ioWrapper = new MockIOWrapper();
    gameConsole = new GameConsole(ioWrapper);
    humanPlayer = new MockHumanMoveSubscriber();
  }

  @Test
  void testCorrectlyNotifiesSubscriberWhenMoveMade() {
    ioWrapper.addMockInput("1");

    gameConsole.subscribe(humanPlayer);

    assertEquals(1, ioWrapper.getNumberOfTimesRenderWasCalled());
    assertEquals(1, ioWrapper.getNumberOfTimesGetInputWasCalled());
    assertEquals(1, humanPlayer.valueOfMoveNotification());
  }

  @Test
  void testDoesNotAcceptEmptyStringAsValidInput() {
    ioWrapper.addMockInput("");
    ioWrapper.addMockInput("1");

    gameConsole.subscribe(humanPlayer);

    assertEquals(2, ioWrapper.getNumberOfTimesRenderWasCalled());
    assertEquals(2, ioWrapper.getNumberOfTimesGetInputWasCalled());
    assertEquals(1, humanPlayer.valueOfMoveNotification());
  }
}
