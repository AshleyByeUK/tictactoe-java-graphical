package uk.ashleybye.tictactoe;

public class HumanPlayer implements Player, HumanMoveSubscriber {

  private final Mark mark;
  private final HumanMovePublisher publisher;
  private Integer positionToPlay;

  public HumanPlayer(Mark mark, HumanMovePublisher publisher) {
    this.mark = mark;
    this.publisher = publisher;
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  @Override
  public int choosePositionToPlay(Game game) {
    positionToPlay = null;
    publisher.subscribe(this);
    while (positionToPlay == null) {

    }
    return positionToPlay;
  }

  @Override
  public void notifyMoveMade(int position) {
    positionToPlay = position;
  }
}
