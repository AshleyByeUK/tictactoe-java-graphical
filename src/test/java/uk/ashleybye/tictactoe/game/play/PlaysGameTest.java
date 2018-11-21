package uk.ashleybye.tictactoe.game.play;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.MockGame;
import uk.ashleybye.tictactoe.game.MockUserInterface;

public class PlaysGameTest {
  private MockTakesPlayersTurn takesPlayersTurn;
  private MockUpdatesGame updatesGame;
  private MockRendersGame rendersGame;
  private MockUserInterface userInterface;
  private MockGame game;
  private List<GameState> gameStates;

  @BeforeEach
  void setUp() {
    takesPlayersTurn = new MockTakesPlayersTurn();
    updatesGame = new MockUpdatesGame();
    rendersGame = new MockRendersGame();
    userInterface = new MockUserInterface();
    game = new MockGame();
  }

  @Test
  void testUserInterfaceIsCorrectlyRegistered() {
    PlaysGame playsGame = new PlaysGame(takesPlayersTurn, updatesGame, rendersGame);
    playsGame.registerUserInterface(userInterface);

    assertTrue(takesPlayersTurn.registeredUserInterface());
    assertTrue(rendersGame.registeredUserInterface());
  }

  @Test
  void testGamePlayStopsWhenGameIsDrawn() {
    Move move = new MockMove(1);
    takesPlayersTurn.makeMovesInOrder(Arrays.asList(move));
    gameStates = Arrays.asList(GameState.IN_PROGRESS, GameState.GAME_OVER_DRAW);
    game.returnGameStatesInOrder(gameStates);

    PlaysGame playsGame = new PlaysGame(takesPlayersTurn, updatesGame, rendersGame);
    playsGame.play(game);

    assertEquals(1, takesPlayersTurn.getNumberOfTimesTakeTurnHasBeenCalled());
    assertTrue(takesPlayersTurn.wasLastCalledWith(game));
    assertEquals(1, updatesGame.getNumberOfTimesApplyHasBeenCalled());
    assertTrue(updatesGame.wasLastCalledWith(move, game));
    assertEquals(1, rendersGame.getNumberOfTimesRenderHasBeenCalled());
    assertTrue(rendersGame.wasLastCalledWith(game));
  }

  @Test
  void testGamePlayStopsWhenGameIsWonByPlayerOne() {
    Move move = new MockMove(1);
    takesPlayersTurn.makeMovesInOrder(Arrays.asList(move));
    gameStates = Arrays.asList(GameState.IN_PROGRESS, GameState.GAME_OVER_WIN_PLAYER_ONE);
    game.returnGameStatesInOrder(gameStates);

    PlaysGame playsGame = new PlaysGame(takesPlayersTurn, updatesGame, rendersGame);
    playsGame.play(game);

    assertEquals(1, takesPlayersTurn.getNumberOfTimesTakeTurnHasBeenCalled());
    assertTrue(takesPlayersTurn.wasLastCalledWith(game));
    assertEquals(1, updatesGame.getNumberOfTimesApplyHasBeenCalled());
    assertTrue(updatesGame.wasLastCalledWith(move, game));
    assertEquals(1, rendersGame.getNumberOfTimesRenderHasBeenCalled());
    assertTrue(rendersGame.wasLastCalledWith(game));
  }

  @Test
  void testGamePlayStopsWhenGameIsWonByPlayerTwo() {
    Move move = new MockMove(1);
    takesPlayersTurn.makeMovesInOrder(Arrays.asList(move));
    gameStates = Arrays.asList(GameState.IN_PROGRESS, GameState.GAME_OVER_WIN_PLAYER_TWO);
    game.returnGameStatesInOrder(gameStates);

    PlaysGame playsGame = new PlaysGame(takesPlayersTurn, updatesGame, rendersGame);
    playsGame.play(game);

    assertEquals(1, takesPlayersTurn.getNumberOfTimesTakeTurnHasBeenCalled());
    assertTrue(takesPlayersTurn.wasLastCalledWith(game));
    assertEquals(1, updatesGame.getNumberOfTimesApplyHasBeenCalled());
    assertTrue(updatesGame.wasLastCalledWith(move, game));
    assertEquals(1, rendersGame.getNumberOfTimesRenderHasBeenCalled());
    assertTrue(rendersGame.wasLastCalledWith(game));
  }

  @Test
  void testGamePlayContinuesUntilGameIsOver() {
    Move moveOne = new MockMove(1);
    Move moveTwo = new MockMove(2);
    takesPlayersTurn.makeMovesInOrder(Arrays.asList(moveOne, moveTwo));
    gameStates = Arrays.asList(GameState.IN_PROGRESS, GameState.IN_PROGRESS, GameState.GAME_OVER_DRAW);
    game.returnGameStatesInOrder(gameStates);

    PlaysGame playsGame = new PlaysGame(takesPlayersTurn, updatesGame, rendersGame);
    playsGame.play(game);

    assertEquals(2, takesPlayersTurn.getNumberOfTimesTakeTurnHasBeenCalled());
    assertTrue(takesPlayersTurn.wasLastCalledWith(game));
    assertEquals(2, updatesGame.getNumberOfTimesApplyHasBeenCalled());
    assertTrue(updatesGame.wasLastCalledWith(moveTwo, game));
    assertEquals(2, rendersGame.getNumberOfTimesRenderHasBeenCalled());
    assertTrue(rendersGame.wasLastCalledWith(game));
  }
}
