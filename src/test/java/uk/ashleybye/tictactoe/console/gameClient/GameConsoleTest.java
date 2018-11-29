package uk.ashleybye.tictactoe.console.gameClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.console.MockIOWrapper;
import uk.ashleybye.tictactoe.core.GameReport;
import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;

public class GameConsoleTest {

  private MockIOWrapper ioWrapper;
  private GameConsole gameConsole;

  @BeforeEach
  void setUp() {
    ioWrapper = new MockIOWrapper();
    gameConsole = new GameConsole(ioWrapper);
  }

  @Test
  void testCorrectlyNotifiesSubscriberWhenMoveMade() {
    ioWrapper.addMockInput("1");

    assertEquals(1, gameConsole.getPlayersMove());
  }

  @Test
  void testDoesNotAcceptEmptyStringAsValidInput() {
    ioWrapper.addMockInput("");

    assertThrows(InvalidSquareNumber.class, () -> gameConsole.getPlayersMove());
  }

  @Test
  void testDoesNotAcceptNonTextAsValidInput() {
    ioWrapper.addMockInput("a string of text");

    assertThrows(InvalidSquareNumber.class, () -> gameConsole.getPlayersMove());
  }

  @Test
  void testDoesNotAcceptFloatingPointNumbersAsValidInput() {
    ioWrapper.addMockInput("3.14");

    assertThrows(InvalidSquareNumber.class, () -> gameConsole.getPlayersMove());
  }

  @Test
  void testCorrectlyRendersReadyGame() {
    GameReport report = MockGameReport.readyGame();

    gameConsole.renderGame(report);

    String renderedText = ioWrapper.getRenderedText();
    assertTrue(renderedText.contains("Great, let's play a game of Tic Tac Toe!\n\n"));
    assertTrue(renderedText.contains(" 1 | 2 | 3 \n-----------\n 4 | 5 | 6 \n-----------\n 7 | 8 | 9 "));
    assertFalse(renderedText.contains("played in position"));
    assertTrue(renderedText.contains("Player 1's turn > "));
    assertFalse(renderedText.contains("That square is not available, try again Player 1 > "));
    assertFalse(renderedText.contains("Game over"));
  }

  @Test
  void testCorrectlyRendersReadyGameAfterInvalidFirstMove() {
    GameReport report = MockGameReport.readyGame();

    gameConsole.renderGame(report);
    gameConsole.renderGame(report);

    String renderedText = ioWrapper.getRenderedText();
    assertFalse(renderedText.contains("Great, let's play a gameClient of Tic Tac Toe!\n\n"));
    assertTrue(renderedText.contains(" 1 | 2 | 3 \n-----------\n 4 | 5 | 6 \n-----------\n 7 | 8 | 9 "));
    assertFalse(renderedText.contains("played in position"));
    assertFalse(renderedText.contains("Player 1's turn > "));
    assertTrue(renderedText.contains("That square is not available, try again Player 1 > "));
    assertFalse(renderedText.contains("Game over"));
  }

  @Test
  void testCorrectlyRendersInProgressGameAfterReadyGame() {
    GameReport readyReport = MockGameReport.readyGame();
    GameReport inProgressReport = MockGameReport.inProgressGame();

    gameConsole.renderGame(readyReport);
    gameConsole.renderGame(inProgressReport);

    String renderedText = ioWrapper.getRenderedText();
    assertFalse(renderedText.contains("Great, let's play a gameClient of Tic Tac Toe!\n\n"));
    assertTrue(renderedText.contains(" X | O | 3 \n-----------\n 4 | 5 | 6 \n-----------\n 7 | 8 | 9 "));
    assertTrue(renderedText.contains("played in position"));
    assertTrue(renderedText.contains("Player 1's turn > "));
    assertFalse(renderedText.contains("That square is not available, try again Player 1 > "));
    assertFalse(renderedText.contains("Game over"));
  }

  @Test
  void testCorrectlyRendersTiedGame() {
    GameReport readyReport = MockGameReport.readyGame();
    GameReport inProgressReport = MockGameReport.inProgressGame();
    GameReport report = MockGameReport.tiedGame();

    gameConsole.renderGame(readyReport);
    gameConsole.renderGame(inProgressReport);
    gameConsole.renderGame(report);

    String renderedText = ioWrapper.getRenderedText();
    assertFalse(renderedText.contains("Great, let's play a gameClient of Tic Tac Toe!\n\n"));
    assertTrue(renderedText.contains(" X | X | O \n-----------\n O | X | X \n-----------\n X | O | O "));
    assertTrue(renderedText.contains("played in position"));
    assertFalse(renderedText.contains("Player 1's turn > "));
    assertFalse(renderedText.contains("Player 2's turn > "));
    assertFalse(renderedText.contains("That square is not available, try again Player 1 > "));
    assertTrue(renderedText.contains("Game over"));
    assertTrue(renderedText.contains("It's a draw"));
  }

  @Test
  void testCorrectlyRendersWonGamePlayerOneWins() {
    GameReport readyReport = MockGameReport.readyGame();
    GameReport inProgressReport = MockGameReport.inProgressGame();
    GameReport report = MockGameReport.playerOneWins();

    gameConsole.renderGame(readyReport);
    gameConsole.renderGame(inProgressReport);
    gameConsole.renderGame(report);

    String renderedText = ioWrapper.getRenderedText();
    assertFalse(renderedText.contains("Great, let's play a gameClient of Tic Tac Toe!\n\n"));
    assertTrue(renderedText.contains(" X | X | X \n-----------\n O | O | 6 \n-----------\n 7 | 8 | 9 "));
    assertTrue(renderedText.contains("played in position"));
    assertFalse(renderedText.contains("Player 1's turn > "));
    assertFalse(renderedText.contains("Player 2's turn > "));
    assertFalse(renderedText.contains("That square is not available, try again Player 1 > "));
    assertTrue(renderedText.contains("Game over"));
    assertTrue(renderedText.contains("Player 1 won"));
  }

  @Test
  void testCorrectlyRendersWonGamePlayerTwoWins() {
    GameReport readyReport = MockGameReport.readyGame();
    GameReport inProgressReport = MockGameReport.inProgressGame();
    GameReport report = MockGameReport.playerTwoWins();

    gameConsole.renderGame(readyReport);
    gameConsole.renderGame(inProgressReport);
    gameConsole.renderGame(report);

    String renderedText = ioWrapper.getRenderedText();
    assertFalse(renderedText.contains("Great, let's play a gameClient of Tic Tac Toe!\n\n"));
    assertTrue(renderedText.contains(" X | X | 3 \n-----------\n O | O | O \n-----------\n 7 | 8 | X "));
    assertTrue(renderedText.contains("played in position"));
    assertFalse(renderedText.contains("Player 1's turn > "));
    assertFalse(renderedText.contains("Player 2's turn > "));
    assertFalse(renderedText.contains("That square is not available, try again Player 1 > "));
    assertTrue(renderedText.contains("Game over"));
    assertTrue(renderedText.contains("Player 2 won"));
  }
}
