package uk.ashleybye.tictactoe.core;

import java.util.Objects;
import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.core.player.PlayerFactory;

public class GameRunner {

  private final ClientInterface clientInterface;
  private Game game;

  public GameRunner(Game game, ClientInterface clientInterface) {
    this.game = game;
    this.clientInterface = clientInterface;
  }

  public static GameRunner create(
      PlayerFactory playerFactory, GameConfiguration gameConfiguration, ClientInterface clientInterface) {
    Player playerOne = playerFactory.make(gameConfiguration.getPlayerConfiguration(1));
    Player playerTwo = playerFactory.make(gameConfiguration.getPlayerConfiguration(2));

    Game game = new Game(playerOne, playerTwo, gameConfiguration.getEmptyMark());

    return new GameRunner(game, clientInterface);
  }

  public void play() {
    while (!game.isGameOver()) {
      playGame();
    }
    clientInterface.renderGame(game.generateGameReport());
  }

  private void playGame() {
    try {
      clientInterface.renderGame(game.generateGameReport());
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
    GameRunner runner = (GameRunner) o;
    return Objects.equals(clientInterface, runner.clientInterface) &&
        Objects.equals(game, runner.game);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientInterface, game);
  }
}
