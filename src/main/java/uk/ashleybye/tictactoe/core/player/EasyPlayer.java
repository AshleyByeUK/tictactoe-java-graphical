package uk.ashleybye.tictactoe.core.player;

import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.Mark;
import uk.ashleybye.tictactoe.core.Player;

public class EasyPlayer implements Player {

  private final Mark mark;
  private final String name;

  public EasyPlayer(Mark mark, String name) {
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
    return game.listOpenPositions().get(0);
  }
}
