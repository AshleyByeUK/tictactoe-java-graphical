package uk.ashleybye.tictactoe.graphical;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.graphical.controller.MainMenuController;

public class GraphicalClient extends Application {

  public GraphicalClient() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("fxml/MainMenu.fxml"));
    ClientContext.setMainMenuView(new Scene(loader.load()), loader.getController());

    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("fxml/Game.fxml"));
    ClientContext.setGameView(new Scene(loader.load()), loader.getController());

    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("fxml/ConfigureGameMenu.fxml"));
    ClientContext.setConfigureGameMenuView(new Scene(loader.load()), loader.getController());
  }

  public static void launchClient() {
    launch();
  }

  @Override
  public void start(Stage stage) {
      stage.setScene(ClientContext.getMainMenuScene());
      MainMenuController mainMenu = ClientContext.getMainMenuController();
      mainMenu.initialise(stage);
      stage.show();
  }
}
