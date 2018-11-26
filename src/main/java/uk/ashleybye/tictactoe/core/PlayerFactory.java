package uk.ashleybye.tictactoe.core;

import java.util.List;

public interface PlayerFactory {

  List<String> listPlayerTypes();

  Player make(String playerType, String playerName, Mark playerMark);
}
