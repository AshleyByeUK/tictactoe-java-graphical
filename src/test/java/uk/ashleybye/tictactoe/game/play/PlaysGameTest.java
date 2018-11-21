package uk.ashleybye.tictactoe.game.play;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.MockGame;

public class PlaysGameTest {
  private MockTakesPlayersTurn takesPlayersTurn;
  private MockUpdatesGame updatesGame;
  private MockRendersGame rendersGame;
  private MockGame game;
  private List<GameState> gameStates;

  @BeforeEach
  void setUp() {
    takesPlayersTurn = new MockTakesPlayersTurn();
    updatesGame = new MockUpdatesGame();
    rendersGame = new MockRendersGame();
    game = new MockGame();
  }

  @Test
  void testGamePlayStopsWhenGameIsOver() {
    Move move = new MockMove(1);
    takesPlayersTurn.makeMovesInOrder(Arrays.asList(move));
    gameStates = Arrays.asList(GameState.READY, GameState.GAME_OVER);
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
    gameStates = Arrays.asList(GameState.READY, GameState.IN_PROGRESS, GameState.GAME_OVER);
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
