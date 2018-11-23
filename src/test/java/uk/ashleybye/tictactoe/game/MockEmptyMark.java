package uk.ashleybye.tictactoe.game;

import java.util.Objects;

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
}
