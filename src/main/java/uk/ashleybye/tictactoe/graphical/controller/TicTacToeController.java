package uk.ashleybye.tictactoe.graphical.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.core.ClientInterface;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.GameReport;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.graphical.ClientContext;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;
import uk.ashleybye.tictactoe.graphical.component.GraphicalSquare;

public class TicTacToeController implements ClientInterface {

  private int nextMove;
  private Game game;
  private Stage stage;
  private List<GraphicalSquare> squares;
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
  public Button restart = null;
  public Button mainMenu = null;

  public void initialise(Stage stage) {
    setStage(stage);
    setupSquares();
    setupButtons();
  }

  private void setStage(Stage stage) {
    this.stage = stage;
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

  private void setupButtons() {
    restart.setOnAction(click -> startGame());
    mainMenu.setOnAction(click -> returnToMainMenu());
  }

  public void startGame() {
    initialiseGame();
    renderGame(game.generateGameReport());
  }

  private void returnToMainMenu() {
    stage.setScene(ClientContext.getMainMenuScene());
    stage.show();
  }

  private void initialiseGame() {
    Player playerOne = new HumanPlayer(new GraphicalMark("X"), "Player 1", this);
    Player playerTwo = new HumanPlayer(new GraphicalMark("O"), "Player 2", this);
    game = new Game(playerOne, playerTwo, new GraphicalMark(" "));
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
        status.setText(String.format("Game over!\n%s won!", gameReport.getWinner()));
      } else {
        status.setText("Game over!\nIt's a tie.");
      }
    } else {
      status.setText(" ");
    }
  }
}
