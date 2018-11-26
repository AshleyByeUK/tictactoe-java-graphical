package uk.ashleybye.tictactoe.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConsoleClientTest {

  private ConsolePlayerConfiguration playerOneConfiguration;
  private ConsolePlayerConfiguration playerTwoConfiguration;
  private ConsoleGameConfiguration gameConfiguration;
  private MockIOWrapper ioWrapper;
  private ConsoleClient consoleClient;

  @BeforeEach
  void setUp() {
    playerOneConfiguration = new ConsolePlayerConfiguration();
    playerTwoConfiguration = new ConsolePlayerConfiguration();

    playerOneConfiguration.setPlayerMark("X");
    playerOneConfiguration.setPlayerName("Player 1");
    playerOneConfiguration.setPlayerType("human");

    playerTwoConfiguration.setPlayerMark("O");
    playerTwoConfiguration.setPlayerName("Player 2");
    playerTwoConfiguration.setPlayerType("easy");

    gameConfiguration = new ConsoleGameConfiguration(Arrays.asList(playerOneConfiguration, playerTwoConfiguration));
    ioWrapper = new MockIOWrapper();
    ioWrapper.retainAllRenderedText();

    consoleClient = new ConsoleClient(gameConfiguration, ioWrapper);
  }

  @Test
  void testMainMenuScreen() {
    ioWrapper.addMockInput("4");
    consoleClient.start();

    String renderedText = ioWrapper.getRenderedText();
    assertTrue(renderedText.contains(MainMenuItem.MAIN_TITLE));
    assertTrue(renderedText.contains("Current game options:"));
    assertTrue(renderedText.contains("Player 1:"));
    assertTrue(renderedText.contains("Type: User"));
    assertTrue(renderedText.contains("Name: Player 1"));
    assertTrue(renderedText.contains("Mark: X"));
    assertTrue(renderedText.contains("Player 2:"));
    assertTrue(renderedText.contains("Type: Easy Computer"));
    assertTrue(renderedText.contains("Name: Player 2"));
    assertTrue(renderedText.contains("Mark: O"));
    assertTrue(renderedText.contains("Select an option below:"));
    assertTrue(renderedText.contains("1. Configure Player 1"));
    assertTrue(renderedText.contains("2. Configure Player 2"));
    assertTrue(renderedText.contains("3. Play a game"));
    assertTrue(renderedText.contains("4. Quit"));
    assertTrue(renderedText.contains("> "));
    assertEquals(2, ioWrapper.getNumberOfTimesRenderWasCalled());
    assertEquals(1, ioWrapper.getNumberOfTimesGetInputWasCalled());
  }

  @Test
  void testMainMenuIsDisplayedUntilQuitIsSelected() {
    ioWrapper.addMockInput("99");
    ioWrapper.addMockInput("4");

    consoleClient.start();

    String renderedText = ioWrapper.getRenderedText();
    assertTrue(renderedText.contains("Invalid option, please try again"));
    assertTrue(renderedText.contains("> "));
    assertTrue(renderedText.contains("Good bye, thank you for playing"));
    assertEquals(4, ioWrapper.getNumberOfTimesRenderWasCalled());
    assertEquals(2, ioWrapper.getNumberOfTimesGetInputWasCalled());
  }

  @Test
  void testCanLaunchAndLeavePlayerOneConfigurationMenu() {
    ioWrapper.addMockInput("1");
    ioWrapper.addMockInput("4");
    ioWrapper.addMockInput("4");

    consoleClient.start();

    String renderedText = ioWrapper.getRenderedText();
    assertTrue(renderedText.contains("Configure options for Player 1"));
    assertEquals(4, ioWrapper.getNumberOfTimesRenderWasCalled());
    assertEquals(3, ioWrapper.getNumberOfTimesGetInputWasCalled());
  }

  @Test
  void testCanLaunchAndLeavePlayerTwoConfigurationMenu() {
    ioWrapper.addMockInput("2");
    ioWrapper.addMockInput("4");
    ioWrapper.addMockInput("4");

    consoleClient.start();

    String renderedText = ioWrapper.getRenderedText();
    assertTrue(renderedText.contains("Configure options for Player 2"));
    assertEquals(4, ioWrapper.getNumberOfTimesRenderWasCalled());
    assertEquals(3, ioWrapper.getNumberOfTimesGetInputWasCalled());
  }

  @Test
  void testCanLaunchAndLeaveAGame() {
    playerOneConfiguration.setPlayerType("easy");
    ioWrapper.addMockInput("3");
    ioWrapper.addMockInput("");
    ioWrapper.addMockInput("4");

    consoleClient.start();

    String renderedText = ioWrapper.getRenderedText();
    assertTrue(renderedText.contains("Press ENTER to return to the main menu"));
    assertEquals(12, ioWrapper.getNumberOfTimesRenderWasCalled());
    assertEquals(3, ioWrapper.getNumberOfTimesGetInputWasCalled());
  }
}
