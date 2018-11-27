package uk.ashleybye.tictactoe.console.view;

import uk.ashleybye.tictactoe.console.ConsoleClient.InvalidMenuOption;
import uk.ashleybye.tictactoe.console.gameClient.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.gameClient.ConsolePlayerConfiguration;
import uk.ashleybye.tictactoe.core.PlayerFactory;

public class ConfigurePlayerView extends View {

  private static final String MAIN_HEADING = "Configure options for %s, current options shown in brackets:";
  private static final String SET_DONE = "4. Done";
  private static final String SET_PLAYER_MARK = "2. Set player mark (%s)";
  private static final String SET_PLAYER_NAME = "1. Set player name (%s)";
  private static final String SET_PLAYER_TYPE = "3. Set player type (%s)";
  private final int player;
  private PlayerFactory playerFactory;

  public ConfigurePlayerView(
      View previousMenu, ConsoleGameConfiguration configuration, int player, PlayerFactory playerFactory) {
    super(previousMenu, configuration);
    this.player = player;
    this.playerFactory = playerFactory;
  }

  @Override
  public String launch() {
    ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(player);
    return configurePlayerMenu(playerConfiguration);
  }

  private String configurePlayerMenu(ConsolePlayerConfiguration playerConfiguration) {
    return String.format(MAIN_HEADING, playerConfiguration.getPlayerName()) + "\n\n"
        + String.format(SET_PLAYER_NAME, playerConfiguration.getPlayerName()) + "\n"
        + String.format(SET_PLAYER_MARK, playerConfiguration.getPlayerMark()) + "\n"
        + String.format(SET_PLAYER_TYPE, textForPlayerType(playerConfiguration.getPlayerType())) + "\n"
        + SET_DONE + "\n\n"
        + PROMPT;
  }

  @Override
  public View handleInput(String input) {
    switch (input) {
      case "1":
        return new SetPlayerNameView(this, configuration, player);
      case "2":
        return new SetPlayerMarkView(this, configuration, player);
      case "3":
        return new SetPlayerTypeView(this, configuration, player, playerFactory);
      case "4":
        return previousMenu;
      default:
        throw new InvalidMenuOption();
    }
  }
}
