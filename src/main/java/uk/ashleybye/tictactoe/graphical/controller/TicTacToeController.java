package uk.ashleybye.tictactoe.graphical.controller;

import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Label;
import uk.ashleybye.tictactoe.core.ClientInterface;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.GameReport;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;
import uk.ashleybye.tictactoe.graphical.component.GraphicalSquare;

public class TicTacToeController implements ClientInterface {

  private int nextMove;
  private Game game;
  public GraphicalSquare square1 = null;
  public GraphicalSquare square2 = null;
  public GraphicalSquare square3 = null;
  public GraphicalSquare square4 = null;
  public GraphicalSquare square5 = null;
  public GraphicalSquare square6 = null;
  public GraphicalSquare square7 = null;
  public GraphicalSquare square8 = null;
  public GraphicalSquare square9 = null;
  public Label status = null;
  private List<GraphicalSquare> squares;

  public void setupGame(Game game) {
    this.game = game;
    squares = Arrays.asList(square1, square2, square3, square4, square5, square6, square7, square8, square9);
    for (int square = 0; square < squares.size(); square++) {
      final int position = square + 1;
      squares.get(square).setOnAction(click -> handleClick(position));
    }
  }

  private void handleClick(int square) {
    try {
      nextMove = square;
      game = game.playNextTurn();
      renderGame(game.generateGameReport());
    } catch (SquareUnavailable ex) {
      // Do nothing.
    }
  }

  @Override
  public int getPlayersMove() {
    return nextMove;
  }

  @Override
  public void renderGame(GameReport gameReport) {
    var marks = gameReport.getCurrentBoard();
    for (int square = 0; square < squares.size(); square++) {
      squares.get(square).setMark((GraphicalMark) marks.get(square + 1));
    }
    if (gameReport.getCurrentState().equals("game_over")) {
      if (gameReport.getResult().equals("won")) {
        status.setText(String.format("Game over!\n%s won!",gameReport.getWinner()));
      } else {
        status.setText("Game over!\nIt's a tie.");
      }
    }
  }
}
