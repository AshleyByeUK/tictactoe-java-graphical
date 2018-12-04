package uk.ashleybye.tictactoe.console.view;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.console.ConsoleClient.InvalidMenuOption;
import uk.ashleybye.tictactoe.console.IOWrapper;
import uk.ashleybye.tictactoe.console.game.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.game.ConsolePlayerConfiguration;

public class MainView extends View {

  public static final String MAIN_TITLE =
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

  private View configurePlayerOneView;
  private View configurePlayerTwoView;
  private View playGameView;

  public MainView(ConsoleGameConfiguration gameConfiguration, IOWrapper ioWrapper) {
    super(gameConfiguration, ioWrapper);
  }

  public void setConfigurePlayerOneView(View view) {
    configurePlayerOneView = view;
  }

  public void setConfigurePlayerTwoView(View view) {
    configurePlayerTwoView = view;
  }

  public void setPlayGameView(View view) {
    playGameView = view;
  }

  @Override
  public void render() {
    ioWrapper.render(String.format("%s%s\n%s\n%s",
        MAIN_TITLE,
        textForCurrentGameOptions(),
        textForMainMenu(),
        PROMPT));
  }

  private String textForCurrentGameOptions() {
    return String.format("%s\n\n%s\n%s",
        CURRENT_GAME_OPTIONS,
        textForCurrentPlayerOptions(1),
        textForCurrentPlayerOptions(2));
  }

  private String textForCurrentPlayerOptions(int playerNumber) {
    ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(playerNumber);
    return String.format(
        PLAYER_CONFIGURATION,
        String.format("%s:", String.format(PLAYER_HEADING, playerNumber)),
        String.format(PLAYER_TYPE, textForPlayerType(playerConfiguration.getPlayerType())),
        String.format(PLAYER_NAME, playerConfiguration.getPlayerName()),
        String.format(PLAYER_MARK, playerConfiguration.getPlayerMark()));
  }


  private String textForMainMenu() {
    return String.format("%s\n\n%s", MAIN_MENU_HEADING, textForMainMenuOptions());
  }

  private String textForMainMenuOptions() {
    List<String> menuItems = Arrays.asList(
        String.format(MAIN_MENU_CONFIGURE_PLAYER, String.format(PLAYER_HEADING, 1)),
        String.format(MAIN_MENU_CONFIGURE_PLAYER, String.format(PLAYER_HEADING, 2)),
        MAIN_MENU_PLAY,
        MAIN_MENU_QUIT);

    String text = "";
    for (int i = 0; i < menuItems.size(); i++) {
      text += String.format("%d. %s\n", i + 1, menuItems.get(i));
    }
    return text;
  }

  @Override
  public View handleInput(String input) {
    switch (input) {
      case "1":
        return configurePlayerOneView;
      case "2":
        return configurePlayerTwoView;
      case "3":
        return playGameView;
      case "4":
        return previousMenu;
      default:
        throw new InvalidMenuOption();
    }
  }
}
