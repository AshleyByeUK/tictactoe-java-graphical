package uk.ashleybye.tictactoe.console.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.ashleybye.tictactoe.TestHelpers.colourisedMark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.console.MockIOWrapper;
import uk.ashleybye.tictactoe.console.gameClient.ConsoleMark;
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
    super(null, null);
  }

  @BeforeEach
  void setUp() {
    playerOneConfiguration = new ConsolePlayerConfiguration();
    playerTwoConfiguration = new ConsolePlayerConfiguration();

    playerOneConfiguration.setPlayerMark(new ConsoleMark("X"));
    playerOneConfiguration.setPlayerName("Player 1");
    playerOneConfiguration.setPlayerType("human");

    playerTwoConfiguration.setPlayerMark(new ConsoleMark("O"));
    playerTwoConfiguration.setPlayerName("Player 2");
    playerTwoConfiguration.setPlayerType("easy");

    gameConfiguration = new ConsoleGameConfiguration();
    gameConfiguration.addPlayerConfiguration(1, playerOneConfiguration);
    gameConfiguration.addPlayerConfiguration(2, playerTwoConfiguration);
    ioWrapper = new MockIOWrapper();

    configurePlayerOneMenuItem = new ConfigurePlayerView(
        this, gameConfiguration, 1, new MockPlayerFactory(), ioWrapper);
    configurePlayerTwoMenuItem = new ConfigurePlayerView(
        this, gameConfiguration, 2, new MockPlayerFactory(), ioWrapper);
  }

  @Test
  void testCorrectlyDisplaysMenuOptionsForPlayerOne() {
    configurePlayerOneMenuItem.render();

    String text = ioWrapper.getRenderedText();
    assertTrue(text.contains("Configure options for Player 1, current options shown in brackets"));
    assertTrue(text.contains("1. Set player name (Player 1)"));
    assertTrue(text.contains("2. Set player mark (" + colourisedMark("X", "[37m", "[37m") + ")"));
    assertTrue(text.contains("3. Set player type (User)"));
    assertTrue(text.contains("4. Done"));
  }

  @Test
  void testCorrectlyDisplaysMenuOptionsForPlayerTwo() {
    configurePlayerTwoMenuItem.render();

    String text = ioWrapper.getRenderedText();
    assertTrue(text.contains("Configure options for Player 2, current options shown in brackets"));
    assertTrue(text.contains("1. Set player name (Player 2)"));
    assertTrue(text.contains("2. Set player mark (" + colourisedMark("O", "[37m", "[37m") + ")"));
    assertTrue(text.contains("3. Set player type (Easy Computer)"));
    assertTrue(text.contains("4. Done"));
  }

  @Test
  void testSetPlayerOneName() {
    View returnedView = configurePlayerOneMenuItem.handleInput("1");

    returnedView.render();
    returnedView.handleInput("New Name");

    String text = ioWrapper.getRenderedText();
    assertTrue(text.contains("Enter a new name for Player 1"));
    assertEquals("New Name", gameConfiguration.getPlayerConfiguration(1).getPlayerName());
  }

  @Test
  void testSetPlayerTwoName() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("1");

    returnedView.render();
    returnedView.handleInput("New Name");

    String text = ioWrapper.getRenderedText();
    assertTrue(text.contains("Enter a new name for Player 2"));
    assertEquals("New Name", gameConfiguration.getPlayerConfiguration(2).getPlayerName());
  }

  @Test
  void testSetPlayerOneMark() {
    View returnedView = configurePlayerOneMenuItem.handleInput("2");

    returnedView.render();
    returnedView.handleInput("a");

    String text = ioWrapper.getRenderedText();
    assertTrue(text.contains("Enter a new mark for Player 1"));
    assertEquals(
        colourisedMark("A", "[37m", "[37m"),
        gameConfiguration.getPlayerConfiguration(1).getPlayerMark().toString());
  }

  @Test
  void testSetPlayerTwoMark() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("2");

    returnedView.render();
    returnedView.handleInput(" B ");

    String text = ioWrapper.getRenderedText();
    assertTrue(text.contains("Enter a new mark for Player 2"));
        assertEquals(
        colourisedMark("B", "[37m", "[37m"),
        gameConfiguration.getPlayerConfiguration(2).getPlayerMark().toString());
  }

  @Test
  void testPlayersMarksCannotBeTheSame() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("2");

    assertThrows(DuplicateMark.class, () -> returnedView.handleInput("X"));
  }

  @Test
  void testSetPlayerOneType() {
    View returnedView = configurePlayerOneMenuItem.handleInput("3");

    returnedView.render();
    returnedView.handleInput("2");

    String text = ioWrapper.getRenderedText();
    assertTrue(text.contains("Select a player type for Player 1"));
    assertTrue(text.contains("1. User"));
    assertTrue(text.contains("2. Easy Computer"));
    assertTrue(text.contains("3. Hard Computer"));
    assertEquals("easy", gameConfiguration.getPlayerConfiguration(1).getPlayerType());
  }

  @Test
  void testSetPlayerTwoType() {
    View returnedView = configurePlayerTwoMenuItem.handleInput("3");

    returnedView.render();
    returnedView.handleInput("1");

    String text = ioWrapper.getRenderedText();
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

    returnedView.render();

    String text = ioWrapper.getRenderedText();
    assertTrue(text.contains("Received Test Class"));
  }

  @Test
  void testInvalidInputThrowsInvalidMenuOption() {
    assertThrows(InvalidMenuOption.class, () -> configurePlayerTwoMenuItem.handleInput("bad view option"));
  }

  @Override
  public void render() {
    ioWrapper.render("Received Test Class");
  }

  @Override
  public View handleInput(String input) {
    return null;
  }
}
