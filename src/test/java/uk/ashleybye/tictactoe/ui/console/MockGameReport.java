package uk.ashleybye.tictactoe.ui.console;

import java.util.HashMap;
import java.util.Map;
import uk.ashleybye.tictactoe.core.GameReport;
import uk.ashleybye.tictactoe.core.Mark;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

public class MockGameReport extends GameReport {

  private static Mark empty = new MockEmptyMark();
  private static Mark one = new MockPlayerOneMark();
  private static Mark two = new MockPlayerTwoMark();
  private Map<Integer, Mark> board;
  private String currentPlayer;
  private String lastPlayer;
  private String currentState;
  private String result;
  private String winner;

  public static MockGameReport readyGame() {
    Map<Integer, Mark> board = new HashMap<>();
    board.put(1, empty);
    board.put(2, empty);
    board.put(3, empty);
    board.put(4, empty);
    board.put(5, empty);
    board.put(6, empty);
    board.put(7, empty);
    board.put(8, empty);
    board.put(9, empty);

    MockGameReport report = new MockGameReport();
    report.board = board;
    report.currentPlayer = "Player 1";
    report.lastPlayer = "";
    report.currentState = "ready";
    report.result = "";
    report.winner = "";
    return report;
  }

  public static MockGameReport inProgressGame() {
    Map<Integer, Mark> board = new HashMap<>();
    board.put(1, one);
    board.put(2, two);
    board.put(3, empty);
    board.put(4, empty);
    board.put(5, empty);
    board.put(6, empty);
    board.put(7, empty);
    board.put(8, empty);
    board.put(9, empty);

    MockGameReport report = new MockGameReport();
    report.board = board;
    report.currentPlayer = "Player 1";
    report.lastPlayer = "Player 2";
    report.currentState = "playing";
    report.result = "";
    report.winner = "";
    return report;
  }

  public static MockGameReport tiedGame() {
    Map<Integer, Mark> board = new HashMap<>();
    board.put(1, one);
    board.put(2, one);
    board.put(3, two);
    board.put(4, two);
    board.put(5, one);
    board.put(6, one);
    board.put(7, one);
    board.put(8, two);
    board.put(9, two);

    MockGameReport report = new MockGameReport();
    report.board = board;
    report.currentPlayer = "Player 2";
    report.lastPlayer = "Player 1";
    report.currentState = "game_over";
    report.result = "tied";
    report.winner = "";
    return report;
  }

  public static MockGameReport playerOneWins() {
    Map<Integer, Mark> board = new HashMap<>();
    board.put(1, one);
    board.put(2, one);
    board.put(3, one);
    board.put(4, two);
    board.put(5, two);
    board.put(6, empty);
    board.put(7, empty);
    board.put(8, empty);
    board.put(9, empty);

    MockGameReport report = new MockGameReport();
    report.board = board;
    report.currentPlayer = "Player 2";
    report.lastPlayer = "Player 1";
    report.currentState = "game_over";
    report.result = "won";
    report.winner = "Player 1";
    return report;
  }

  public static MockGameReport playerTwoWins() {
    Map<Integer, Mark> board = new HashMap<>();
    board.put(1, one);
    board.put(2, one);
    board.put(3, empty);
    board.put(4, two);
    board.put(5, two);
    board.put(6, two);
    board.put(7, empty);
    board.put(8, empty);
    board.put(9, one);

    MockGameReport report = new MockGameReport();
    report.board = board;
    report.currentPlayer = "Player 1";
    report.lastPlayer = "Player 2";
    report.currentState = "game_over";
    report.result = "won";
    report.winner = "Player 2";
    return report;
  }

  @Override
  public Map<Integer, Mark> getCurrentBoard() {
    return board;
  }

  @Override
  public String getCurrentPlayer() {
    return currentPlayer;
  }

  @Override
  public String getLastPlayer() {
    return lastPlayer;
  }

  @Override
  public String getCurrentState() {
    return currentState;
  }

  @Override
  public String getResult() {
    return result;
  }

  @Override
  public String getWinner() {
    return winner;
  }
}
