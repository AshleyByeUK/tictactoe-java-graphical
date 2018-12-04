package uk.ashleybye.tictactoe.console.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.ClientInterface;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.MockGame;
import uk.ashleybye.tictactoe.core.MockGameConfiguration;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;
import uk.ashleybye.tictactoe.core.player.Player;

class ConsoleGameRunnerTest {

  private MockGameConsole console;
  private MockGame game;
  private ConsoleGameRunner runner;

  @BeforeEach
  void setUp() {
    console = new MockGameConsole();
    game = new MockGame();
    runner = new ConsoleGameRunner(game, console);
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
    ConsolePlayerFactory playerFactory = new MockPlayerFactory();
    ConsoleGameConfiguration gameConfiguration = new MockGameConfiguration();
    ClientInterface clientInterface = new MockGameConsole();

    Player playerOne = new MockPlayer(new MockPlayerOneMark(), "Player 1");
    Player playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Player 2");
    Game game = new Game(playerOne, playerTwo, new MockEmptyMark());
    ConsoleGameRunner expected = new ConsoleGameRunner(game, clientInterface);

    ConsoleGameRunner actual = ConsoleGameRunner.create(playerFactory, gameConfiguration, clientInterface);

    assertEquals(expected, actual);
  }

  @Test
  void testEquality() {
    ConsolePlayerFactory playerFactory = new MockPlayerFactory();
    ConsoleGameConfiguration gameConfiguration = new MockGameConfiguration();
    ClientInterface clientInterface = new MockGameConsole();
    ConsoleGameRunner runner = ConsoleGameRunner.create(playerFactory, gameConfiguration, clientInterface);

    Player playerOne = new MockPlayer(new MockPlayerOneMark(), "Not Player 1");
    Player playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Not Player 2");
    Game game = new Game(playerOne, playerTwo, new MockEmptyMark());
    ConsoleGameRunner other = new ConsoleGameRunner(game, clientInterface);

    assertEquals(runner, runner);
    assertEquals(runner, ConsoleGameRunner.create(playerFactory, gameConfiguration, clientInterface));
    assertEquals(runner.hashCode(),
        (ConsoleGameRunner.create(playerFactory, gameConfiguration, clientInterface)).hashCode());
    assertNotEquals(runner, other);
    assertNotEquals(game, "not ConsoleGameRunner");
    assertNotEquals(game, null);
    assertNotEquals(game.hashCode(), other.hashCode());
  }
}
