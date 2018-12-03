package uk.ashleybye.tictactoe.graphical.controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.graphical.ClientContext;

public class MainMenuController {

  private Stage stage;
  public Button play = null;
  public Button configure = null;

  public void initialise(Stage stage) {
    this.stage = stage;
    play.setOnAction(click -> handleClickOnPlay());
    configure.setOnAction(click -> handleClickOnConfigure());
  }

  private void handleClickOnPlay() {
    stage.setScene(ClientContext.getGameScene());
    stage.show();
    ClientContext.getGameController().initialise(stage);
    ClientContext.getGameController().startGame();
  }

  private void handleClickOnConfigure() {
    stage.setScene(ClientContext.getConfigureGameMenuScene());
    stage.show();
    ClientContext.getConfigureGameMenuController().initialise(stage);
  }
}
