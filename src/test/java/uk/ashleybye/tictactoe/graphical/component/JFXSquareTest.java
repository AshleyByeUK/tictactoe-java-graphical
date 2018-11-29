package uk.ashleybye.tictactoe.graphical.component;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

public class JFXSquareTest extends JFXTest {

  private MockJFXBoard board;
  private MockJFXBoard stageBoard;

  @Start
  void onStart(Stage stage) {
    stageBoard = new MockJFXBoard();
    JFXSquare square = new JFXSquare(1, stageBoard);
    square.setMark(new JFXMark(" "));
    square.setId("SUT");
    Scene scene = new Scene(square, 200, 200);
    stage.setScene(scene);
    stage.show();
  }

  @BeforeEach
  void setUp() {
    board = new MockJFXBoard();
  }

  @Test
  void testEmptySquareCanBeClicked() {
    JFXSquare square = new JFXSquare(1, board);
    square.setMark(new JFXMark(" "));

    assertEquals(" ", square.getText());

    square.handleClick(square);

    assertEquals(1, board.receivedClicksForSquare(1));
  }

  @Test
  void testMarkedSquareCannotBeClicked() {
    JFXSquare square = new JFXSquare(1, board);
    square.setMark(new JFXMark("X"));

    assertEquals("X", square.getText());

    square.handleClick(square);

    assertEquals(0, board.receivedClicksForSquare(1));
  }

  @Test
  void testSquareOnClickIsConnectedToHandleClick(FxRobot robot) {
    robot.clickOn("#SUT");

    assertEquals(1, stageBoard.receivedClicksForSquare(1));
  }
}
