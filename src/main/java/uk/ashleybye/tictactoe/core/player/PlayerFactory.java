package uk.ashleybye.tictactoe.core.player;

import java.util.List;

public interface PlayerFactory {

  List<String> listPlayerTypes();

  Player make(PlayerConfiguration playerConfiguration);
}
