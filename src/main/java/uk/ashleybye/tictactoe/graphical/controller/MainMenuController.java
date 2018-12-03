package uk.ashleybye.tictactoe.graphical.controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.graphical.ViewManager;

public class MainMenuController {

  private Stage stage;
  public Button play = null;
  public Button configure = null;
  private ViewManager viewManager;

  public void initialise(Stage stage) {
    this.stage = stage;
    play.setOnAction(click -> handleClickOnPlay());
    configure.setOnAction(click -> handleClickOnConfigure());
    viewManager = ViewManager.getViewManager();
  }

  private void handleClickOnPlay() {
    stage.setScene(viewManager.getGameScene());
    stage.show();
    viewManager.getGameController().initialise(stage);
    viewManager.getGameController().startGame();
  }

  private void handleClickOnConfigure() {
    stage.setScene(viewManager.getConfigureGameMenuScene());
    stage.show();
    viewManager.getConfigureGameMenuController().initialise(stage);
  }
}
