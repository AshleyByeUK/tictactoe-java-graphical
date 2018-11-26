package uk.ashleybye.tictactoe.console;

import uk.ashleybye.tictactoe.console.ConsoleClient.InvalidMenuOption;

public class ConfigurePlayerMenuItem extends MenuItem {

  private static final String MAIN_HEADING = "Configure options for %s";
  private final int player;

  public ConfigurePlayerMenuItem(MenuItem previousMenu, ConsoleGameConfiguration configuration, int player) {
    super(previousMenu, configuration);
    this.player = player;
  }

  @Override
  public String launch() {
    ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfigurations().get(player - 1);
    return String.format(MAIN_HEADING, playerConfiguration.getPlayerName());
  }

  @Override
  public MenuItem handleInput(String input) {
    switch (input) {
      case "4":
        return previousMenu;
      default:
        throw new InvalidMenuOption();
    }
  }
}
