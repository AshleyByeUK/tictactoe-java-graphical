package uk.ashleybye.tictactoe;

import java.util.Objects;

public class Square {

  private final Integer position;
  private Mark mark;
  private boolean marked;

  public Square(Integer position, Mark mark) {
    this.position = position;
    this.mark = mark;
    this.marked = !mark.isEmpty();
  }

  public Integer getPosition() {
    return position;
  }

  public boolean isMarked() {
    return marked;
  }

  public Mark getMark() {
    return mark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Square square = (Square) o;
    return marked == square.marked &&
        Objects.equals(mark, square.mark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mark, marked);
  }
}
