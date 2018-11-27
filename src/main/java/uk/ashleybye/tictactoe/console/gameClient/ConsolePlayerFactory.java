package uk.ashleybye.tictactoe.console.gameClient;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.core.player.HumanTurnPublisher;
import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.core.PlayerFactory;
import uk.ashleybye.tictactoe.core.player.EasyPlayer;
import uk.ashleybye.tictactoe.core.player.HardPlayer;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;

public class ConsolePlayerFactory implements PlayerFactory {

  private final HumanTurnPublisher turnPublisher;

  public ConsolePlayerFactory(HumanTurnPublisher turnPublisher) {
    this.turnPublisher = turnPublisher;
  }

  @Override
  public List<String> listPlayerTypes() {
    return Arrays.asList("human", "easy", "hard");
  }

  @Override
  public Player make(String playerType, String playerName, Mark playerMark) {
    switch (playerType) {
      case "human":
        return new HumanPlayer(playerMark, playerName, turnPublisher);
      case "easy":
        return new EasyPlayer(playerMark, playerName);
      case "hard":
        return new HardPlayer(playerMark, playerName);
      default:
        throw new IllegalArgumentException();
    }
  }
}
