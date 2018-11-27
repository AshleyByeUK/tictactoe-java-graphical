package uk.ashleybye.tictactoe.core.player;

import java.util.Objects;
import uk.ashleybye.tictactoe.core.board.Mark;

public class MockEmptyMark implements Mark {

  private final String mark;

  public MockEmptyMark() {
    this.mark = " ";
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    MockEmptyMark that = (MockEmptyMark) o;
    return Objects.equals(mark, that.mark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mark);
  }

  @Override
  public String toString() {
    return mark;
  }
}
