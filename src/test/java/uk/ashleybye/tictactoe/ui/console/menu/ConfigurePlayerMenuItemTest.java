package uk.ashleybye.tictactoe.ui.console.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.ui.console.ConsoleClient.InvalidMenuOption;
import uk.ashleybye.tictactoe.ui.console.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.ui.console.ConsolePlayerConfiguration;
import uk.ashleybye.tictactoe.ui.console.MenuItem;
import uk.ashleybye.tictactoe.ui.console.MockIOWrapper;
import uk.ashleybye.tictactoe.ui.console.MockPlayerFactory;

class ConfigurePlayerMenuItemTest extends MenuItem {

  private ConsolePlayerConfiguration playerOneConfiguration;
  private ConsolePlayerConfiguration playerTwoConfiguration;
  private ConsoleGameConfiguration gameConfiguration;
  private MockIOWrapper ioWrapper;
  private ConfigurePlayerMenuItem configurePlayerOneMenuItem;
  private ConfigurePlayerMenuItem configurePlayerTwoMenuItem;

  protected ConfigurePlayerMenuItemTest() {
    super(null);
  }

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

    gameConfiguration = new ConsoleGameConfiguration();
    gameConfiguration.addPlayerConfiguration(1, playerOneConfiguration);
    gameConfiguration.addPlayerConfiguration(2, playerTwoConfiguration);
    ioWrapper = new MockIOWrapper();

    configurePlayerOneMenuItem = new ConfigurePlayerMenuItem(this, gameConfiguration, 1, new MockPlayerFactory());
    configurePlayerTwoMenuItem = new ConfigurePlayerMenuItem(this, gameConfiguration, 2, new MockPlayerFactory());
  }

  @Test
  void testCorrectlyDisplaysMenuOptionsForPlayerOne() {
    String text = configurePlayerOneMenuItem.launch();

    assertTrue(text.contains("Configure options for Player 1, current options shown in brackets"));
    assertTrue(text.contains("1. Set player name (Player 1)"));
    assertTrue(text.contains("2. Set player mark (X)"));
    assertTrue(text.contains("3. Set player type (User)"));
    assertTrue(text.contains("4. Done"));
  }

  @Test
  void testCorrectlyDisplaysMenuOptionsForPlayerTwo() {
    String text = configurePlayerTwoMenuItem.launch();

    assertTrue(text.contains("Configure options for Player 2, current options shown in brackets"));
    assertTrue(text.contains("1. Set player name (Player 2)"));
    assertTrue(text.contains("2. Set player mark (O)"));
    assertTrue(text.contains("3. Set player type (Easy Computer)"));
    assertTrue(text.contains("4. Done"));
  }

  @Test
  void testSetPlayerOneName() {
    MenuItem returnedMenuItem = configurePlayerOneMenuItem.handleInput("1");
    String text = returnedMenuItem.launch();
    returnedMenuItem.handleInput("New Name");

    assertTrue(text.contains("Enter a new name for Player 1"));
    assertEquals("New Name", gameConfiguration.getPlayerConfiguration(1).getPlayerName());
  }

  @Test
  void testSetPlayerTwoName() {
    MenuItem returnedMenuItem = configurePlayerTwoMenuItem.handleInput("1");
    String text = returnedMenuItem.launch();
    returnedMenuItem.handleInput("New Name");

    assertTrue(text.contains("Enter a new name for Player 2"));
    assertEquals("New Name", gameConfiguration.getPlayerConfiguration(2).getPlayerName());
  }

  @Test
  void testSetPlayerOneMark() {
    MenuItem returnedMenuItem = configurePlayerOneMenuItem.handleInput("2");
    String text = returnedMenuItem.launch();
    returnedMenuItem.handleInput("a");

    assertTrue(text.contains("Enter a new mark for Player 1"));
    assertEquals("A", gameConfiguration.getPlayerConfiguration(1).getPlayerMark().toString());
  }

  @Test
  void testSetPlayerTwoMark() {
    MenuItem returnedMenuItem = configurePlayerTwoMenuItem.handleInput("2");
    String text = returnedMenuItem.launch();
    returnedMenuItem.handleInput(" B ");

    assertTrue(text.contains("Enter a new mark for Player 2"));
    assertEquals("B", gameConfiguration.getPlayerConfiguration(2).getPlayerMark().toString());
  }

  @Test
  void testSetPlayerOneType() {
    MenuItem returnedMenuItem = configurePlayerOneMenuItem.handleInput("3");
    String text = returnedMenuItem.launch();
    returnedMenuItem.handleInput("2");

    assertTrue(text.contains("Select a player type for Player 1"));
    assertTrue(text.contains("1. User"));
    assertTrue(text.contains("2. Easy Computer"));
    assertTrue(text.contains("3. Hard Computer"));
    assertEquals("easy", gameConfiguration.getPlayerConfiguration(1).getPlayerType());
  }

  @Test
  void testSetPlayerTwoType() {
    MenuItem returnedMenuItem = configurePlayerTwoMenuItem.handleInput("3");
    String text = returnedMenuItem.launch();
    returnedMenuItem.handleInput("1");

    assertTrue(text.contains("Select a player type for Player 2"));
    assertTrue(text.contains("1. User"));
    assertTrue(text.contains("2. Easy Computer"));
    assertTrue(text.contains("3. Hard Computer"));
    assertEquals("human", gameConfiguration.getPlayerConfiguration(2).getPlayerType());
  }

  @Test
  void testSetInvalidPlayerType() {
    MenuItem returnedMenuItem = configurePlayerTwoMenuItem.handleInput("3");

    assertThrows(InvalidMenuOption.class, () -> returnedMenuItem.handleInput("999"));
  }

  @Test
  void testSelectingDoneReturnsToPreviousMenu() {
    MenuItem returnedMenuItem = configurePlayerTwoMenuItem.handleInput("4");

    assertEquals("Received Test Class", returnedMenuItem.launch());
  }

  @Test
  void testInvalidInputThrowsInvalidMenuOption() {
    assertThrows(InvalidMenuOption.class, () -> configurePlayerTwoMenuItem.handleInput("bad menu option"));
  }

  @Override
  public String launch() {
    return "Received Test Class";
  }

  @Override
  public MenuItem handleInput(String input) {
    return null;
  }
}
