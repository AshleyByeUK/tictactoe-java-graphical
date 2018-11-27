package uk.ashleybye.tictactoe.core.player;

public interface HumanTurnSubscriber {

  void notifyMoveMade(int position);

  String getName();
}
