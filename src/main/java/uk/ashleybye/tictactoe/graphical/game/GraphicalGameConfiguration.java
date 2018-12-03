package uk.ashleybye.tictactoe.graphical.game;

import uk.ashleybye.tictactoe.core.GameConfiguration;
import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;

public class GraphicalGameConfiguration implements GameConfiguration {

  private static GraphicalGameConfiguration configuration = null;
  private final GraphicalMark emptyMark = new GraphicalMark(" ");
  private final GraphicalPlayerConfiguration playerOneConfiguration = new GraphicalPlayerConfiguration();
  private final GraphicalPlayerConfiguration playerTwoConfiguration = new GraphicalPlayerConfiguration();

  public static GraphicalGameConfiguration getCurrentConfiguration() {
    if (configuration == null) {
      configuration = new GraphicalGameConfiguration();
    }
    return configuration;
  }

  // For testing purposes.
  public static void resetToDefaults() {
    configuration = new GraphicalGameConfiguration();
  }

  private GraphicalGameConfiguration() {
    initialisePlayerConfigurations();
  }

  private void initialisePlayerConfigurations() {
    initialisePlayerOneConfiguration();
    initialisePlayerTwoConfiguration();
  }

  private void initialisePlayerOneConfiguration() {
    playerOneConfiguration.setPlayerName("Player 1");
    playerOneConfiguration.setPlayerMark(new GraphicalMark("X"));
    playerOneConfiguration.setPlayerType("human");
  }

  private void initialisePlayerTwoConfiguration() {
    playerTwoConfiguration.setPlayerName("Player 2");
    playerTwoConfiguration.setPlayerMark(new GraphicalMark("O"));
    playerTwoConfiguration.setPlayerType("human");
  }

  @Override
  public PlayerConfiguration getPlayerConfiguration(int player) {
    return player == 1 ? playerOneConfiguration : playerTwoConfiguration;
  }

  @Override
  public Mark getEmptyMark() {
    return emptyMark;
  }

  public void setPlayerName(int playerNumber, String name) {
    if (playerNumber == 1) {
      playerOneConfiguration.setPlayerName(name);
    } else if (playerNumber == 2) {
      playerTwoConfiguration.setPlayerName(name);
    } else {
      throw new InvalidPlayerNumber();
    }
  }

  public void setPlayerMark(int playerNumber, GraphicalMark mark) {
    if (playerNumber == 1) {
      playerOneConfiguration.setPlayerMark(mark);
    } else if (playerNumber == 2) {
      playerTwoConfiguration.setPlayerMark(mark);
    } else {
      throw new InvalidPlayerNumber();
    }
  }

  public void setPlayerType(int playerNumber, String type) {
    if (playerNumber == 1) {
      playerOneConfiguration.setPlayerType(type);
    } else if (playerNumber == 2) {
      playerTwoConfiguration.setPlayerType(type);
    } else {
      throw new InvalidPlayerNumber();
    }
  }

  class InvalidPlayerNumber extends RuntimeException {
  }
}
