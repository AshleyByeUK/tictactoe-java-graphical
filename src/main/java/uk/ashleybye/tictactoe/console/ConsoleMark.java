package uk.ashleybye.tictactoe.console;

import uk.ashleybye.tictactoe.core.Mark;

public class ConsoleMark implements Mark {

  private static final String EMPTY_MARK = " ";

  private final String mark;

  public ConsoleMark(String mark) {
    if (mark.strip().isEmpty())
      this.mark = EMPTY_MARK;
    else
      this.mark = mark.strip().substring(0, 1).toUpperCase();
  }

  public static Mark emptyMark() {
    return new ConsoleMark(EMPTY_MARK);
  }

  @Override
  public boolean isEmpty() {
    return mark.equals(EMPTY_MARK);
  }

  @Override
  public String toString() {
    return mark;
  }
}
