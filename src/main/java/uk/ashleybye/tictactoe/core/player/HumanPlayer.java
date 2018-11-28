package uk.ashleybye.tictactoe.core.player;

import java.util.Objects;
import uk.ashleybye.tictactoe.core.TicTacToe;
import uk.ashleybye.tictactoe.core.board.Mark;

public class HumanPlayer implements Player, HumanTurnSubscriber {

  private final Mark mark;
  private final String name;
  private final HumanTurnPublisher publisher;
  private Integer positionToPlay;

  public HumanPlayer(Mark mark, String name, HumanTurnPublisher publisher) {
    this.mark = mark;
    this.name = name;
    this.publisher = publisher;
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
    positionToPlay = null;
    publisher.subscribeToTurnNotifications(this);
    while (true) {
      if (positionToPlay != null) {
        return positionToPlay;
      }
    }
  }

  @Override
  public void notifyMoveMade(int position) {
    positionToPlay = position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HumanPlayer that = (HumanPlayer) o;
    return Objects.equals(mark, that.mark) &&
        Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mark, name);
  }
}
