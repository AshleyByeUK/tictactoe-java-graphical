package uk.ashleybye.tictactoe.console.gameClient;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.core.PlayerFactory;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

public class MockPlayerFactory implements PlayerFactory {

  @Override
  public List<String> listPlayerTypes() {
    return Arrays.asList("human", "easy", "hard");
  }

  @Override
  public Player make(String playerType, String playerName, Mark playerMark) {
    if (playerType.equals("1"))
      return new MockPlayer(new MockPlayerOneMark(), playerName);
    else
      return new MockPlayer(new MockPlayerTwoMark(), playerName);
  }
}
