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

public class GameTest {

  private MockPlayer playerOne;
  private MockPlayer playerTwo;
  private Game game;

  @BeforeEach
  void setUp() {
    playerOne = new MockPlayer(new MockPlayerOneMark(), "Player 1");
    playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Player 2");
    game = new Game(playerOne, playerTwo, new MockEmptyMark());
  }

  @Test
  void testNewGameIsCorrectlySetup() {
    Assertions.assertEquals(TestHelpers.generateBoard("- - - - - - - - -"), game.getBoard());
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), game.listOpenPositions());
    assertEquals(playerOne, game.getCurrentPlayer());
    assertEquals(playerTwo, game.getOtherPlayer());
    Assertions.assertEquals(GameState.READY, game.getGameState());
    assertFalse(game.isWon(playerOne));
    assertFalse(game.isWon(playerTwo));
    assertFalse(game.isTied());
  }

  @Test
  void testAfterValidFirstTurnPlayerTwoIsCurrentPlayer() {
    playerOne.setNextPositionToPlay(1);

    game = game.playNextTurn();

    Assertions.assertEquals(TestHelpers.generateBoard("X - - - - - - - -"), game.getBoard());
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

    Assertions.assertEquals(TestHelpers.generateBoard("X O - - - - - - -"), game.getBoard());
    assertEquals(Arrays.asList(3, 4, 5, 6, 7, 8, 9), game.listOpenPositions());
    assertEquals(playerOne, game.getCurrentPlayer());
    assertEquals(playerTwo, game.getOtherPlayer());
    assertEquals(GameState.PLAYING, game.getGameState());
    assertFalse(game.isWon(playerOne));
    assertFalse(game.isWon(playerTwo));
    assertFalse(game.isTied());
  }

  @Test
  void testGameDoesNotChangeWhenChoosingPreviouslyMarkedSquare() {
    Board board = TestHelpers.generateBoard("X - - - - - - - -");
    playerOne.setNextPositionToPlay(1);
    game = new Game(playerOne, playerTwo, board);

    final Game[] gameAfterBadTurn = new Game[1];
    Throwable exception = assertThrows(SquareUnavailable.class,
        () -> gameAfterBadTurn[0] = game.playNextTurn());

    assertEquals("square has already been marked", exception.getMessage());
    assertNull(gameAfterBadTurn[0]);
  }

  @Test
  void testGameDoesNotChangeWhenChoosingInvalidSquareNumber() {
    playerOne.setNextPositionToPlay(999);

    final Game[] gameAfterBadTurn = new Game[1];
    Throwable exception = assertThrows(InvalidSquareNumber.class,
        () -> gameAfterBadTurn[0] = game.playNextTurn());

    assertEquals("invalid square number provided", exception.getMessage());
    assertNull(gameAfterBadTurn[0]);
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
    Board board = TestHelpers.generateBoard("X X X O O - - - -");

    game = new Game(playerOne, playerTwo, board);

    assertEquals(Arrays.asList(6, 7, 8, 9), game.listOpenPositions());
    assertEquals(GameState.GAME_OVER, game.getGameState());
    assertTrue(game.isWon(playerOne));
    assertFalse(game.isWon(playerTwo));
    assertFalse(game.isTied());
  }

  @Test
  void testPlayerTwoHasWon() {
    Board board = TestHelpers.generateBoard("X X - O O O X - -");

    game = new Game(playerOne, playerTwo, board);

    assertEquals(Arrays.asList(3, 8, 9), game.listOpenPositions());
    assertEquals(GameState.GAME_OVER, game.getGameState());
    assertFalse(game.isWon(playerOne));
    assertTrue(game.isWon(playerTwo));
    assertFalse(game.isTied());
  }

  @Test
  void testWinForPlayerOneOnFinalTurnIsNotATie() {
    Board board = TestHelpers.generateBoard("O X X X O X O O -");
    playerOne.setNextPositionToPlay(9);

    game = new Game(playerOne, playerTwo, board);
    game = game.playNextTurn();

    assertEquals(Collections.emptyList(), game.listOpenPositions());
    assertEquals(GameState.GAME_OVER, game.getGameState());
    assertTrue(game.isWon(playerOne));
    assertFalse(game.isWon(playerTwo));
    assertFalse(game.isTied());
  }

  @Test
  void testWinForPlayerTwoOnFinalTurnIsNotATie() {
    Board board = TestHelpers.generateBoard("O O X X O X X X -");
    playerTwo.setNextPositionToPlay(9);

    game = new Game(playerTwo, playerOne, board);
    game = game.playNextTurn();

    assertEquals(Collections.emptyList(), game.listOpenPositions());
    assertEquals(GameState.GAME_OVER, game.getGameState());
    assertFalse(game.isWon(playerOne));
    assertTrue(game.isWon(playerTwo));
    assertFalse(game.isTied());
  }

  @Test
  void testWhenGameIsOverPlayingATurnHasNoEffect() {
    Board board = TestHelpers.generateBoard("O X X X O X O O X");
    playerOne.setNextPositionToPlay(9);

    game = new Game(playerOne, playerTwo, board);
    Game unchangedGame = game.playNextTurn();

    assertEquals(game, unchangedGame);
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

    GameReport report = game.generateGameReport();

    assertEquals(boardRepresentation, report.getCurrentBoard());
    assertEquals(playerOne.getName(), report.getCurrentPlayer());
    assertEquals("", report.getLastPlayer());
    assertEquals("ready", report.getCurrentState());
    assertEquals("", report.getResult());
    assertEquals("", report.getWinner());
  }

  @Test
  void testEquality() {
    Game game = new Game(playerOne, playerTwo, new MockEmptyMark());
    Game otherGame = new Game(playerTwo, playerOne, new MockPlayerOneMark());

    assertEquals(game, game);
    assertEquals(game, new Game(playerOne, playerTwo, new MockEmptyMark()));
    assertEquals(game.hashCode(), (new Game(playerOne, playerTwo, new MockEmptyMark())).hashCode());
    assertNotEquals(game, otherGame);
    assertNotEquals(game, "not a game");
    assertNotEquals(game, null);
    assertNotEquals(game.hashCode(), otherGame.hashCode());
  }
}
