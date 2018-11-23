package uk.ashleybye.tictactoe;

public interface Player {

  Mark getMark();

  int choosePositionToPlay(Game game);
}
