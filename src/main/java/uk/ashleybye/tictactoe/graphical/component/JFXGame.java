package uk.ashleybye.tictactoe.graphical.component;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import uk.ashleybye.tictactoe.core.ClientInterface;
import uk.ashleybye.tictactoe.core.GameReport;

public class JFXGame extends VBox implements ClientInterface {

  private JFXBoard board;
  private int nextMove;
  private boolean hasMove = false;
  private Label statusLabel;

  public JFXGame() {
    super();
    statusLabel = new Label();
    statusLabel.setId("status");
    statusLabel.setMinSize(100, 40);
    board = new JFXBoard(this);
    getChildren().addAll(board, statusLabel);
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
    String status = "";
    if (gameReport.getCurrentState().equals("game_over")) {
      if (gameReport.getResult().equals("tied")) {
        status = "Game over!\nIt's a tie.";
      } else {
        status = String.format("Game over!\n%s won!", gameReport.getWinner());
      }
    }
    statusLabel.setText(status);
    board.update(gameReport.getCurrentBoard());
  }
}
