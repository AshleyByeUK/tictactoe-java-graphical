package uk.ashleybye.tictactoe.core.player;

import java.util.Objects;
import uk.ashleybye.tictactoe.core.TicTacToe;
import uk.ashleybye.tictactoe.core.board.Mark;

public class MockPlayer implements Player {

  private final Mark mark;
  private final String name;
  private int nextPositionToPlay;

  public MockPlayer(Mark mark, String name) {
    this.mark = mark;
    this.name = name;
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int choosePositionToPlay(TicTacToe ticTacToe) {
    return nextPositionToPlay;
  }

  public void setNextPositionToPlay(int position) {
    this.nextPositionToPlay = position;
  }

  @Override
  public String toString() {
    return "MockPlayer{" + "mark=" + mark + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    MockPlayer that = (MockPlayer) o;
    return Objects.equals(mark, that.mark) &&
        Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mark, name);
  }
}
