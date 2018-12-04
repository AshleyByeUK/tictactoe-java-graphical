package uk.ashleybye.tictactoe.console.game;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;
import uk.ashleybye.tictactoe.core.player.PlayerFactory;

public class MockPlayerFactory implements PlayerFactory {

  @Override
  public List<String> listPlayerTypes() {
    return Arrays.asList("human", "easy", "hard");
  }

  @Override
  public Player make(PlayerConfiguration playerConfiguration) {
    if (playerConfiguration.getPlayerType().equals("1")) {
      return new MockPlayer(new MockPlayerOneMark(), playerConfiguration.getPlayerName());
    } else {
      return new MockPlayer(new MockPlayerTwoMark(), playerConfiguration.getPlayerName());
    }
  }
}
