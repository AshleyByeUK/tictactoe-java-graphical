package uk.ashleybye.tictactoe.console.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.console.MockIOWrapper;
import uk.ashleybye.tictactoe.console.gameClient.MockPlayerFactory;
import uk.ashleybye.tictactoe.console.ConsoleClient.InvalidMenuOption;
import uk.ashleybye.tictactoe.console.gameClient.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.gameClient.ConsolePlayerConfiguration;
import uk.ashleybye.tictactoe.console.view.SetPlayerMarkView.DuplicateMark;

class ConfigurePlayerViewTest extends View {

  private ConsolePlayerConfiguration playerOneConfiguration;
  private ConsolePlayerConfiguration playerTwoConfiguration;
  private ConsoleGameConfiguration gameConfiguration;
  private MockIOWrapper ioWrapper;
  private ConfigurePlayerView configurePlayerOneMenuItem;
  private ConfigurePlayerView configurePlayerTwoMenuItem;

  protected ConfigurePlayerViewTest() {
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

    configurePlayerOneMenuItem = new ConfigurePlayerView(this, gameConfiguration, 1, new MockPlayerFactory());
    configurePlayerTwoMenuItem = new ConfigurePlayerView(this, gameConfiguration, 2, new MockPlayerFactory());
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
    View returnedView = configurePlayerOneMenuItem.handleInput("1");
    String text = returnedView.launch();
    returnedView.handleInput("New Name");

    assertTrue(text.contains("Enter a new name for Player 1"));
    assertEquals("New Name", gameConfiguration.getPlayerConfiguration(1).getPlayerName());
  }

  @Test
  void testSetPlayerTwoName() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("1");
    String text = returnedView.launch();
    returnedView.handleInput("New Name");

    assertTrue(text.contains("Enter a new name for Player 2"));
    assertEquals("New Name", gameConfiguration.getPlayerConfiguration(2).getPlayerName());
  }

  @Test
  void testSetPlayerOneMark() {
    View returnedView = configurePlayerOneMenuItem.handleInput("2");
    String text = returnedView.launch();
    returnedView.handleInput("a");

    assertTrue(text.contains("Enter a new mark for Player 1"));
    assertEquals("A", gameConfiguration.getPlayerConfiguration(1).getPlayerMark().toString());
  }

  @Test
  void testSetPlayerTwoMark() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("2");
    String text = returnedView.launch();
    returnedView.handleInput(" B ");

    assertTrue(text.contains("Enter a new mark for Player 2"));
    assertEquals("B", gameConfiguration.getPlayerConfiguration(2).getPlayerMark().toString());
  }

  @Test
  void testPlayersMarksCannotBeTheSame() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("2");

    assertThrows(DuplicateMark.class, () -> returnedView.handleInput("X"));
  }

  @Test
  void testSetPlayerOneType() {
    View returnedView = configurePlayerOneMenuItem.handleInput("3");
    String text = returnedView.launch();
    returnedView.handleInput("2");

    assertTrue(text.contains("Select a player type for Player 1"));
    assertTrue(text.contains("1. User"));
    assertTrue(text.contains("2. Easy Computer"));
    assertTrue(text.contains("3. Hard Computer"));
    assertEquals("easy", gameConfiguration.getPlayerConfiguration(1).getPlayerType());
  }

  @Test
  void testSetPlayerTwoType() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("3");
    String text = returnedView.launch();
    returnedView.handleInput("1");

    assertTrue(text.contains("Select a player type for Player 2"));
    assertTrue(text.contains("1. User"));
    assertTrue(text.contains("2. Easy Computer"));
    assertTrue(text.contains("3. Hard Computer"));
    assertEquals("human", gameConfiguration.getPlayerConfiguration(2).getPlayerType());
  }

  @Test
  void testSetInvalidPlayerType() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("3");

    assertThrows(InvalidMenuOption.class, () -> returnedView.handleInput("999"));
  }

  @Test
  void testSelectingDoneReturnsToPreviousMenu() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("4");

    assertEquals("Received Test Class", returnedView.launch());
  }

  @Test
  void testInvalidInputThrowsInvalidMenuOption() {
    assertThrows(InvalidMenuOption.class, () -> configurePlayerTwoMenuItem.handleInput("bad view option"));
  }

  @Override
  public String launch() {
    return "Received Test Class";
  }

  @Override
  public View handleInput(String input) {
    return null;
  }
}
