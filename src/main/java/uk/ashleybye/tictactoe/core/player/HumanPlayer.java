package uk.ashleybye.tictactoe.core.player;

import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.HumanTurnPublisher;
import uk.ashleybye.tictactoe.core.HumanTurnSubscriber;
import uk.ashleybye.tictactoe.core.Mark;
import uk.ashleybye.tictactoe.core.Player;

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
  public int choosePositionToPlay(Game game) {
    positionToPlay = null;
    publisher.subscribeToTurnNotifications(this);
    while (true)
      if (positionToPlay != null)
        return positionToPlay;
  }

  @Override
  public void notifyMoveMade(int position) {
    positionToPlay = position;
  }
}
