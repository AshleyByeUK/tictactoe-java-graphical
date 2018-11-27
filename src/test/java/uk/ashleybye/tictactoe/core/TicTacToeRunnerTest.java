package uk.ashleybye.tictactoe.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.console.gameClient.MockGameConsole;
import uk.ashleybye.tictactoe.console.gameClient.MockPlayerFactory;

class TicTacToeRunnerTest {

  private MockGameConsole console;
  private MockTicTacToe game;
  private TicTacToeRunner runner;

  @BeforeEach
  void setUp() {
    console = new MockGameConsole();
    game = new MockTicTacToe();
    runner = new TicTacToeRunner(game, console);
  }

  @Test
  void testStopsRunningWhenGameIsOver() {
    game.addIsGameOver(true);

    runner.play();

    assertEquals(1, game.getNumberOfTimesIsGameOverCalled());
    assertEquals(1, game.getNumberOfTimesGenerateGameReportCalled());
    assertEquals(1, console.getNumberOfTimesRenderGameWasCalled());
  }

  @Test
  void testKeepsRunningUntilGameIsOver() {
    game.addIsGameOver(false);
    game.addIsGameOver(true);

    runner.play();

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

    runner.play();

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

    runner.play();

    assertEquals(3, game.getNumberOfTimesIsGameOverCalled());
    assertEquals(3, game.getNumberOfTimesGenerateGameReportCalled());
    assertEquals(3, console.getNumberOfTimesRenderGameWasCalled());
  }

  @Test
  void testCreatesNewGame() {
    PlayerFactory playerFactory = new MockPlayerFactory();
    GameConfiguration gameConfiguration = new MockGameConfiguration();
    ClientInterface clientInterface = new MockGameConsole();

    Player playerOne = new MockPlayer(new MockPlayerOneMark(), "Player 1");
    Player playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Player 2");
    TicTacToe ticTacToe = new TicTacToe(playerOne, playerTwo, new MockEmptyMark());
    TicTacToeRunner expected = new TicTacToeRunner(ticTacToe, clientInterface);

    TicTacToeRunner actual = TicTacToeRunner.create(playerFactory, gameConfiguration, clientInterface);

    assertEquals(expected, actual);
  }

  @Test
  void testEquality() {
    PlayerFactory playerFactory = new MockPlayerFactory();
    GameConfiguration gameConfiguration = new MockGameConfiguration();
    ClientInterface clientInterface = new MockGameConsole();
    TicTacToeRunner runner = TicTacToeRunner.create(playerFactory, gameConfiguration, clientInterface);

    Player playerOne = new MockPlayer(new MockPlayerOneMark(), "Not Player 1");
    Player playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Not Player 2");
    TicTacToe ticTacToe = new TicTacToe(playerOne, playerTwo, new MockEmptyMark());
    TicTacToeRunner other = new TicTacToeRunner(ticTacToe, clientInterface);

    assertEquals(runner, runner);
    assertEquals(runner, TicTacToeRunner.create(playerFactory, gameConfiguration, clientInterface));
    assertEquals(runner.hashCode(), (TicTacToeRunner.create(playerFactory, gameConfiguration, clientInterface)).hashCode());
    assertNotEquals(runner, other);
    assertNotEquals(ticTacToe, "not TicTacToeRunner");
    assertNotEquals(ticTacToe, null);
    assertNotEquals(ticTacToe.hashCode(), other.hashCode());
  }
}
