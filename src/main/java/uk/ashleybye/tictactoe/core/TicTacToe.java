package uk.ashleybye.tictactoe.core;

import java.util.Objects;
import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;

public class TicTacToe {

  private final UserInterface userInterface;
  private Game game;

  public static TicTacToe create(
      PlayerFactory playerFactory, GameConfiguration gameConfiguration, UserInterface userInterface) {
    Player playerOne = makePlayer(1, playerFactory, gameConfiguration);
    Player playerTwo = makePlayer(2, playerFactory, gameConfiguration);

    Game game = new Game(playerOne, playerTwo, gameConfiguration.getEmptyMark());

    return new TicTacToe(game, userInterface);
  }

  TicTacToe(Game game, UserInterface userInterface) {
    this.game = game;
    this.userInterface = userInterface;
  }

  private static Player makePlayer(int player, PlayerFactory playerFactory, GameConfiguration gameConfiguration) {
    PlayerConfiguration playerOneConfiguration = gameConfiguration.getPlayerConfiguration(player);
    return playerFactory.make(
        playerOneConfiguration.getPlayerType(),
        playerOneConfiguration.getPlayerName(),
        playerOneConfiguration.getPlayerMark());
  }

  public void play() {
    while (!game.isGameOver())
      playGame();
    userInterface.renderGame(game.generateGameReport());
  }

  private void playGame() {
    try {
      userInterface.renderGame(game.generateGameReport());
      game = game.playNextTurn();
    } catch (InvalidSquareNumber | SquareUnavailable ex) {
      // Do nothing.
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    TicTacToe ticTacToe = (TicTacToe) o;
    return Objects.equals(userInterface, ticTacToe.userInterface) &&
        Objects.equals(game, ticTacToe.game);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userInterface, game);
  }
}
