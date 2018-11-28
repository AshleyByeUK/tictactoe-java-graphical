package uk.ashleybye.tictactoe.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.TestHelpers;
import uk.ashleybye.tictactoe.core.board.Board.InvalidSquareNumber;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

public class MockTicTacToe extends TicTacToe {

  private MockPlayer playerOne = new MockPlayer(new MockPlayerOneMark(), "Player 1");
  private MockPlayer playerTwo = new MockPlayer(new MockPlayerTwoMark(), "Player 2");
  private List<Boolean> isGameOver = new ArrayList<>();
  private int numberOfTimesIsGameOverCalled = 0;
  private int getNumberOfTimesGenerateGameReportCalled = 0;
  private boolean throwInvalidSquareNumber = false;
  private boolean throwSquareUnavailable = false;

  public MockTicTacToe() {
    super(null, null, new MockEmptyMark());
  }

  @Override
  public List<Integer> listOpenPositions() {
    return Arrays.asList(4, 5, 6);
  }

  @Override
  public TicTacToe playNextTurn() {
    if (throwInvalidSquareNumber) {
      throw new InvalidSquareNumber();
    }
    if (throwSquareUnavailable) {
      throw new SquareUnavailable();
    }
    return this;
  }

  @Override
  public boolean isGameOver() {
    return isGameOver.get(numberOfTimesIsGameOverCalled++);
  }

  @Override
  public GameReport generateGameReport() {
    getNumberOfTimesGenerateGameReportCalled++;
    GameReport report = new GameReport();
    report.setCurrentBoard(TestHelpers.generateBoard("X - - - - - - - -"));
    report.setCurrentPlayer(playerTwo);
    report.setLastPlayer(playerOne);
    report.setCurrentState(GameState.PLAYING);
    report.setResult(false, false);
    return report;
  }

  public void addIsGameOver(Boolean isGameOver) {
    this.isGameOver.add(isGameOver);
  }

  public void setThrowInvalidSquareNumber() {
    throwInvalidSquareNumber = true;
  }

  public void setThrowSquareUnavailable() {
    throwSquareUnavailable = true;
  }

  public int getNumberOfTimesIsGameOverCalled() {
    return numberOfTimesIsGameOverCalled;
  }

  public int getNumberOfTimesGenerateGameReportCalled() {
    return getNumberOfTimesGenerateGameReportCalled;
  }
}
