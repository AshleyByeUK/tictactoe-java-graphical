package uk.ashleybye.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

  private MockPlayer playerOne;
  private MockPlayer playerTwo;
  private Game game;

  @BeforeEach
  void setUp() {
    playerOne = new MockPlayer(new MockPlayerOneMark());
    playerTwo = new MockPlayer(new MockPlayerTwoMark());
    game = new Game(playerOne, playerTwo, new MockEmptyMark());
  }

  @Test
  void testNewGameIsCorrectlySetup() {
    assertEquals(TestHelpers.generateBoard("- - - - - - - - -"), game.getBoard());
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), game.listOpenPositions());
    assertEquals(playerOne, game.getCurrentPlayer());
    assertEquals(playerTwo, game.getOtherPlayer());
    assertEquals(GameState.READY, game.getGameState());
    assertFalse(game.isWon(playerOne));
    assertFalse(game.isWon(playerTwo));
    assertFalse(game.isTied());
  }

  @Test
  void testAfterValidFirstTurnPlayerTwoIsCurrentPlayer() {
    playerOne.setNextPositionToPlay(1);

    game = game.playNextTurn();

    assertEquals(TestHelpers.generateBoard("X - - - - - - - -"), game.getBoard());
    assertEquals(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9), game.listOpenPositions());
    assertEquals(playerTwo, game.getCurrentPlayer());
    assertEquals(playerOne, game.getOtherPlayer());
    assertEquals(GameState.PLAYING, game.getGameState());
    assertFalse(game.isWon(playerOne));
    assertFalse(game.isWon(playerTwo));
    assertFalse(game.isTied());
  }

  @Test
  void testAfterValidPlayerOneAndPlayerTwoTurnsPlayerOneIsCurrentPlayer() {
    playerOne.setNextPositionToPlay(1);
    playerTwo.setNextPositionToPlay(2);

    game = game.playNextTurn();
    game = game.playNextTurn();

    assertEquals(TestHelpers.generateBoard("X O - - - - - - -"), game.getBoard());
    assertEquals(Arrays.asList(3, 4, 5, 6, 7, 8, 9), game.listOpenPositions());
    assertEquals(playerTwo, game.getCurrentPlayer());
    assertEquals(playerOne, game.getOtherPlayer());
    assertEquals(GameState.PLAYING, game.getGameState());
    assertFalse(game.isWon(playerOne));
    assertFalse(game.isWon(playerTwo));
    assertFalse(game.isTied());
  }

  @Test
  void testFullBoardNoWinnerResultsInTiedGame() {
    Board board = TestHelpers.generateBoard("X X O O X X X O O");

    game = new Game(playerOne, playerTwo, board);

    assertEquals(Collections.emptyList(), game.listOpenPositions());
    assertEquals(GameState.GAME_OVER, game.getGameState());
    assertFalse(game.isWon(playerOne));
    assertFalse(game.isWon(playerTwo));
    assertTrue(game.isTied());
  }

  @Test
  void testPlayerOneHasWon() {
    
  }
}
