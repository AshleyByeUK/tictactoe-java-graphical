package uk.ashleybye.tictactoe.console.game;

import java.util.Objects;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;
import uk.ashleybye.tictactoe.core.player.Player;

public class ConsoleGameRunner {

  private final GameConsole gameConsole;
  private Game game;

  public ConsoleGameRunner(Game game, GameConsole gameConsole) {
    this.game = game;
    this.gameConsole = gameConsole;
  }

  public static ConsoleGameRunner create(
      ConsolePlayerFactory playerFactory, ConsoleGameConfiguration gameConfiguration, GameConsole gameConsole) {
    Player playerOne = playerFactory.make(gameConfiguration.getPlayerConfiguration(1));
    Player playerTwo = playerFactory.make(gameConfiguration.getPlayerConfiguration(2));

    Game game = new Game(playerOne, playerTwo, gameConfiguration.getEmptyMark());

    return new ConsoleGameRunner(game, gameConsole);
  }

  public void play() {
    while (!game.isGameOver()) {
      playGame();
    }
    gameConsole.renderGame(game.generateGameReport());
  }

  private void playGame() {
    try {
      gameConsole.renderGame(game.generateGameReport());
      game = game.playNextTurn();
    } catch (InvalidSquareNumber | SquareUnavailable ex) {
      // Do nothing.
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsoleGameRunner runner = (ConsoleGameRunner) o;
    return Objects.equals(gameConsole, runner.gameConsole) &&
        Objects.equals(game, runner.game);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gameConsole, game);
  }
}
