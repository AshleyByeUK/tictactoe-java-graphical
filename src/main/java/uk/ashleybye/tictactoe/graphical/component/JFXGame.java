package uk.ashleybye.tictactoe.graphical.component;

import javafx.scene.Parent;
import uk.ashleybye.tictactoe.core.ClientInterface;
import uk.ashleybye.tictactoe.core.GameReport;

public class JFXGame extends Parent implements ClientInterface {

  private JFXBoard board;
  private int nextMove;
  private boolean hasMove = false;

  public JFXGame() {
    super();
    board = new JFXBoard(this);
    getChildren().add(board);
  }

  public JFXBoard getBoard() {
    return board;
  }

  public boolean hasMove() {
    return hasMove;
  }

  void receiveSquareClicked(int square) {
    hasMove = true;
    nextMove = square;
  }

  public int getPlayersMove() {
    hasMove = false;
    return nextMove;
  }

  @Override
  public void renderGame(GameReport gameReport) {
    board.update(gameReport.getCurrentBoard());
  }
}
