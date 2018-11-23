package uk.ashleybye.tictactoe;

public interface HumanMovePublisher {

  void subscribe(HumanMoveSubscriber subscriber);
}
