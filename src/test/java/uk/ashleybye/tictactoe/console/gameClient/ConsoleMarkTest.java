package uk.ashleybye.tictactoe.console.gameClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.ashleybye.tictactoe.TestHelpers.colourisedMark;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.TestHelpers;
import uk.ashleybye.tictactoe.console.gameClient.ConsoleMark.ConsoleColour;
import uk.ashleybye.tictactoe.core.board.Mark;

class ConsoleMarkTest {

  @Test
  void testReturnsCorrectStringRepresentation() {
    Mark mark = new ConsoleMark("X");

    assertEquals(colourisedMark("X", "[37m", "[37m"), mark.toString());
  }

  @Test
  void testMarksWithMultipleCharactersUseFirstCharacter() {
    Mark mark = new ConsoleMark("ABC");

    assertEquals(colourisedMark("A", "[37m", "[37m"), mark.toString());
  }

  @Test
  void testMarkWithSingleSpaceIsValid() {
    Mark mark = new ConsoleMark("");

    assertEquals(ConsoleMark.emptyMark().toString(), mark.toString());
  }

  @Test
  void testRemovesLeadingAndTrailingWhitespaceFromNonEmptyMarks() {
    Mark mark = new ConsoleMark(" ABC ");

    assertEquals(colourisedMark("A", "[37m", "[37m"), mark.toString());
  }

  @Test
  void testMarksWithOnlyMultipleSpacesAreValidForEmptyMark() {
    Mark mark = new ConsoleMark("   ");

    assertEquals(colourisedMark(" ", "[37m", "[37m"), mark.toString());
  }

  @Test
  void testLowercaseMarksAreConvertedToUppercase() {
    Mark mark = new ConsoleMark(" abc ");

    assertEquals(colourisedMark("A", "[37m", "[37m"), mark.toString());
  }

  @Test
  void testEmptyMarkIsEmpty() {
    Mark mark = new ConsoleMark("");

    assertTrue(mark.isEmpty());
  }

  @Test
  void testNonEmptyMarkIsNotEmpty() {
    Mark mark = new ConsoleMark("X");

    assertFalse(mark.isEmpty());
  }

  @Test
  void testMarkCanBeCyan() {
    Mark mark = new ConsoleMark("X", ConsoleColour.CYAN);

    assertEquals(TestHelpers.colourisedMark("X", "[36m", "[37m"), mark.toString());
  }

  @Test
  void testMarkCanBeYellow() {
    Mark mark = new ConsoleMark("X", ConsoleColour.YELLOW);

    assertEquals(TestHelpers.colourisedMark("X", "[33m", "[37m"), mark.toString());
  }

  @Test
  void testMarkCanBeWhite() {
    Mark mark = new ConsoleMark("X", ConsoleColour.WHITE);

    assertEquals(TestHelpers.colourisedMark("X", "[37m", "[37m"), mark.toString());
  }

  @Test
  void testEquality() {
    Mark mark = new ConsoleMark("X");
    Mark otherMark = new ConsoleMark("O");

    assertEquals(mark, mark);
    assertEquals(mark, new ConsoleMark("X"));
    assertEquals(mark.hashCode(), (new ConsoleMark("X")).hashCode());
    assertNotEquals(mark, otherMark);
    assertNotEquals(mark, "not a mark");
    assertNotEquals(mark, null);
    assertNotEquals(mark.hashCode(), otherMark.hashCode());
  }
}
