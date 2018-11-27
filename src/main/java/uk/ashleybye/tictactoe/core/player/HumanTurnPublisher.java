package uk.ashleybye.tictactoe.core.player;

public interface HumanTurnPublisher {

  void subscribeToTurnNotifications(HumanTurnSubscriber humanPlayer);

  void handleMoveMade(int position);
}
