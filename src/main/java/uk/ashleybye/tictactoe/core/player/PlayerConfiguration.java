package uk.ashleybye.tictactoe.core.player;

import uk.ashleybye.tictactoe.core.board.Mark;

public interface PlayerConfiguration {

  String getPlayerType();

  String getPlayerName();

  Mark getPlayerMark();
}
