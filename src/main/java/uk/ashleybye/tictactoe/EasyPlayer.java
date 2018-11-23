package uk.ashleybye.tictactoe;

public class EasyPlayer implements Player {

  private final Mark mark;

  public EasyPlayer(Mark mark) {
    this.mark = mark;
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  @Override
  public int choosePositionToPlay(Game game) {
    return game.listOpenPositions().get(0);
  }
}
