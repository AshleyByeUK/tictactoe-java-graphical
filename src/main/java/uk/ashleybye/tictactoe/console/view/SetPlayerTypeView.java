package uk.ashleybye.tictactoe.console.view;

import java.util.List;
import uk.ashleybye.tictactoe.console.ConsoleClient.InvalidMenuOption;
import uk.ashleybye.tictactoe.console.IOWrapper;
import uk.ashleybye.tictactoe.console.game.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.game.ConsolePlayerConfiguration;
import uk.ashleybye.tictactoe.core.player.PlayerFactory;

public class SetPlayerTypeView extends View {

  private static final String SELECT_PLAYER_TYPE = "Select a player type for %s:";

  private final int player;
  private PlayerFactory playerFactory;

  SetPlayerTypeView(
      View previousMenu,
      ConsoleGameConfiguration configuration,
      int player,
      PlayerFactory playerFactory,
      IOWrapper ioWrapper) {
    super(previousMenu, configuration, ioWrapper);
    this.player = player;
    this.playerFactory = playerFactory;
  }

  @Override
  public void render() {
    ioWrapper.render(String.format("%s\n\n%s\n%s",
        String.format(SELECT_PLAYER_TYPE, String.format(PLAYER_HEADING, player)),
        textForPlayerTypes(),
        PROMPT));
  }

  private String textForPlayerTypes() {
    List<String> playerTypes = playerFactory.listPlayerTypes();
    String text = "";
    for (int i = 0; i < playerTypes.size(); i++) {
      text += String.format("%d. %s\n", i + 1, textForPlayerType(playerTypes.get(i)));
    }
    return text;
  }

  @Override
  public View handleInput(String input) {
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
