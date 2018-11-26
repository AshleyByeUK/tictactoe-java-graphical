package uk.ashleybye.tictactoe.ui.console.menu;

import java.util.List;
import uk.ashleybye.tictactoe.core.PlayerFactory;
import uk.ashleybye.tictactoe.ui.console.ConsoleClient.InvalidMenuOption;
import uk.ashleybye.tictactoe.ui.console.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.ui.console.ConsolePlayerConfiguration;
import uk.ashleybye.tictactoe.ui.console.MenuItem;

public class ConfigurePlayerMenuItem extends MenuItem {

  private static final String ENTER_PLAYER_MARK = "Enter a new mark for %s";
  private static final String ENTER_PLAYER_NAME = "Enter a new name for %s";
  private static final String MAIN_HEADING = "Configure options for %s, current options shown in brackets:";
  private static final String SELECT_PLAYER_TYPE = "Select a player type for %s:";
  private static final String SET_DONE = "4. Done";
  private static final String SET_PLAYER_MARK = "2. Set player mark (%s)";
  private static final String SET_PLAYER_NAME = "1. Set player name (%s)";
  private static final String SET_PLAYER_TYPE = "3. Set player type (%s)";
  private final int player;
  private PlayerFactory playerFactory;

  public ConfigurePlayerMenuItem(
      MenuItem previousMenu, ConsoleGameConfiguration configuration, int player, PlayerFactory playerFactory) {
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
  public MenuItem handleInput(String input) {
    switch (input) {
      case "1":
        return new SetPlayerNameMenuItem(this, configuration, player);
      case "2":
        return new SetPlayerMarkMenuItem(this, configuration, player);
      case "3":
        return new SetPlayerTypeMenuItem(this, configuration, player, playerFactory);
      case "4":
        return previousMenu;
      default:
        throw new InvalidMenuOption();
    }
  }

  private class SetPlayerNameMenuItem extends MenuItem {

    private final int player;

    SetPlayerNameMenuItem(MenuItem previousMenu, ConsoleGameConfiguration configuration, int player) {
      super(previousMenu, configuration);
      this.player = player;
    }

    @Override
    public String launch() {
      return String.format(ENTER_PLAYER_NAME, String.format(PLAYER_HEADING, player)) + "\n\n"
          + PROMPT;
    }

    @Override
    public MenuItem handleInput(String input) {
      ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(player);
      playerConfiguration.setPlayerName(input.strip());
      configuration.setPlayerConfiguration(player, playerConfiguration);
      return previousMenu;
    }
  }

  private class SetPlayerMarkMenuItem extends MenuItem {

    private final int player;

    SetPlayerMarkMenuItem(MenuItem previousMenu, ConsoleGameConfiguration configuration, int player) {
      super(previousMenu, configuration);
      this.player = player;
    }

    @Override
    public String launch() {
      return String.format(ENTER_PLAYER_MARK, String.format(PLAYER_HEADING, player)) + "\n\n"
          + PROMPT;
    }

    @Override
    public MenuItem handleInput(String input) {
      ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(player);
      playerConfiguration.setPlayerMark(input.strip().toUpperCase());
      configuration.setPlayerConfiguration(player, playerConfiguration);
      return previousMenu;
    }
  }

  private class SetPlayerTypeMenuItem extends MenuItem {

    private final int player;
    private PlayerFactory playerFactory;

    SetPlayerTypeMenuItem(
        MenuItem previousMenu, ConsoleGameConfiguration configuration, int player, PlayerFactory playerFactory) {
      super(previousMenu, configuration);
      this.player = player;
      this.playerFactory = playerFactory;
    }

    @Override
    public String launch() {
      return String.format(SELECT_PLAYER_TYPE, String.format(PLAYER_HEADING, player)) + "\n\n"
          + textForPlayerTypes() + "\n"
          + PROMPT;
    }

    private String textForPlayerTypes() {
      List<String> playerTypes = playerFactory.listPlayerTypes();
      String text = "";
      for (int i = 0; i < playerTypes.size(); i++)
        text += String.format("%d. %s\n", i + 1, textForPlayerType(playerTypes.get(i)));
      return text;
    }

    @Override
    public MenuItem handleInput(String input) {
      try {
        String option = playerFactory.listPlayerTypes().get(Integer.parseInt(input) - 1);
        ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(player);
        playerConfiguration.setPlayerType(option);
        configuration.setPlayerConfiguration(player, playerConfiguration);
        return previousMenu;
      } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
        throw new InvalidMenuOption();
      }
    }
  }
}
