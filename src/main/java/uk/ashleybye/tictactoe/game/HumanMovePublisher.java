package uk.ashleybye.tictactoe.game;

public interface HumanMovePublisher {

  void subscribe(HumanMoveSubscriber subscriber);

  void handleMoveMade(int position);
}
