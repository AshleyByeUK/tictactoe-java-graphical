package uk.ashleybye.tictactoe.console.game;

import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;

public class ConsolePlayerConfiguration implements PlayerConfiguration {

  private String playerType;
  private String playerName;
  private Mark playerMark;

  @Override
  public String getPlayerType() {
    return playerType;
  }

  public void setPlayerType(String playerType) {
    this.playerType = playerType;
  }

  @Override
  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  @Override
  public Mark getPlayerMark() {
    return playerMark;
  }

  public void setPlayerMark(Mark playerMark) {
    this.playerMark = playerMark;
  }
}
