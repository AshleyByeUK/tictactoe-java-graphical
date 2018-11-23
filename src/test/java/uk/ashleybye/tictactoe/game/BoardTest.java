package uk.ashleybye.tictactoe.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.Board.InvalidSquareNumber;
import uk.ashleybye.tictactoe.game.Board.SquareUnavailable;

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
}
