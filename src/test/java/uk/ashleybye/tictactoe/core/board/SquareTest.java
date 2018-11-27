package uk.ashleybye.tictactoe.core.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

class SquareTest {

  private Mark emptyMark;
  private Mark playerOneMark;
  private Mark playerTwoMark;

  @BeforeEach
  void setUp() {
    emptyMark = new MockEmptyMark();
    playerOneMark = new MockPlayerOneMark();
    playerTwoMark = new MockPlayerTwoMark();
  }

  @Test
  void testEmptySquareIsNotMarked() {
    Square unmarkedSquare = new Square(1, emptyMark);

    assertFalse(unmarkedSquare.isMarked());
  }

  @Test
  void testMarkedSquareIsMarked() {
    Square markedSquare = new Square(1, playerOneMark);

    assertTrue(markedSquare.isMarked());
  }

  @Test
  void testEmptySquareDoesNotEqualMarkedSquare() {
    Square unmarked = new Square(1, emptyMark);
    Square marked = new Square(1, playerOneMark);

    assertNotEquals(unmarked, marked);
  }

  @Test
  void testUnmarkedSquaresInSamePositionAreEqual() {
    Square squareOne = new Square(1, emptyMark);
    Square squareTwo = new Square(1, emptyMark);

    assertEquals(squareOne, squareTwo);
  }

  @Test
  void testSimilarlyMarkedSquaresInSamePositionAreEqual() {
    Square squareOne = new Square(1, playerOneMark);
    Square squareTwo = new Square(1, playerOneMark);

    assertEquals(squareOne, squareTwo);
  }

  @Test
  void testDifferentlyMarkedSquaresInSamePositionAreNotEqual() {
    Square squareOne = new Square(1, playerOneMark);
    Square squareTwo = new Square(1, playerTwoMark);

    assertNotEquals(squareOne, squareTwo);
  }

  @Test
  void testUnmarkedSquaresInDifferentPositionsAreEqual() {
    Square squareOne = new Square(1, emptyMark);
    Square squareTwo = new Square(2, emptyMark);

    assertEquals(squareOne, squareTwo);
  }

  @Test
  void testSimilarlyMarkedSquaresInDifferentPositionsAreEqual() {
    Square squareOne = new Square(1, playerOneMark);
    Square squareTwo = new Square(2, playerOneMark);

    assertEquals(squareOne, squareTwo);
  }

  @Test
  void testDifferentlyMarkedSquaresInDifferentPositionsAreNotEqual() {
    Square squareOne = new Square(1, playerOneMark);
    Square squareTwo = new Square(2, playerTwoMark);

    assertNotEquals(squareOne, squareTwo);
  }

  @Test
  void testEquality() {
    Square square = new Square(1, emptyMark);
    Square otherSquare = new Square(1, playerOneMark);

    assertEquals(square, square);
    assertEquals(square, new Square(1, new MockEmptyMark()));
    assertEquals(square.hashCode(), (new Square(1, new MockEmptyMark())).hashCode());
    assertNotEquals(square, otherSquare);
    assertNotEquals(square, "not a square");
    assertNotEquals(square, null);
    assertNotEquals(square.hashCode(), otherSquare.hashCode());
  }
}
