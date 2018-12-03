package uk.ashleybye.tictactoe.graphical.game;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.core.ClientInterface;
import uk.ashleybye.tictactoe.core.PlayerFactory;
import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.EasyPlayer;
import uk.ashleybye.tictactoe.core.player.HardPlayer;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;
import uk.ashleybye.tictactoe.core.player.Player;

public class GraphicalPlayerFactory implements PlayerFactory {

  private final ClientInterface clientInterface;

  public GraphicalPlayerFactory(ClientInterface clientInterface) {
    this.clientInterface = clientInterface;
  }

  @Override
  public List<String> listPlayerTypes() {
    return Arrays.asList("human", "easy", "hard");
  }

  @Override
  public Player make(String playerType, String playerName, Mark playerMark) {
    switch (playerType) {
      case "human":
        return new HumanPlayer(playerMark, playerName, clientInterface);
      case "easy":
        return new EasyPlayer(playerMark, playerName);
      case "hard":
        return new HardPlayer(playerMark, playerName);
      default:
        throw new IllegalArgumentException();
    }
  }
}
