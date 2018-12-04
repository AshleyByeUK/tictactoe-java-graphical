package uk.ashleybye.tictactoe.graphical;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.graphical.controller.MainMenuController;

public class GraphicalClient extends Application {

  public GraphicalClient() throws IOException {
    ViewManager.initialiseViewManager();
  }

  public static void launchClient() {
    launch();
  }

  @Override
  public void start(Stage stage) {
    ViewManager context = ViewManager.getViewManager();
    stage.setTitle("Tic Tac Toe");
    stage.setScene(context.getMainMenuScene());
    MainMenuController mainMenu = context.getMainMenuController();
    mainMenu.initialise(stage);
    stage.show();
  }
}
