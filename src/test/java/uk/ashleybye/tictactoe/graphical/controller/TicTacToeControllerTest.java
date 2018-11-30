package uk.ashleybye.tictactoe.graphical.controller;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;
import uk.ashleybye.tictactoe.graphical.JFXTest;

public class TicTacToeControllerTest extends JFXTest {

  @Start
  void onStart(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/TicTacToe.fxml"));

    Scene scene = new Scene(loader.load());
    stage.setScene(scene);

    TicTacToeController controller = loader.getController();
    Player playerOne = new HumanPlayer(new GraphicalMark("X"), "Player 1", controller);
    Player playerTwo = new HumanPlayer(new GraphicalMark("O"), "Player 2", controller);
    Game game = new Game(playerOne, playerTwo, new GraphicalMark(" "));
    controller.playGame(game);
    stage.show();
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
}
