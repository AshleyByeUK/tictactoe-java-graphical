package uk.ashleybye.tictactoe.core;

public interface GameConfiguration {

  PlayerConfiguration getPlayerConfiguration(int player);

  Mark getEmptyMark();
}
