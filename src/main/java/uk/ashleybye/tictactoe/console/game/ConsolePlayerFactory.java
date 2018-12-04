package uk.ashleybye.tictactoe.console.game;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.core.ClientInterface;
import uk.ashleybye.tictactoe.core.player.EasyPlayer;
import uk.ashleybye.tictactoe.core.player.HardPlayer;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;
import uk.ashleybye.tictactoe.core.player.PlayerFactory;

public class ConsolePlayerFactory implements PlayerFactory {

  private final ClientInterface clientInterface;

  public ConsolePlayerFactory(ClientInterface clientInterface) {
    this.clientInterface = clientInterface;
  }

  @Override
  public List<String> listPlayerTypes() {
    return Arrays.asList("human", "easy", "hard");
  }

  @Override
  public Player make(PlayerConfiguration playerConfiguration) {
    switch (playerConfiguration.getPlayerType()) {
      case "human":
        return new HumanPlayer(playerConfiguration.getPlayerMark(), playerConfiguration.getPlayerName(),
            clientInterface);
      case "easy":
        return new EasyPlayer(playerConfiguration.getPlayerMark(), playerConfiguration.getPlayerName());
      case "hard":
        return new HardPlayer(playerConfiguration.getPlayerMark(), playerConfiguration.getPlayerName());
      default:
        throw new IllegalArgumentException();
    }
  }
}
