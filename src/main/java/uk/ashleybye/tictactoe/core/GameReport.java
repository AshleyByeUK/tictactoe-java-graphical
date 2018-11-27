package uk.ashleybye.tictactoe.core;

import java.util.HashMap;
import java.util.Map;
import uk.ashleybye.tictactoe.core.board.Board;
import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.board.Square;
import uk.ashleybye.tictactoe.core.player.Player;

public class GameReport {

  private static final String EMPTY = "";
  private static final String READY = "ready";
  private static final String PLAYING = "playing";
  private static final String GAME_OVER = "game_over";
  private static final String TIED_GAME = "tied";
  private static final String WON_GAME = "won";

  private Map<Integer, Mark> currentBoard = new HashMap<>();
  private String currentPlayer = EMPTY;
  private String lastPlayer = EMPTY;
  private String currentState = READY;
  private boolean tied = false;
  private boolean won = false;

  public Map<Integer, Mark> getCurrentBoard() {
    return currentBoard;
  }

  void setCurrentBoard(Board board) {
    for (Square square : board)
      currentBoard.put(square.getPosition(), square.getMark());
  }

  public String getCurrentPlayer() {
    return currentPlayer;
  }

  void setCurrentPlayer(Player player) {
    currentPlayer = player.getName();
  }

  public String getLastPlayer() {
    if (currentState.equals(READY))
      return EMPTY;
    else
      return lastPlayer;
  }

  void setLastPlayer(Player player) {
    lastPlayer = player.getName();
  }

  public String getCurrentState() {
    return currentState;
  }

  void setCurrentState(GameState gameState) {
    switch (gameState) {
      case READY:
        currentState = READY;
        break;
      case PLAYING:
        currentState = PLAYING;
        break;
      case GAME_OVER:
        currentState = GAME_OVER;
        break;
    }
  }

  public String getResult() {
    if (tied)
      return TIED_GAME;
    else if (won)
      return WON_GAME;
    else
      return EMPTY;
  }

  void setResult(boolean tied, boolean won) {
    this.tied = tied;
    this.won = won;
  }

  public String getWinner() {
    if (won)
      return getLastPlayer();
    else
      return EMPTY;
  }
}
