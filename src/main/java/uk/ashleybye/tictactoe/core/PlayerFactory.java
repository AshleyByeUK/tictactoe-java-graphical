package uk.ashleybye.tictactoe.core;

import java.util.List;
import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.Player;

public interface PlayerFactory {

  List<String> listPlayerTypes();

  Player make(String playerType, String playerName, Mark playerMark);
}
