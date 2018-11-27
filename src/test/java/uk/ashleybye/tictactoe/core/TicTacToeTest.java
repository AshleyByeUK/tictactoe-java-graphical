package uk.ashleybye.tictactoe.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.TestHelpers;
import uk.ashleybye.tictactoe.core.board.Board;
import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;
import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

public class TicTacToeTest {

  private MockPlayer playerOne;
  private MockPlayer playerTwo;
  private TicTacToe ticTacToe;

  @BeforeEach
  void setUp() {
    playerOne = new MockPlayer(new MockPlayerOneMark(), "Player 1");
    playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Player 2");
    ticTacToe = new TicTacToe(playerOne, playerTwo, new MockEmptyMark());
  }

  @Test
  void testNewGameIsCorrectlySetup() {
    Assertions.assertEquals(TestHelpers.generateBoard("- - - - - - - - -"), ticTacToe.getBoard());
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), ticTacToe.listOpenPositions());
    assertEquals(playerOne, ticTacToe.getCurrentPlayer());
    assertEquals(playerTwo, ticTacToe.getOtherPlayer());
    Assertions.assertEquals(GameState.READY, ticTacToe.getGameState());
    assertFalse(ticTacToe.isWon(playerOne));
    assertFalse(ticTacToe.isWon(playerTwo));
    assertFalse(ticTacToe.isTied());
  }

  @Test
  void testAfterValidFirstTurnPlayerTwoIsCurrentPlayer() {
    playerOne.setNextPositionToPlay(1);

    ticTacToe = ticTacToe.playNextTurn();

    Assertions.assertEquals(TestHelpers.generateBoard("X - - - - - - - -"), ticTacToe.getBoard());
    assertEquals(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9), ticTacToe.listOpenPositions());
    assertEquals(playerTwo, ticTacToe.getCurrentPlayer());
    assertEquals(playerOne, ticTacToe.getOtherPlayer());
    assertEquals(GameState.PLAYING, ticTacToe.getGameState());
    assertFalse(ticTacToe.isWon(playerOne));
    assertFalse(ticTacToe.isWon(playerTwo));
    assertFalse(ticTacToe.isTied());
  }

  @Test
  void testAfterValidPlayerOneAndPlayerTwoTurnsPlayerOneIsCurrentPlayer() {
    playerOne.setNextPositionToPlay(1);
    playerTwo.setNextPositionToPlay(2);

    ticTacToe = ticTacToe.playNextTurn();
    ticTacToe = ticTacToe.playNextTurn();

    Assertions.assertEquals(TestHelpers.generateBoard("X O - - - - - - -"), ticTacToe.getBoard());
    assertEquals(Arrays.asList(3, 4, 5, 6, 7, 8, 9), ticTacToe.listOpenPositions());
    assertEquals(playerOne, ticTacToe.getCurrentPlayer());
    assertEquals(playerTwo, ticTacToe.getOtherPlayer());
    assertEquals(GameState.PLAYING, ticTacToe.getGameState());
    assertFalse(ticTacToe.isWon(playerOne));
    assertFalse(ticTacToe.isWon(playerTwo));
    assertFalse(ticTacToe.isTied());
  }

  @Test
  void testGameDoesNotChangeWhenChoosingPreviouslyMarkedSquare() {
    Board board = TestHelpers.generateBoard("X - - - - - - - -");
    playerOne.setNextPositionToPlay(1);
    ticTacToe = new TicTacToe(playerOne, playerTwo, board);

    final TicTacToe[] ticTacToeAfterBadTurn = new TicTacToe[1];
    Throwable exception = assertThrows(SquareUnavailable.class, () -> ticTacToeAfterBadTurn[0] = ticTacToe.playNextTurn());

    assertEquals("square has already been marked", exception.getMessage());
    assertNull(ticTacToeAfterBadTurn[0]);
  }

  @Test
  void testGameDoesNotChangeWhenChoosingInvalidSquareNumber() {
    playerOne.setNextPositionToPlay(999);

    final TicTacToe[] ticTacToeAfterBadTurn = new TicTacToe[1];
    Throwable exception = assertThrows(InvalidSquareNumber.class, () -> ticTacToeAfterBadTurn[0] = ticTacToe.playNextTurn());

    assertEquals("invalid square number provided", exception.getMessage());
    assertNull(ticTacToeAfterBadTurn[0]);
  }

  @Test
  void testFullBoardNoWinnerResultsInTiedGame() {
    Board board = TestHelpers.generateBoard("X X O O X X X O O");

    ticTacToe = new TicTacToe(playerOne, playerTwo, board);

    assertEquals(Collections.emptyList(), ticTacToe.listOpenPositions());
    assertEquals(GameState.GAME_OVER, ticTacToe.getGameState());
    assertFalse(ticTacToe.isWon(playerOne));
    assertFalse(ticTacToe.isWon(playerTwo));
    assertTrue(ticTacToe.isTied());
  }

  @Test
  void testPlayerOneHasWon() {
    Board board = TestHelpers.generateBoard("X X X O O - - - -");

    ticTacToe = new TicTacToe(playerOne, playerTwo, board);

    assertEquals(Arrays.asList(6, 7, 8, 9), ticTacToe.listOpenPositions());
    assertEquals(GameState.GAME_OVER, ticTacToe.getGameState());
    assertTrue(ticTacToe.isWon(playerOne));
    assertFalse(ticTacToe.isWon(playerTwo));
    assertFalse(ticTacToe.isTied());
  }

  @Test
  void testPlayerTwoHasWon() {
    Board board = TestHelpers.generateBoard("X X - O O O X - -");

    ticTacToe = new TicTacToe(playerOne, playerTwo, board);

    assertEquals(Arrays.asList(3, 8, 9), ticTacToe.listOpenPositions());
    assertEquals(GameState.GAME_OVER, ticTacToe.getGameState());
    assertFalse(ticTacToe.isWon(playerOne));
    assertTrue(ticTacToe.isWon(playerTwo));
    assertFalse(ticTacToe.isTied());
  }

  @Test
  void testWinForPlayerOneOnFinalTurnIsNotATie() {
    Board board = TestHelpers.generateBoard("O X X X O X O O -");
    playerOne.setNextPositionToPlay(9);

    ticTacToe = new TicTacToe(playerOne, playerTwo, board);
    ticTacToe = ticTacToe.playNextTurn();

    assertEquals(Collections.emptyList(), ticTacToe.listOpenPositions());
    assertEquals(GameState.GAME_OVER, ticTacToe.getGameState());
    assertTrue(ticTacToe.isWon(playerOne));
    assertFalse(ticTacToe.isWon(playerTwo));
    assertFalse(ticTacToe.isTied());
  }

  @Test
  void testWinForPlayerTwoOnFinalTurnIsNotATie() {
    Board board = TestHelpers.generateBoard("O O X X O X X X -");
    playerTwo.setNextPositionToPlay(9);

    ticTacToe = new TicTacToe(playerTwo, playerOne, board);
    ticTacToe = ticTacToe.playNextTurn();

    assertEquals(Collections.emptyList(), ticTacToe.listOpenPositions());
    assertEquals(GameState.GAME_OVER, ticTacToe.getGameState());
    assertFalse(ticTacToe.isWon(playerOne));
    assertTrue(ticTacToe.isWon(playerTwo));
    assertFalse(ticTacToe.isTied());
  }

  @Test
  void testWhenGameIsOverPlayingATurnHasNoEffect() {
    Board board = TestHelpers.generateBoard("O X X X O X O O X");
    playerOne.setNextPositionToPlay(9);

    ticTacToe = new TicTacToe(playerOne, playerTwo, board);
    TicTacToe unchangedTicTacToe = ticTacToe.playNextTurn();

    assertEquals(ticTacToe, unchangedTicTacToe);
  }

  @Test
  void testGeneratesGameReportWithCorrectDetails() {
    Mark empty = new MockEmptyMark();
    Map<Integer, Mark> boardRepresentation = new HashMap<>();
    boardRepresentation.put(1, empty);
    boardRepresentation.put(2, empty);
    boardRepresentation.put(3, empty);
    boardRepresentation.put(4, empty);
    boardRepresentation.put(5, empty);
    boardRepresentation.put(6, empty);
    boardRepresentation.put(7, empty);
    boardRepresentation.put(8, empty);
    boardRepresentation.put(9, empty);

    GameReport report = ticTacToe.generateGameReport();

    assertEquals(boardRepresentation, report.getCurrentBoard());
    assertEquals(playerOne.getName(), report.getCurrentPlayer());
    assertEquals("", report.getLastPlayer());
    assertEquals("ready", report.getCurrentState());
    assertEquals("", report.getResult());
    assertEquals("", report.getWinner());
  }

  @Test
  void testEquality() {
    TicTacToe ticTacToe = new TicTacToe(playerOne, playerTwo, new MockEmptyMark());
    TicTacToe otherTicTacToe = new TicTacToe(playerTwo, playerOne, new MockPlayerOneMark());

    assertEquals(ticTacToe, ticTacToe);
    assertEquals(ticTacToe, new TicTacToe(playerOne, playerTwo, new MockEmptyMark()));
    assertEquals(ticTacToe.hashCode(), (new TicTacToe(playerOne, playerTwo, new MockEmptyMark())).hashCode());
    assertNotEquals(ticTacToe, otherTicTacToe);
    assertNotEquals(ticTacToe, "not a ticTacToe");
    assertNotEquals(ticTacToe, null);
    assertNotEquals(ticTacToe.hashCode(), otherTicTacToe.hashCode());
  }
}
