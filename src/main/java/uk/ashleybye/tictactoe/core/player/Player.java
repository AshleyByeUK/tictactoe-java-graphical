package uk.ashleybye.tictactoe.core.player;

import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.TicTacToe;

public interface Player {

  Mark getMark();

  String getName();

  int choosePositionToPlay(TicTacToe ticTacToe);
}
