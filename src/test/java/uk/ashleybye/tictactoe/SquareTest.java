package uk.ashleybye.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SquareTest {

  @Test
  void testEmptySquareIsNotMarked() {
    Mark emptyMark = new MockEmptyMark();
    Square unmarkedSquare = new Square(1, emptyMark);

    assertFalse(unmarkedSquare.isMarked());
  }

  @Test
  void testMarkedSquareIsMarked() {
    Mark mark = new MockPlayerOneMark();
    Square markedSquare = new Square(1, mark);

    assertTrue(markedSquare.isMarked());
  }

  @Test
  void testEmptySquareDoesNotEqualMarkedSquare() {
    Mark emptyMark = new MockEmptyMark();
    Mark playerOneMark = new MockPlayerOneMark();

    Square unmarked = new Square(1, emptyMark);
    Square marked = new Square(1, playerOneMark);

    assertNotEquals(unmarked, marked);
  }

  @Test
  void testUnmarkedSquaresInSamePositionAreEqual() {
    Mark emptyMark = new MockEmptyMark();

    Square squareOne = new Square(1, emptyMark);
    Square squareTwo = new Square(1, emptyMark);

    assertEquals(squareOne, squareTwo);
  }

  @Test
  void testSimilarlyMarkedSquaresInSamePositionAreEqual() {
    Mark playerOneMark = new MockPlayerOneMark();

    Square squareOne = new Square(1, playerOneMark);
    Square squareTwo = new Square(1, playerOneMark);

    assertEquals(squareOne, squareTwo);
  }

  @Test
  void testDifferentlyMarkedSquaresInSamePositionAreNotEqual() {
    Mark playerOneMark = new MockPlayerOneMark();
    Mark playerTwoMark = new MockPlayerTwoMark();

    Square squareOne = new Square(1, playerOneMark);
    Square squareTwo = new Square(1, playerTwoMark);

    assertNotEquals(squareOne, squareTwo);
  }

  @Test
  void testUnmarkedSquaresInDifferentPositionsAreEqual() {
    Mark emptyMark = new MockEmptyMark();

    Square squareOne = new Square(1, emptyMark);
    Square squareTwo = new Square(2, emptyMark);

    assertEquals(squareOne, squareTwo);
  }

  @Test
  void testSimilarlyMarkedSquaresInDifferentPositionsAreEqual() {
    Mark playerOneMark = new MockPlayerOneMark();

    Square squareOne = new Square(1, playerOneMark);
    Square squareTwo = new Square(2, playerOneMark);

    assertEquals(squareOne, squareTwo);
  }

  @Test
  void testDifferentlyMarkedSquaresInDifferentPositionsAreNotEqual() {
    Mark playerOneMark = new MockPlayerOneMark();
    Mark playerTwoMark = new MockPlayerTwoMark();

    Square squareOne = new Square(1, playerOneMark);
    Square squareTwo = new Square(2, playerTwoMark);

    assertNotEquals(squareOne, squareTwo);
  }
}
