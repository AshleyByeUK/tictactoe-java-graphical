package uk.ashleybye.tictactoe;

public class MockPlayer implements Player {

  private final Mark mark;
  private int nextPositionToPlay;

  public MockPlayer(Mark mark) {
    this.mark = mark;
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  @Override
  public int choosePositionToPlay(Game game) {
    return nextPositionToPlay;
  }

  public void setNextPositionToPlay(int position) {
    this.nextPositionToPlay = position;
  }

  @Override
  public String toString() {
    return "MockPlayer{" + "mark=" + mark + '}';
  }
}
