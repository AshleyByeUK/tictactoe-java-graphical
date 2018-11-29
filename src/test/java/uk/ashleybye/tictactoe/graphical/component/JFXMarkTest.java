package uk.ashleybye.tictactoe.graphical.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JFXMarkTest extends JFXTest {

  @Test
  void testEmptyMarkIsEmpty() {
    JFXMark mark = new JFXMark(" ");

    assertEquals(" ", mark.getText());
    assertTrue(mark.isEmpty());
  }

  @Test
  void testNonEmptyMarkIsNotEmpty() {
    JFXMark mark = new JFXMark("X");

    assertEquals("X", mark.getText());
    assertFalse(mark.isEmpty());
  }

  @Test
  void testEquality() {
    JFXMark mark = new JFXMark("X");
    JFXMark empty = new JFXMark(" ");

    assertEquals(mark, mark);
    assertEquals(mark, new JFXMark("X"));
    assertEquals(mark.hashCode(), (new JFXMark("X").hashCode()));
    assertNotEquals(mark, empty);
    assertNotEquals(mark, "not a mark");
    assertNotEquals(mark, null);
    assertNotEquals(mark.hashCode(), empty.hashCode());
  }
}
