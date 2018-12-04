package uk.ashleybye.tictactoe.console.view;

import uk.ashleybye.tictactoe.console.IOWrapper;
import uk.ashleybye.tictactoe.console.game.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.game.ConsoleGameRunner;
import uk.ashleybye.tictactoe.console.game.ConsolePlayerFactory;
import uk.ashleybye.tictactoe.core.ClientInterface;

public class PlayGameView extends View {

  private static final String RETURN_TO_MAIN_MENU = "Press ENTER to return to the main view";
  private final ConsolePlayerFactory playerFactory;
  private final ClientInterface clientInterface;

  public PlayGameView(View previousMenu,
      ConsoleGameConfiguration configuration,
      ConsolePlayerFactory playerFactory,
      ClientInterface clientInterface,
      IOWrapper ioWrapper) {
    super(previousMenu, configuration, ioWrapper);
    this.playerFactory = playerFactory;
    this.clientInterface = clientInterface;
  }

  @Override
  public void render() {
    playGame();
    ioWrapper.render(String.format("\n\n%s", RETURN_TO_MAIN_MENU));
  }

  private void playGame() {
    ConsoleGameRunner runner = ConsoleGameRunner.create(playerFactory, configuration, clientInterface);
    runner.play();
  }

  @Override
  public View handleInput(String input) {
    return previousMenu;
  }
}
