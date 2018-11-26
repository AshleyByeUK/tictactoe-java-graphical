package uk.ashleybye.tictactoe.core.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;
import uk.ashleybye.tictactoe.core.Mark;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.TestHelpers;

class BoardTest {

  @Test
  void testBoardWithNoMarkedSquaresHasNineUnmarkedSquares() {
    Board board = new Board(new MockEmptyMark());

    assertEquals(9, board.listUnmarkedSquares().size());
  }

  @Test
  void testBoardWithOneMarkedSquareHasEightUnmarkedSquares() {
    Board board = TestHelpers.generateBoard("X - - - - - - - -");

    assertEquals(8, board.listUnmarkedSquares().size());
  }

  @Test
  void testSquaresCannotBeMarkedOnFullBoard() {
    Board board = TestHelpers.generateBoard("X O X O X O X O X");
    Mark mark = new MockPlayerOneMark();

    Throwable exception = assertThrows(SquareUnavailable.class, () -> board.markSquare(1, mark));
    assertEquals("square has already been marked", exception.getMessage());
  }

  @Test
  void testSquaresCannotBeMarkedMoreThanOnceBySamePlayer() {
    Board board = TestHelpers.generateBoard(("X - - - - - - - -"));
    Mark mark = new MockPlayerOneMark();

    Throwable exception = assertThrows(SquareUnavailable.class, () -> board.markSquare(1, mark));
    assertEquals("square has already been marked", exception.getMessage());
  }

  @Test
  void testSquaresCannotBeMarkedMoreThanOnceByDifferentPlayer() {
    Board board = TestHelpers.generateBoard(("O - - - - - - - -"));
    Mark mark = new MockPlayerOneMark();

    Throwable exception = assertThrows(SquareUnavailable.class, () -> board.markSquare(1, mark));
    assertEquals("square has already been marked", exception.getMessage());
  }

  @Test
  void testSquaresGreaterThanBoardSizeCannotBeMarked() {
    Board board = TestHelpers.generateBoard(("O - - - - - - - -"));
    Mark mark = new MockPlayerOneMark();

    Throwable exception = assertThrows(InvalidSquareNumber.class, () -> board.markSquare(10, mark));
    assertEquals("invalid square number provided", exception.getMessage());
  }

  @Test
  void testSquaresLessThanBoardSizeCannotBeMarked() {
    Board board = TestHelpers.generateBoard(("O - - - - - - - -"));
    Mark mark = new MockPlayerOneMark();

    Throwable exception = assertThrows(InvalidSquareNumber.class, () -> board.markSquare(0, mark));
    assertEquals("invalid square number provided", exception.getMessage());
  }

  @Test
  void testEquality() {
    Board board = new Board(new MockEmptyMark());
    Board otherBoard = new Board(new MockPlayerOneMark());

    assertEquals(board, board);
    assertEquals(board, new Board(new MockEmptyMark()));
    assertEquals(board.hashCode(), (new Board(new MockEmptyMark())).hashCode());
    assertNotEquals(board, otherBoard);
    assertNotEquals(board, "not a board");
    assertNotEquals(board, null);
    assertNotEquals(board.hashCode(), otherBoard.hashCode());
  }
}
