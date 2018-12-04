package uk.ashleybye.tictactoe.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.console.game.MockGameConsole;
import uk.ashleybye.tictactoe.console.game.MockPlayerFactory;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.core.player.PlayerFactory;

class GameRunnerTest {

  private MockGameConsole console;
  private MockGame game;
  private GameRunner runner;

  @BeforeEach
  void setUp() {
    console = new MockGameConsole();
    game = new MockGame();
    runner = new GameRunner(game, console);
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
    Game game = new Game(playerOne, playerTwo, new MockEmptyMark());
    GameRunner expected = new GameRunner(game, clientInterface);

    GameRunner actual = GameRunner.create(playerFactory, gameConfiguration, clientInterface);

    assertEquals(expected, actual);
  }

  @Test
  void testEquality() {
    PlayerFactory playerFactory = new MockPlayerFactory();
    GameConfiguration gameConfiguration = new MockGameConfiguration();
    ClientInterface clientInterface = new MockGameConsole();
    GameRunner runner = GameRunner.create(playerFactory, gameConfiguration, clientInterface);

    Player playerOne = new MockPlayer(new MockPlayerOneMark(), "Not Player 1");
    Player playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Not Player 2");
    Game game = new Game(playerOne, playerTwo, new MockEmptyMark());
    GameRunner other = new GameRunner(game, clientInterface);

    assertEquals(runner, runner);
    assertEquals(runner, GameRunner.create(playerFactory, gameConfiguration, clientInterface));
    assertEquals(runner.hashCode(),
        (GameRunner.create(playerFactory, gameConfiguration, clientInterface)).hashCode());
    assertNotEquals(runner, other);
    assertNotEquals(game, "not GameRunner");
    assertNotEquals(game, null);
    assertNotEquals(game.hashCode(), other.hashCode());
  }
}
