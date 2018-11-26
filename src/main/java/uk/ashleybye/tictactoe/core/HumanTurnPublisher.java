package uk.ashleybye.tictactoe.core;

public interface HumanTurnPublisher {

  void subscribeToTurnNotifications(HumanTurnSubscriber humanPlayer);

  void handleMoveMade(int position);
}
