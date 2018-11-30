package uk.ashleybye.tictactoe.graphical.controller;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import uk.ashleybye.tictactoe.graphical.ClientContext;
import uk.ashleybye.tictactoe.graphical.JavaFXTest;

public class MainMenuControllerTest extends JavaFXTest {

  @Start
  void onStart(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/MainMenu.fxml"));
    ClientContext.setMainMenuView(new Scene(loader.load()), loader.getController());
    ClientContext.getMainMenuController().initialise(stage);

    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/TicTacToe.fxml"));
    ClientContext.setGameView(new Scene(loader.load()), loader.getController());

    ClientContext.getMainMenuController().initialise(stage);
    stage.setScene(ClientContext.getMainMenuScene());
    stage.show();
  }

  @Test
  void testClickingOnPlayLaunchesGame(FxRobot robot) {
    robot.clickOn("#play");

    robot.clickOn("#square1");

    verifyThat("#square1", hasText("X"));
  }
}
