package uk.ashleybye.tictactoe.core.player;

import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.board.Mark;

public interface Player {

  Mark getMark();

  String getName();

  int takeTurn(Game game);
}
