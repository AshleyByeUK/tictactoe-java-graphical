package uk.ashleybye.tictactoe.console;

import uk.ashleybye.tictactoe.game.HumanMovePublisher;
import uk.ashleybye.tictactoe.game.HumanMoveSubscriber;

public class GameConsole implements HumanMovePublisher {

  private final IOWrapper ioWrapper;
  private HumanMoveSubscriber subscriber;

  public GameConsole(IOWrapper ioWrapper) {
    this.ioWrapper = ioWrapper;
  }

  @Override
  public void subscribe(HumanMoveSubscriber subscriber) {
    this.subscriber = subscriber;


    int position = getMoveFromUser();

    handleMoveMade(position);
  }

  private int getMoveFromUser() {
    ioWrapper.render("Gimme a move, punk! > ");
    
    Integer position = null;
    while (position == null) {
      try {
        String input = ioWrapper.getInput();
        position = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        ioWrapper.render("Gimme a proper move, punk! > ");
      }
    }
    return position;
  }

  @Override
  public void handleMoveMade(int position) {
    subscriber.notifyMoveMade(position);
  }
}
