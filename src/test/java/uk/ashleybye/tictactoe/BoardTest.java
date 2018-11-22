package uk.ashleybye.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.Board.SquareUnavailable;

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
}
