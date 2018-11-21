package uk.ashleybye.tictactoe.game.play;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.MockGame;

class GeneratesGameOverviewTest {
  private static final int EMPTY = 0;
  private static final int PLAYER_1 = 1;
  private static final int PLAYER_2 = 2;
  private static final List<Integer> CURRENT_BOARD = Arrays
      .asList(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, PLAYER_1, PLAYER_2, PLAYER_1, PLAYER_2);
  private List<Integer> OPEN_POSITIONS = Arrays.asList(1, 2, 3, 4, 5);

  @Test
  void testParsesGameIntoCorrectGAmeOverview() {
    MockGame game = new MockGame();
    game.returnCurrentPlayer(PLAYER_1);
    game.returnCurrentBoard(CURRENT_BOARD);
    game.returnOpenPositions(OPEN_POSITIONS);
    game.returnGameStatesInOrder(Arrays.asList(GameState.IN_PROGRESS));
    game.returnLastMoveValid(true);

    GeneratesGameOverview generatesGameOverview = new GeneratesGameOverview();
    GameOverview gameOverview = generatesGameOverview.parse(game);

    assertEquals(CURRENT_BOARD, gameOverview.currentBoard);
    assertEquals(OPEN_POSITIONS, gameOverview.openPositions);
    assertEquals("in_progress", gameOverview.currentGameState);
    assertEquals(Integer.valueOf(PLAYER_1), gameOverview.currentPlayer);
    assertEquals("valid", gameOverview.lastMoveValid);
  }
}
