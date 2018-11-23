package uk.ashleybye.tictactoe.console;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.Mark;

class ConsoleMarkTest {

  @Test
  void testReturnsCorrectStringRepresentation() {
    Mark mark = new ConsoleMark("X");

    assertEquals("X", mark.toString());
  }

  @Test
  void testMarksWithMultipleCharactersUseFirstCharacter() {
    Mark mark = new ConsoleMark("ABC");

    assertEquals("A", mark.toString());
  }

  @Test
  void testMarkWithSingleSpaceIsValid() {
    Mark mark = new ConsoleMark("");

    assertEquals(ConsoleMark.emptyMark().toString(), mark.toString());
  }

  @Test
  void testRemovesLeadingAndTrailingWhitespaceFromNonEmptyMarks() {
    Mark mark = new ConsoleMark(" ABC ");

    assertEquals("A", mark.toString());
  }

  @Test
  void testMarksWithOnlyMultipleSpacesAreValidForEmptyMark() {
    Mark mark = new ConsoleMark("   ");

    assertEquals(ConsoleMark.emptyMark().toString(), mark.toString());
  }

  @Test
  void testLowercaseMarksAreConvertedToUppercase() {
    Mark mark = new ConsoleMark(" abc ");

    assertEquals("A", mark.toString());
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
}
