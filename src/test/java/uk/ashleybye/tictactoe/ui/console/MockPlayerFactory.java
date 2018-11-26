package uk.ashleybye.tictactoe.ui.console;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.core.Mark;
import uk.ashleybye.tictactoe.core.Player;
import uk.ashleybye.tictactoe.core.PlayerFactory;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

public class MockPlayerFactory implements PlayerFactory {

  @Override
  public List<String> listPlayerTypes() {
    return Arrays.asList("human", "easy");
  }

  @Override
  public Player make(String playerType, String playerName, Mark playerMark) {
    if (playerType.equals("1"))
      return new MockPlayer(new MockPlayerOneMark(), playerName);
    else
      return new MockPlayer(new MockPlayerTwoMark(), playerName);
  }
}
