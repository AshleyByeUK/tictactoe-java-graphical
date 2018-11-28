package uk.ashleybye.tictactoe.core.player;

import uk.ashleybye.tictactoe.core.TicTacToe;
import uk.ashleybye.tictactoe.core.board.Mark;

public interface Player {

  Mark getMark();

  String getName();

  int choosePositionToPlay(TicTacToe ticTacToe);
}
