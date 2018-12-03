package uk.ashleybye.tictactoe.graphical.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.core.ClientInterface;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.GameConfiguration;
import uk.ashleybye.tictactoe.core.GameReport;
import uk.ashleybye.tictactoe.core.PlayerFactory;
import uk.ashleybye.tictactoe.core.board.Board.SquareUnavailable;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;
import uk.ashleybye.tictactoe.graphical.ViewManager;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;
import uk.ashleybye.tictactoe.graphical.component.GraphicalSquare;
import uk.ashleybye.tictactoe.graphical.game.GraphicalGameConfiguration;
import uk.ashleybye.tictactoe.graphical.game.GraphicalPlayerFactory;

public class GameController implements ClientInterface {

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
  private int nextMove;
  private Game game;
  private List<GraphicalSquare> squares;

  public void initialise(Stage stage) {
    setupSquares();
    setupButtons(stage);
  }

  private void setupSquares() {
    squares = Arrays.asList(
        square1, square2, square3, square4, square5, square6, square7, square8, square9);
    IntStream
        .range(1, 10)
        .forEach(position -> squares.get(position - 1).setOnAction(click -> handleSquareClicked(position)));
  }

  private void handleSquareClicked(int square) {
    try {
      nextMove = square;
      game = game.playNextTurn();
      if (game.computerHasNextTurn()) {
        game = game.playNextTurn();
      }
      renderGame(game.generateGameReport());
    } catch (SquareUnavailable ex) {
      // Do nothing.
    }
  }

  private void setupButtons(Stage stage) {
    restart.setOnAction(click -> startGame());
    mainMenu.setOnAction(click -> returnToMainMenu(stage));
  }

  public void startGame() {
    initialiseGame();
    while (game.computerHasNextTurn()) {
      game = game.playNextTurn();
    }
    renderGame(game.generateGameReport());
  }

  private void initialiseGame() {
    GameConfiguration configuration = GraphicalGameConfiguration.getCurrentConfiguration();
    PlayerConfiguration p1Config = configuration.getPlayerConfiguration(1);
    PlayerConfiguration p2Config = configuration.getPlayerConfiguration(2);
    PlayerFactory playerFactory = new GraphicalPlayerFactory(this);
    Player playerOne = playerFactory.make(p1Config.getPlayerType(), p1Config.getPlayerName(), p1Config.getPlayerMark());
    Player playerTwo = playerFactory.make(p2Config.getPlayerType(), p2Config.getPlayerName(), p2Config.getPlayerMark());
    game = new Game(playerOne, playerTwo, configuration.getEmptyMark());
  }

  private void returnToMainMenu(Stage stage) {
    ViewManager viewManager = ViewManager.getViewManager();
    stage.setScene(viewManager.getMainMenuScene());
    stage.show();
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
