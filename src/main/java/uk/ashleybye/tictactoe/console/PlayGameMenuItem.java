package uk.ashleybye.tictactoe.console;

import uk.ashleybye.tictactoe.core.PlayerFactory;
import uk.ashleybye.tictactoe.core.TicTacToe;
import uk.ashleybye.tictactoe.core.UserInterface;

public class PlayGameMenuItem extends MenuItem {

  private static final String RETURN_TO_MAIN_MENU = "Press ENTER to return to the main menu";
  private final PlayerFactory playerFactory;
  private final UserInterface userInterface;

  public PlayGameMenuItem(MenuItem previousMenu, ConsoleGameConfiguration configuration, PlayerFactory playerFactory, UserInterface userInterface) {
    super(previousMenu, configuration);
    this.playerFactory = playerFactory;
    this.userInterface = userInterface;
  }

  @Override
  public String launch() {
    playGame();
    return "\n\n" + RETURN_TO_MAIN_MENU;
  }

  private void playGame() {
    TicTacToe ticTacToe = TicTacToe.create(playerFactory, configuration, userInterface);
    ticTacToe.play();
  }

  @Override
  public MenuItem handleInput(String input) {
    return previousMenu;
  }
}
