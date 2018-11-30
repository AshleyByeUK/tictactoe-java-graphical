package uk.ashleybye.tictactoe.graphical.controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.graphical.ClientContext;

public class MainMenuController {

  private Stage stage;
  public Button play = null;

  public void initialise(Stage stage) {
    this.stage = stage;
    play.setOnAction(click -> handleClickOnPlay());
  }

  private void handleClickOnPlay() {
    stage.setScene(ClientContext.getTicTacToeScene());
    stage.show();
    ClientContext.getTictacToeController().initialise(stage);
    ClientContext.getTictacToeController().startGame();
  }
}
