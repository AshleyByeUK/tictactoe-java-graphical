package uk.ashleybye.tictactoe.ui.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import uk.ashleybye.tictactoe.core.GameReport;
import uk.ashleybye.tictactoe.core.HumanTurnPublisher;
import uk.ashleybye.tictactoe.core.HumanTurnSubscriber;
import uk.ashleybye.tictactoe.core.Mark;
import uk.ashleybye.tictactoe.core.UserInterface;
import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;

public class GameConsole implements HumanTurnPublisher, UserInterface {

  private static final String DRAW = "It's a draw.";
  private static final String EMPTY_STRING = "";
  private static final String GAME_OVER = "Game over!";
  private static final String INVALID_SQUARE = "That square is not available, try again %s %s";
  private static final String PLAYERS_TURN = "%s's turn %s";
  private static final String PROMPT = "> ";
  private static final String ROW_SPACER = "-----------";
  private static final String SQUARE_DELIMITER = "|";
  private static final String WELCOME = "Great, let's play a game of Tic Tac Toe!";
  private static final String WON = "won.";

  private final IOWrapper ioWrapper;
  private final List<GameReport> history = new ArrayList<>();
  private HumanTurnSubscriber subscriber;

  public GameConsole(IOWrapper ioWrapper) {
    this.ioWrapper = ioWrapper;
  }

  @Override
  public void subscribeToTurnNotifications(HumanTurnSubscriber humanPlayer) {
    this.subscriber = humanPlayer;
    handleMoveMade(getMoveFromUser());
  }

  @Override
  public void handleMoveMade(int position) {
    subscriber.notifyMoveMade(position);
  }

  private int getMoveFromUser() {
    try {
      return Integer.parseInt(ioWrapper.getInput());
    } catch (NumberFormatException e) {
      throw new InvalidSquareNumber();
    }
  }

  @Override
  public void renderGame(GameReport gameReport) {
    String text = getTextForGameReadySection(gameReport) + "\n\n"
        + getTextForGameBoardSection(gameReport)
        + getTextForMessageSection(gameReport)
        + getTextForGameOverSection(gameReport);
    history.add(gameReport);
    ioWrapper.clearScreen();
    ioWrapper.render(text);
  }

  private String getTextForGameReadySection(GameReport gameReport) {
    if (history.size() == 0 && gameReport.getCurrentState().equals("ready"))
      return WELCOME;
    else
      return "";
  }

  private String getTextForGameBoardSection(GameReport gameReport) {
    Map<Integer, Mark> board = gameReport.getCurrentBoard();
    return getTextForRows(board);
  }

  private String getTextForRows(Map<Integer, Mark> board) {
    String text = "";
    for (int row = 0; row < 3; row++)
      text += String.format("%s\n%s\n", getTextForRow(board, row), getTextForSpacerRow(row));
    return text;
  }

  private String getTextForRow(Map<Integer, Mark> board, int row) {
    String text = "";
    for (int col = 0; col < 3; col++)
      text += getTextForColumn(board, row, col);
    return text;
  }

  private String getTextForColumn(Map<Integer, Mark> board, int row, int col) {
    int squareNumber = (3 * row) + (col + 1);
    Mark mark = board.get(squareNumber);
    String content = mark.toString();
    if (mark.isEmpty())
      content = String.valueOf(squareNumber);
    String squareDelimiter = col < 2 ? SQUARE_DELIMITER : EMPTY_STRING;
    return String.format(" %s %s", content, squareDelimiter);
  }

  private String getTextForSpacerRow(int row) {
    return row < 2 ? ROW_SPACER : EMPTY_STRING;
  }

  private String getTextForMessageSection(GameReport gameReport) {
    if (thisTurnSameAsLastTurn(gameReport))
      return String.format(INVALID_SQUARE, gameReport.getCurrentPlayer(), PROMPT);
    else if (!gameReport.getCurrentState().equals("game_over"))
      return String.format(PLAYERS_TURN, gameReport.getCurrentPlayer(), PROMPT);
    else
      return EMPTY_STRING;
  }

  private boolean thisTurnSameAsLastTurn(GameReport gameReport) {
    return !gameReport.getCurrentState().equals("game_over")
        && history.size() > 0
        && history.get(history.size() - 1).getCurrentBoard().equals(gameReport.getCurrentBoard());
  }

  private String getTextForGameOverSection(GameReport gameReport) {
    if (gameReport.getCurrentState().equals("game_over"))
      return getTextForGameOver(gameReport);
    else
      return EMPTY_STRING;
  }

  private String getTextForGameOver(GameReport gameReport) {
    if (gameReport.getResult().equals("won"))
      return GAME_OVER + " " + gameReport.getWinner() + " " + WON;
    else
      return GAME_OVER + " " + DRAW;
  }
}
