package uk.ashleybye.tictactoe.core.player;

import java.util.Objects;
import uk.ashleybye.tictactoe.core.board.Mark;

public class MockPlayerTwoMark implements Mark {

  private final String mark;

  public MockPlayerTwoMark() {
    this.mark = "O";
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MockPlayerTwoMark that = (MockPlayerTwoMark) o;
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
