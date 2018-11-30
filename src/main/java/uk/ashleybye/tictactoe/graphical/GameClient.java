package uk.ashleybye.tictactoe.graphical;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;
import uk.ashleybye.tictactoe.graphical.controller.TicTacToeController;

public class GameClient extends Application {

  public static void startGameClient() {
    launch();
  }

  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("fxml/TicTacToe.fxml"));

      Scene scene = new Scene(loader.load());
      stage.setScene(scene);

      TicTacToeController controller = loader.getController();
      Player playerOne = new HumanPlayer(new GraphicalMark("X"), "Player 1", controller);
      Player playerTwo = new HumanPlayer(new GraphicalMark("O"), "Player 2", controller);
      Game game = new Game(playerOne, playerTwo, new GraphicalMark(" "));
      controller.setupGame(game);
      stage.show();
    } catch (IOException ex) {
      System.out.println("Could not load FXML file from 'fxml/TicTacToe.fxml'.");
    }
  }
}
