package uk.ashleybye.tictactoe.graphical.view;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import uk.ashleybye.tictactoe.graphical.component.JFXTest;

class GameViewTest extends JFXTest {

  private GameView gameView;

  @Start
  void onStart(Stage stage) {
    gameView = new GameView(stage);
    Scene scene = new Scene(gameView);
    stage.setScene(scene);
    stage.show();
  }

  @Test
  void testPlayerOneWins(FxRobot robot) {
    var gameRunner = gameView.getGameRunner();
    gameView.run(gameRunner);

    robot.clickOn("#square-1");
    verifyThat("#square-1", hasText("X"));

    robot.clickOn("#square-4");
    verifyThat("#square-4", hasText("O"));

    robot.clickOn("#square-2");
    verifyThat("#square-2", hasText("X"));

    robot.clickOn("#square-5");
    verifyThat("#square-5", hasText("O"));

    robot.clickOn("#square-3");
    verifyThat("#square-3", hasText("X"));

    verifyThat("#status", hasText("Game over!\nPlayer 1 won!"));
  }

  @Test
  void testPlayerTwoWins(FxRobot robot) {
    var gameRunner = gameView.getGameRunner();
    gameView.run(gameRunner);

    robot.clickOn("#square-1");
    verifyThat("#square-1", hasText("X"));

    robot.clickOn("#square-4");
    verifyThat("#square-4", hasText("O"));

    robot.clickOn("#square-2");
    verifyThat("#square-2", hasText("X"));

    robot.clickOn("#square-5");
    verifyThat("#square-5", hasText("O"));

    robot.clickOn("#square-9");
    verifyThat("#square-9", hasText("X"));

    robot.clickOn("#square-6");
    verifyThat("#square-6", hasText("O"));

    verifyThat("#status", hasText("Game over!\nPlayer 2 won!"));
  }

  @Test
  void testTieGame(FxRobot robot) {
    var gameRunner = gameView.getGameRunner();
    gameView.run(gameRunner);

    robot.clickOn("#square-1");
    verifyThat("#square-1", hasText("X"));

    robot.clickOn("#square-3");
    verifyThat("#square-3", hasText("O"));

    robot.clickOn("#square-2");
    verifyThat("#square-2", hasText("X"));

    robot.clickOn("#square-4");
    verifyThat("#square-4", hasText("O"));

    robot.clickOn("#square-5");
    verifyThat("#square-5", hasText("X"));

    robot.clickOn("#square-8");
    verifyThat("#square-8", hasText("O"));

    robot.clickOn("#square-6");
    verifyThat("#square-6", hasText("X"));

    robot.clickOn("#square-9");
    verifyThat("#square-9", hasText("O"));

    robot.clickOn("#square-7");
    verifyThat("#square-7", hasText("X"));

    verifyThat("#status", hasText("Game over!\nIt's a tie."));
  }
}
