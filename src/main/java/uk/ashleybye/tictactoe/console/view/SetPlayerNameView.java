package uk.ashleybye.tictactoe.console.view;

import uk.ashleybye.tictactoe.console.IOWrapper;
import uk.ashleybye.tictactoe.console.game.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.game.ConsolePlayerConfiguration;

public class SetPlayerNameView extends View {

  private static final String ENTER_PLAYER_NAME = "Enter a new name for %s";

  private final int player;

  SetPlayerNameView(View previousMenu,
      ConsoleGameConfiguration configuration,
      int player,
      IOWrapper ioWrapper) {
    super(previousMenu, configuration, ioWrapper);
    this.player = player;
  }

  @Override
  public void render() {
    ioWrapper.render(String.format("%s\n\n%s",
        String.format(ENTER_PLAYER_NAME, String.format(PLAYER_HEADING, player)),
        PROMPT));
  }

  @Override
  public View handleInput(String input) {
    ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(player);
    playerConfiguration.setPlayerName(input.strip());
    configuration.setPlayerConfiguration(player, playerConfiguration);
    return previousMenu;
  }
}
