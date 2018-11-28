package uk.ashleybye.tictactoe.core;

import java.util.Objects;
import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;

public class TicTacToeRunner {

  private final ClientInterface clientInterface;
  private TicTacToe ticTacToe;

  TicTacToeRunner(TicTacToe ticTacToe, ClientInterface clientInterface) {
    this.ticTacToe = ticTacToe;
    this.clientInterface = clientInterface;
  }

  public static TicTacToeRunner create(
      PlayerFactory playerFactory, GameConfiguration gameConfiguration, ClientInterface clientInterface) {
    Player playerOne = makePlayer(1, playerFactory, gameConfiguration);
    Player playerTwo = makePlayer(2, playerFactory, gameConfiguration);

    TicTacToe ticTacToe = new TicTacToe(playerOne, playerTwo, gameConfiguration.getEmptyMark());

    return new TicTacToeRunner(ticTacToe, clientInterface);
  }

  private static Player makePlayer(int player, PlayerFactory playerFactory, GameConfiguration gameConfiguration) {
    PlayerConfiguration configuration = gameConfiguration.getPlayerConfiguration(player);
    return playerFactory.make(
        configuration.getPlayerType(),
        configuration.getPlayerName(),
        configuration.getPlayerMark());
  }

  public void play() {
    while (!ticTacToe.isGameOver()) {
      playGame();
    }
    clientInterface.renderGame(ticTacToe.generateGameReport());
  }

  private void playGame() {
    try {
      clientInterface.renderGame(ticTacToe.generateGameReport());
      ticTacToe = ticTacToe.playNextTurn();
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
    TicTacToeRunner runner = (TicTacToeRunner) o;
    return Objects.equals(clientInterface, runner.clientInterface) &&
        Objects.equals(ticTacToe, runner.ticTacToe);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientInterface, ticTacToe);
  }
}
