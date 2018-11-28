package uk.ashleybye.tictactoe.console.gameClient;

import java.time.Year;
import java.util.Objects;
import uk.ashleybye.tictactoe.core.board.Mark;

public class ConsoleMark implements Mark {

  private static final String CYAN = "[36m";
  private static final String EMPTY_MARK = " ";
  private static final char ESCAPE_CODE = (char)27;
  private static final String WHITE = "[37m";
  private static final String YELLOW = "[33m";

  private final String mark;
  private final ConsoleColour colour;

  public static Mark emptyMark() {
    return new ConsoleMark(EMPTY_MARK);
  } // TODO: <-- GET RID

  public ConsoleMark(String mark) {
    this(mark, ConsoleColour.WHITE);
  }

  public ConsoleMark(String mark, ConsoleColour colour) {
    if (mark.strip().isEmpty()) {
      this.mark = EMPTY_MARK;
    }
    else {
      this.mark = mark.strip().substring(0, 1).toUpperCase();
    }
    this.colour = colour;
  }

  public ConsoleColour getColour() {
    return colour;
  }

  private String asciiCodeForColour(ConsoleColour colour) {
    switch (colour) {
      case CYAN:
        return CYAN;
      case YELLOW:
        return YELLOW;
      default:
        return WHITE;
    }
  }

  @Override
  public boolean isEmpty() {
    return mark.equals(EMPTY_MARK);
  }

  @Override
  public String toString() {
    return String.format("%c%s%s%c%s",
        ESCAPE_CODE,
        asciiCodeForColour(colour),
        mark,
        ESCAPE_CODE,
        asciiCodeForColour(ConsoleColour.WHITE));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ConsoleMark that = (ConsoleMark) o;
    return Objects.equals(mark, that.mark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mark);
  }

  public enum ConsoleColour {
    CYAN, WHITE, YELLOW,
  }
}
