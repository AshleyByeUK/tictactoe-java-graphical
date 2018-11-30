package uk.ashleybye.tictactoe.graphical;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.graphical.view.GameView;

public class GameClient extends Application {

  public static void startApp() {
    launch();
  }

  @Override
  public void start(Stage stage) {
    GameView gameView = new GameView(stage);
    Scene scene = new Scene(gameView, 100, 130);
    stage.setScene(scene);
    stage.show();
    gameView.run(gameView.getGameRunner());
  }
}
