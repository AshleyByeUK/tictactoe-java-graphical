package uk.ashleybye.tictactoe.core;

import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;

public interface GameConfiguration {

  PlayerConfiguration getPlayerConfiguration(int player);

  Mark getEmptyMark();
}
