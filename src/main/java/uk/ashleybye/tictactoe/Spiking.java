package uk.ashleybye.tictactoe;

import uk.ashleybye.tictactoe.console.GameConsole;
import uk.ashleybye.tictactoe.console.ConsoleMark;
import uk.ashleybye.tictactoe.console.IOWrapper;
import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.HumanMovePublisher;
import uk.ashleybye.tictactoe.game.HumanPlayer;
import uk.ashleybye.tictactoe.game.Player;

public class Spiking {

  public static void main(String[] args) {
    IOWrapper ioWrapper = new IOWrapper();
    HumanMovePublisher gameConsole = new GameConsole(ioWrapper);
    Player playerOne = new HumanPlayer(new ConsoleMark("X"), gameConsole);
    Player playerTwo = new HumanPlayer(new ConsoleMark("O"), gameConsole);
    Game game = new Game(playerOne, playerTwo, ConsoleMark.emptyMark());

    while (!game.isGameOver()) {
      // Do the outputty
      game = game.playNextTurn();
    }
    // Do the final outputty
  }
}
