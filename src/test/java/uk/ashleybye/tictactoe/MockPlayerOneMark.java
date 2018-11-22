package uk.ashleybye.tictactoe;

import java.util.Objects;

public class MockPlayerOneMark implements Mark {

  private final String mark;

  public MockPlayerOneMark() {
    this.mark = "X";
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    MockPlayerOneMark that = (MockPlayerOneMark) o;
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
