package uk.ashleybye.tictactoe.graphical.game;

import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;

public class GraphicalPlayerConfiguration implements PlayerConfiguration {

  private String playerType;
  private String playerName;
  private GraphicalMark playerMark;

  @Override
  public String getPlayerType() {
    return playerType;
  }

  void setPlayerType(String playerType) {
    this.playerType = playerType;
  }

  @Override
  public String getPlayerName() {
    return playerName;
  }

  void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  @Override
  public Mark getPlayerMark() {
    return playerMark;
  }

  void setPlayerMark(GraphicalMark playerMark) {
    this.playerMark = playerMark;
  }
}
