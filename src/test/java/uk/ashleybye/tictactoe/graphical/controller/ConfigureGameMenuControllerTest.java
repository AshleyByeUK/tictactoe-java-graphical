package uk.ashleybye.tictactoe.graphical.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import uk.ashleybye.tictactoe.core.GameConfiguration;
import uk.ashleybye.tictactoe.graphical.JavaFXTest;
import uk.ashleybye.tictactoe.graphical.ViewManager;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;
import uk.ashleybye.tictactoe.graphical.game.GraphicalGameConfiguration;

class ConfigureGameMenuControllerTest extends JavaFXTest {

  @Start
  void onStart(Stage stage) throws Exception {
    GraphicalGameConfiguration.resetToDefaults();
    ViewManager.initialiseViewManager();
    ViewManager viewManager = ViewManager.getViewManager();
    viewManager.getConfigureGameMenuController().initialise(stage);
    stage.setScene(viewManager.getConfigureGameMenuScene());
    stage.show();
  }

  @Test
  void testCanReturnToMainMenu(FxRobot robot) {
    robot.clickOn("#mainMenu");

    verifyThat("#configure", hasText("Configure Game"));
  }

  @Test
  void testShowsDefaultConfigurationValues(FxRobot robot) {
    assertEquals("Player 1", robot.lookup("#playerOneName").queryTextInputControl().getText());
    assertEquals("X", robot.lookup("#playerOneMark").queryTextInputControl().getText());
    assertEquals("User",
        robot.lookup("#playerOneType").queryComboBox().getSelectionModel().selectedItemProperty().getValue());
    assertEquals("Player 2", robot.lookup("#playerTwoName").queryTextInputControl().getText());
    assertEquals("O", robot.lookup("#playerTwoMark").queryTextInputControl().getText());
    assertEquals("User",
        robot.lookup("#playerTwoType").queryComboBox().getSelectionModel().selectedItemProperty().getValue());
  }

  @Test
  void testCanConfigurePlayerOneNameAndMark(FxRobot robot) {
    robot.clickOn("#playerOneName").eraseText(10).type(KeyCode.A, 5);
    robot.clickOn("#playerOneMark").eraseText(1).type(KeyCode.A);
    robot.clickOn("#mainMenu");

    GameConfiguration configuration = GraphicalGameConfiguration.getCurrentConfiguration();
    assertEquals("aaaaa", configuration.getPlayerConfiguration(1).getPlayerName());
    assertEquals(new GraphicalMark("A"), configuration.getPlayerConfiguration(1).getPlayerMark());
  }

  @Test
  void testCanConfigurePlayerOneType(FxRobot robot) {
    GameConfiguration configuration = GraphicalGameConfiguration.getCurrentConfiguration();

    robot.clickOn("#playerOneType").type(KeyCode.UP, KeyCode.UP, KeyCode.ENTER);
    assertEquals("easy", configuration.getPlayerConfiguration(1).getPlayerType());

    robot.clickOn("#playerOneType").type(KeyCode.DOWN, KeyCode.ENTER);
    assertEquals("hard", configuration.getPlayerConfiguration(1).getPlayerType());

    robot.clickOn("#playerOneType").type(KeyCode.DOWN, KeyCode.ENTER);
    assertEquals("human", configuration.getPlayerConfiguration(1).getPlayerType());
  }

  @Test
  void testCanConfigurePlayerTwoNameAndMark(FxRobot robot) {
    robot.clickOn("#playerTwoName").eraseText(10).type(KeyCode.B, 5);
    robot.clickOn("#playerTwoMark").eraseText(1).type(KeyCode.B);
    robot.clickOn("#mainMenu");

    GameConfiguration configuration = GraphicalGameConfiguration.getCurrentConfiguration();
    assertEquals("bbbbb", configuration.getPlayerConfiguration(2).getPlayerName());
    assertEquals(new GraphicalMark("B"), configuration.getPlayerConfiguration(2).getPlayerMark());
  }

  @Test
  void testCanConfigurePlayerTwoType(FxRobot robot) {
    GameConfiguration configuration = GraphicalGameConfiguration.getCurrentConfiguration();

    robot.clickOn("#playerTwoType").type(KeyCode.UP, KeyCode.UP, KeyCode.ENTER);
    assertEquals("easy", configuration.getPlayerConfiguration(2).getPlayerType());

    robot.clickOn("#playerTwoType").type(KeyCode.DOWN, KeyCode.ENTER);
    assertEquals("hard", configuration.getPlayerConfiguration(2).getPlayerType());

    robot.clickOn("#playerTwoType").type(KeyCode.DOWN, KeyCode.ENTER);
    assertEquals("human", configuration.getPlayerConfiguration(2).getPlayerType());
  }

  @Test
  void testEasyComputerPlayerTypeIsSetWhenReturningToConfigurationMenu(FxRobot robot) {
    robot.clickOn("#playerOneType").type(KeyCode.UP, KeyCode.UP, KeyCode.ENTER);
    robot.clickOn("#mainMenu");
    robot.clickOn("#configure");

    assertEquals("Easy Computer",
        robot.lookup("#playerOneType").queryComboBox().getSelectionModel().selectedItemProperty().getValue());
  }

  @Test
  void testHardComputerPlayerTypeIsSetWhenReturningToConfigurationMenu(FxRobot robot) {
    robot.clickOn("#playerOneType").type(KeyCode.UP, KeyCode.ENTER);
    robot.clickOn("#mainMenu");
    robot.clickOn("#configure");

    assertEquals("Hard Computer",
        robot.lookup("#playerOneType").queryComboBox().getSelectionModel().selectedItemProperty().getValue());
  }
}
