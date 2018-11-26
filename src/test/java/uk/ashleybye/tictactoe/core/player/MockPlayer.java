package uk.ashleybye.tictactoe.core.player;

import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.Mark;
import uk.ashleybye.tictactoe.core.Player;

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
