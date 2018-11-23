package uk.ashleybye.tictactoe.game;

public interface Player {

  Mark getMark();

  int choosePositionToPlay(Game game);
}
