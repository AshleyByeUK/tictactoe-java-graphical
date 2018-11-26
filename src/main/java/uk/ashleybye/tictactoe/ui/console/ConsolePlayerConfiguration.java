package uk.ashleybye.tictactoe.ui.console;

import uk.ashleybye.tictactoe.core.Mark;
import uk.ashleybye.tictactoe.core.PlayerConfiguration;

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

  public void setPlayerMark(String playerMark) {
    this.playerMark = new ConsoleMark(playerMark);
  }
}
