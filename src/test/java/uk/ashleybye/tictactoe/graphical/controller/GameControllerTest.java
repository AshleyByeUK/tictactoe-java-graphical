package uk.ashleybye.tictactoe.graphical.controller;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import uk.ashleybye.tictactoe.graphical.ClientContext;
import uk.ashleybye.tictactoe.graphical.GraphicalGameConfiguration;
import uk.ashleybye.tictactoe.graphical.JavaFXTest;

public class GameControllerTest extends JavaFXTest {

  @Start
  void onStart(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/MainMenu.fxml"));
    ClientContext.setMainMenuView(new Scene(loader.load()), loader.getController());
    ClientContext.getMainMenuController().initialise(stage);

    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/Game.fxml"));
    ClientContext.setGameView(new Scene(loader.load()), loader.getController());


    ClientContext.getGameController().initialise(stage);
    stage.setScene(ClientContext.getGameScene());
    stage.show();
  }

  @BeforeEach
  void setUp() {
    GraphicalGameConfiguration.resetToDefaults();
    ClientContext.getGameController().startGame();
  }

  @Test
  void testCanClickSquare(FxRobot robot) {
    robot.clickOn("#square1");

    verifyThat("#square1", hasText("X"));
  }

  @Test
  void testCannotMarkPreviouslyMarkedSquare(FxRobot robot) {
    robot.clickOn("#square1");
    verifyThat("#square1", hasText("X"));

    robot.clickOn("#square1");
    verifyThat("#square1", hasText("X"));
  }

  @Test
  void testPlayerOneWins(FxRobot robot) {
    robot.clickOn("#square1");
    verifyThat("#square1", hasText("X"));

    robot.clickOn("#square4");
    verifyThat("#square4", hasText("O"));

    robot.clickOn("#square2");
    verifyThat("#square2", hasText("X"));

    robot.clickOn("#square5");
    verifyThat("#square5", hasText("O"));

    robot.clickOn("#square3");
    verifyThat("#square3", hasText("X"));

    verifyThat("#status", hasText("Game over!\nPlayer 1 won!"));

    robot.clickOn("#square6");
    verifyThat("#square6", hasText(" "));
  }

  @Test
  void testPlayerTwoWins(FxRobot robot) {
    robot.clickOn("#square1");
    verifyThat("#square1", hasText("X"));

    robot.clickOn("#square4");
    verifyThat("#square4", hasText("O"));

    robot.clickOn("#square2");
    verifyThat("#square2", hasText("X"));

    robot.clickOn("#square5");
    verifyThat("#square5", hasText("O"));

    robot.clickOn("#square9");
    verifyThat("#square9", hasText("X"));

    robot.clickOn("#square6");
    verifyThat("#square6", hasText("O"));

    verifyThat("#status", hasText("Game over!\nPlayer 2 won!"));
  }

  @Test
  void testTieGame(FxRobot robot) {
    robot.clickOn("#square1");
    verifyThat("#square1", hasText("X"));

    robot.clickOn("#square3");
    verifyThat("#square3", hasText("O"));

    robot.clickOn("#square2");
    verifyThat("#square2", hasText("X"));

    robot.clickOn("#square4");
    verifyThat("#square4", hasText("O"));

    robot.clickOn("#square5");
    verifyThat("#square5", hasText("X"));

    robot.clickOn("#square8");
    verifyThat("#square8", hasText("O"));

    robot.clickOn("#square6");
    verifyThat("#square6", hasText("X"));

    robot.clickOn("#square9");
    verifyThat("#square9", hasText("O"));

    robot.clickOn("#square7");
    verifyThat("#square7", hasText("X"));

    verifyThat("#status", hasText("Game over!\nIt's a tie."));
  }

  @Test
  void testCanRestartGameDuringGamePlay(FxRobot robot) {
    robot.clickOn("#square1");
    verifyThat("#square1", hasText("X"));

    robot.clickOn("#restart");
    verifyThat("#status", hasText(" "));
    verifyThat("#square1", hasText(" "));

    robot.clickOn("#square1");
    verifyThat("#square1", hasText("X"));
  }

  @Test
  void testCanRestartGameAfterGameHasEnded(FxRobot robot) {
    robot.clickOn("#square1");
    verifyThat("#square1", hasText("X"));

    robot.clickOn("#square4");
    verifyThat("#square4", hasText("O"));

    robot.clickOn("#square2");
    verifyThat("#square2", hasText("X"));

    robot.clickOn("#square5");
    verifyThat("#square5", hasText("O"));

    robot.clickOn("#square3");
    verifyThat("#square3", hasText("X"));

    robot.clickOn("#restart");
    verifyThat("#status", hasText(" "));
    verifyThat("#square1", hasText(" "));

    robot.clickOn("#square1");
    verifyThat("#square1", hasText("X"));
  }

  @Test
  void testCanReturnToMainMenu(FxRobot robot) {
    robot.clickOn("#mainMenu");

    verifyThat("#play", hasText("Play"));
  }
}
