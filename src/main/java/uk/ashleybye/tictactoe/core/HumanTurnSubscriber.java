package uk.ashleybye.tictactoe.core;

public interface HumanTurnSubscriber {

  void notifyMoveMade(int position);

  String getName();
}
