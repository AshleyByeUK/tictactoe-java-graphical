package uk.ashleybye.tictactoe.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.console.MockGameConsole;
import uk.ashleybye.tictactoe.console.MockPlayerFactory;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

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

  @Test
  void testCreatesNewGame() {
    PlayerFactory playerFactory = new MockPlayerFactory();
    GameConfiguration gameConfiguration = new MockGameConfiguration();
    UserInterface userInterface = new MockGameConsole();

    Player playerOne = new MockPlayer(new MockPlayerOneMark(), "Player 1");
    Player playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Player 2");
    Game game = new Game(playerOne, playerTwo, new MockEmptyMark());
    TicTacToe expected = new TicTacToe(game, userInterface);

    TicTacToe actual = TicTacToe.create(playerFactory, gameConfiguration, userInterface);

    assertEquals(expected, actual);
  }

  @Test
  void testEquality() {
    PlayerFactory playerFactory = new MockPlayerFactory();
    GameConfiguration gameConfiguration = new MockGameConfiguration();
    UserInterface userInterface = new MockGameConsole();
    TicTacToe ticTacToe = TicTacToe.create(playerFactory, gameConfiguration, userInterface);

    Player playerOne = new MockPlayer(new MockPlayerOneMark(), "Not Player 1");
    Player playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Not Player 2");
    Game game = new Game(playerOne, playerTwo, new MockEmptyMark());
    TicTacToe other = new TicTacToe(game, userInterface);

    assertEquals(ticTacToe, ticTacToe);
    assertEquals(ticTacToe, TicTacToe.create(playerFactory, gameConfiguration, userInterface));
    assertEquals(ticTacToe.hashCode(), (TicTacToe.create(playerFactory, gameConfiguration, userInterface)).hashCode());
    assertNotEquals(ticTacToe, other);
    assertNotEquals(game, "not TicTacToe");
    assertNotEquals(game, null);
    assertNotEquals(game.hashCode(), other.hashCode());
  }
}
