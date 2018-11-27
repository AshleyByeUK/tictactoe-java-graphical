package uk.ashleybye.tictactoe.console.view;

import uk.ashleybye.tictactoe.console.gameClient.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.gameClient.ConsolePlayerConfiguration;

public class SetPlayerNameView extends View {

  private static final String ENTER_PLAYER_NAME = "Enter a new name for %s";

  private final int player;

  SetPlayerNameView(View previousMenu, ConsoleGameConfiguration configuration, int player) {
    super(previousMenu, configuration);
    this.player = player;
  }

  @Override
  public String launch() {
    return String.format(ENTER_PLAYER_NAME, String.format(PLAYER_HEADING, player)) + "\n\n"
        + PROMPT;
  }

  @Override
  public View handleInput(String input) {
    ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(player);
    playerConfiguration.setPlayerName(input.strip());
    configuration.setPlayerConfiguration(player, playerConfiguration);
    return previousMenu;
  }
}
