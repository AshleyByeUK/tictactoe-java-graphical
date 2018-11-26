package uk.ashleybye.tictactoe.console;

import java.util.List;

public class ConsoleGameConfiguration {

  public List<ConsolePlayerConfiguration> playerConfigurations;

  public ConsoleGameConfiguration(List<ConsolePlayerConfiguration> playerConfigurations) {
    this.playerConfigurations = playerConfigurations;
  }

  public List<ConsolePlayerConfiguration> getPlayerConfigurations() {
    return playerConfigurations;
  }

  public void setPlayerConfiguration(int playerNumber, ConsolePlayerConfiguration configuration) {
    this.playerConfigurations.add(playerNumber, configuration);
  }
}
