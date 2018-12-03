package uk.ashleybye.tictactoe.graphical.controller;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import uk.ashleybye.tictactoe.graphical.JavaFXTest;
import uk.ashleybye.tictactoe.graphical.ViewManager;
import uk.ashleybye.tictactoe.graphical.game.GraphicalGameConfiguration;

public class MainMenuControllerTest extends JavaFXTest {

  @Start
  void onStart(Stage stage) throws Exception {
    GraphicalGameConfiguration.resetToDefaults();
    ViewManager.initialiseViewManager();
    ViewManager viewManager = ViewManager.getViewManager();
    viewManager.getMainMenuController().initialise(stage);
    stage.setScene(viewManager.getMainMenuScene());
    stage.show();
  }

  @Test
  void testClickingOnPlayLaunchesGame(FxRobot robot) {
    robot.clickOn("#play");

    robot.clickOn("#square1");

    verifyThat("#square1", hasText("X"));
  }

  @Test
  void testCanLaunchGameConfigurationMenu(FxRobot robot) {
    robot.clickOn("#configure");

    verifyThat("#title", hasText("Configure Game"));
  }
}
