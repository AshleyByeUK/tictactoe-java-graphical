package uk.ashleybye.tictactoe.core;

public interface Player {

  Mark getMark();

  String getName();

  int choosePositionToPlay(Game game);
}
