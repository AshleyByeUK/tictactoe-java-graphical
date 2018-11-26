package uk.ashleybye.tictactoe.console;

import java.util.List;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.Mark;
import uk.ashleybye.tictactoe.core.Player;
import uk.ashleybye.tictactoe.core.TicTacToe;
import uk.ashleybye.tictactoe.core.UserInterface;
import uk.ashleybye.tictactoe.core.player.EasyPlayer;

public class PlayGameMenuItem extends MenuItem {

  private static final String RETURN_TO_MAIN_MENU = "Press ENTER to return to the main menu";

  public PlayGameMenuItem(MenuItem previousMenu, ConsoleGameConfiguration configuration) {
    super(previousMenu, configuration);
  }

  @Override
  public String launch() {
    playGame();
    return RETURN_TO_MAIN_MENU;
  }

  private void playGame() {
    List<ConsolePlayerConfiguration> playerConfigurations = configuration.getPlayerConfigurations();
    Mark playerOneMark = new ConsoleMark(playerConfigurations.get(0).getPlayerMark());
    String playerOneName = playerConfigurations.get(0).getPlayerName();
    Mark playerTwoMark = new ConsoleMark(playerConfigurations.get(1).getPlayerMark());
    String playerTwoName = playerConfigurations.get(1).getPlayerName();
    Player playerOne = new EasyPlayer(playerOneMark, playerOneName);
    Player playerTwo = new EasyPlayer(playerTwoMark, playerTwoName);
    Game game = new Game(playerOne, playerTwo, ConsoleMark.emptyMark());
    UserInterface ui = new GameConsole(ioWrapper);
    TicTacToe ticTacToe = new TicTacToe(game, ui);
    ticTacToe.play();
  }

  @Override
  public MenuItem handleInput(String input) {
    return previousMenu;
  }
}
