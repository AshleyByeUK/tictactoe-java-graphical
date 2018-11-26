package uk.ashleybye.tictactoe.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.console.MockGameConsole;

class TicTacToeTest {

  private MockGameConsole console;
  private MockGame game;
  private TicTacToe ticTacToe;

  @BeforeEach
  void setUp() {
    console = new MockGameConsole();
    game = new MockGame();
    ticTacToe = new TicTacToe(game, console);
  }

  @Test
  void testStopsRunningWhenGameIsOver() {
    game.addIsGameOver(true);

    ticTacToe.play();

    assertEquals(1, game.getNumberOfTimesIsGameOverCalled());
    assertEquals(1, game.getNumberOfTimesGenerateGameReportCalled());
    assertEquals(1, console.getNumberOfTimesRenderGameWasCalled());
  }

  @Test
  void testKeepsRunningUntilGameIsOver() {
    game.addIsGameOver(false);
    game.addIsGameOver(true);

    ticTacToe.play();

    assertEquals(2, game.getNumberOfTimesIsGameOverCalled());
    assertEquals(2, game.getNumberOfTimesGenerateGameReportCalled());
    assertEquals(2, console.getNumberOfTimesRenderGameWasCalled());
  }

  @Test
  void testTurnIsNotValidAfterInvalidSquareNumberGiven() {
    game.addIsGameOver(false);
    game.addIsGameOver(false);
    game.addIsGameOver(true);
    game.setThrowInvalidSquareNumber();

    ticTacToe.play();

    assertEquals(3, game.getNumberOfTimesIsGameOverCalled());
    assertEquals(3, game.getNumberOfTimesGenerateGameReportCalled());
    assertEquals(3, console.getNumberOfTimesRenderGameWasCalled());
  }

  @Test
  void testTurnIsNotValidAfterUnavailableSquareGiven() {
    game.addIsGameOver(false);
    game.addIsGameOver(false);
    game.addIsGameOver(true);
    game.setThrowSquareUnavailable();

    ticTacToe.play();

    assertEquals(3, game.getNumberOfTimesIsGameOverCalled());
    assertEquals(3, game.getNumberOfTimesGenerateGameReportCalled());
    assertEquals(3, console.getNumberOfTimesRenderGameWasCalled());
  }
}
