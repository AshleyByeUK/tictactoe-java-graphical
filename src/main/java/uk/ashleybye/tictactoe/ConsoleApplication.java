package uk.ashleybye.tictactoe;

import uk.ashleybye.tictactoe.console.ConsoleMark;
import uk.ashleybye.tictactoe.console.GameConsole;
import uk.ashleybye.tictactoe.console.IOWrapper;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.Player;
import uk.ashleybye.tictactoe.core.TicTacToe;
import uk.ashleybye.tictactoe.core.player.EasyPlayer;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;

public class ConsoleApplication {

  public static void main(String[] args) {
    IOWrapper ioWrapper = new IOWrapper();
    GameConsole gameConsole = new GameConsole(ioWrapper);
    Player playerOne = new HumanPlayer(new ConsoleMark("X"), "Player 1", gameConsole);
    Player playerTwo = new EasyPlayer(new ConsoleMark("O"), "Player 2");
    Game game = new Game(playerOne, playerTwo, ConsoleMark.emptyMark());
    TicTacToe ticTacToe = new TicTacToe(game, gameConsole);
    ticTacToe.play();
  }
}
