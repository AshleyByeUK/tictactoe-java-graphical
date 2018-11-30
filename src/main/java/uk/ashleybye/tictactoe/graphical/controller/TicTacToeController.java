package uk.ashleybye.tictactoe.graphical.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
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

  public void playGame(Game game) {
    this.game = game;
    setupSquares();
  }

  private void setupSquares() {
    squares = Arrays.asList(
        square1, square2, square3, square4, square5, square6, square7, square8, square9);
    IntStream
        .range(1, 10)
        .forEach(position -> squares.get(position - 1).setOnAction(click -> handleClick(position)));
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
    renderBoard(gameReport);
    renderStatus(gameReport);
  }

  private void renderBoard(GameReport gameReport) {
    var marks = gameReport.getCurrentBoard();
    IntStream
        .range(1, 10)
        .forEach(position -> squares.get(position - 1).setMark((GraphicalMark) marks.get(position)));
  }

  private void renderStatus(GameReport gameReport) {
    if (gameReport.getCurrentState().equals("game_over")) {
      if (gameReport.getResult().equals("won")) {
        status.setText(String.format("Game over!\n%s won!",gameReport.getWinner()));
      } else {
        status.setText("Game over!\nIt's a tie.");
      }
    }
  }
}
