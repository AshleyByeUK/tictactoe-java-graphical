package uk.ashleybye.tictactoe.console;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.console.ConsoleClient.InvalidMenuOption;

public class MainMenuItem extends MenuItem {

  static final String MAIN_TITLE =
      " _______ _   _______      _______          \n" +
          "|__   __(_) |__   __|    |__   __|         \n" +
          "   | |   _  ___| | __ _  ___| | ___   ___  \n" +
          "   | |  | |/ __| |/ _` |/ __| |/ _ \\ / _ \\ \n" +
          "   | |  | | (__| | (_| | (__| | (_) |  __/ \n" +
          "   |_|  |_|\\___|_|\\__,_|\\___|_|\\___/ \\___| \n\n\n";

  private static final String CURRENT_GAME_OPTIONS = "Current game options:";
  private static final String MAIN_MENU_HEADING = "Select an option below:";
  private static final String MAIN_MENU_CONFIGURE_PLAYER = "Configure %s";
  private static final String MAIN_MENU_PLAY = "Play a game";
  private static final String MAIN_MENU_QUIT = "Quit";

  public MainMenuItem(ConsoleGameConfiguration gameConfiguration) {
    super(gameConfiguration);
  }

  @Override
  public String launch() {
    return MAIN_TITLE
        + textForCurrentGameOptions() + "\n"
        + textForMainMenu() + "\n"
        + PROMPT;
  }

  private String textForCurrentGameOptions() {
    return CURRENT_GAME_OPTIONS + "\n\n"
        + textForCurrentPlayerOptions(1) + "\n"
        + textForCurrentPlayerOptions(2);
  }

  private String textForCurrentPlayerOptions(int playerNumber) {
    List<ConsolePlayerConfiguration> playerConfigurations = configuration.getPlayerConfigurations();
    return String.format(
        PLAYER_CONFIGURATION,
        String.format("%s:", String.format(PLAYER_HEADING, playerNumber)),
        String.format(PLAYER_TYPE, textForPlayerType(playerConfigurations.get(playerNumber - 1).getPlayerType())),
        String.format(PLAYER_NAME, playerConfigurations.get(playerNumber - 1).getPlayerName()),
        String.format(PLAYER_MARK, playerConfigurations.get(playerNumber - 1).getPlayerMark()));
  }

  private String textForPlayerType(String type) {
    switch (type) {
      case "human":
        return PLAYER_TYPE_HUMAN;
      case "easy":
        return PLAYER_TYPE_EASY;
      default:
        return "";
    }
  }

  private String textForMainMenu() {
    return MAIN_MENU_HEADING + "\n\n"
        + textForMainMenuOptions();
  }

  private String textForMainMenuOptions() {
    List<String> menuItems = Arrays.asList(
        String.format(MAIN_MENU_CONFIGURE_PLAYER, String.format(PLAYER_HEADING, 1)),
        String.format(MAIN_MENU_CONFIGURE_PLAYER, String.format(PLAYER_HEADING, 2)),
        MAIN_MENU_PLAY,
        MAIN_MENU_QUIT);

    String text = "";
    for (int i = 0; i < menuItems.size(); i++)
      text += String.format("%d. %s\n", i + 1, menuItems.get(i));
    return text;
  }

  @Override
  public MenuItem handleInput(String input) {
    switch (input) {
      case "1":
        return new ConfigurePlayerMenuItem(this, configuration, 1);
      case "2":
        return new ConfigurePlayerMenuItem(this, configuration, 2);
      case "3":
        MenuItem play = new PlayGameMenuItem(this, configuration);
        play.setIOWrapper(ioWrapper);
        return play;
      case "4":
        return previousMenu;
      default:
        throw new InvalidMenuOption();
    }
  }
}
