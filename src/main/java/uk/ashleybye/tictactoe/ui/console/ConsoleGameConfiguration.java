package uk.ashleybye.tictactoe.ui.console;

import java.util.ArrayList;
import java.util.List;
import uk.ashleybye.tictactoe.core.GameConfiguration;
import uk.ashleybye.tictactoe.core.Mark;

public class ConsoleGameConfiguration implements GameConfiguration {

  private List<ConsolePlayerConfiguration> playerConfigurations = new ArrayList<>();
  private Mark emptyMark;

  @Override
  public ConsolePlayerConfiguration getPlayerConfiguration(int player) {
    return playerConfigurations.get(player - 1);
  }

  public void addPlayerConfiguration(int playerNumber, ConsolePlayerConfiguration configuration) {
    this.playerConfigurations.add(playerNumber - 1, configuration);
  }

  public void setPlayerConfiguration(int playerNumber, ConsolePlayerConfiguration configuration) {
    this.playerConfigurations.set(playerNumber - 1, configuration);
  }

  @Override
  public Mark getEmptyMark() {
    return emptyMark;
  }

  public void setEmptyMark(Mark emptyMark) {
    this.emptyMark = emptyMark;
  }
}
