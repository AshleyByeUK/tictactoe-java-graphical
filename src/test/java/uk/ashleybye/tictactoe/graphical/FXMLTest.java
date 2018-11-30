package uk.ashleybye.tictactoe.graphical;

import javafx.stage.Stage;
import org.testfx.framework.junit5.Start;
import uk.ashleybye.tictactoe.graphical.component.JFXTest;
import uk.ashleybye.tictactoe.graphical.controller.TicTacToeController;

public class FXMLTest extends JFXTest {

  @Start
  void onStart(Stage stage) {
    TicTacToeController controller = new TicTacToeController();
    FXMLLoader loader = new FXMLLoader();
  }
}
