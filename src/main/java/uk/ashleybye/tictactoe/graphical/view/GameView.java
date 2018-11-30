package uk.ashleybye.tictactoe.graphical.view;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.GameReport;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.graphical.component.JFXGame;
import uk.ashleybye.tictactoe.graphical.component.JFXMark;

public class GameView extends Parent {

  private JFXGame jfxGame;

  public GameView(Stage stage) {
    super();
    jfxGame = new JFXGame();
    this.getChildren().add(jfxGame);
  }

  public void run(Task<Void> gameRunner) {
    new Thread(gameRunner).start();
  }

  public Task<Void> getGameRunner() {
    Player playerOne = new HumanPlayer(new JFXMark("X"), "Player 1", jfxGame);
    Player playerTwo = new HumanPlayer(new JFXMark("O"), "Player 2", jfxGame);

    return new Task<>() {
      @Override
      protected Void call() {
        Game ttt = new Game(playerOne, playerTwo, new JFXMark(" "));
        while (!ttt.isGameOver()) {
          if (jfxGame.hasMove()) {
            ttt = ttt.playNextTurn();
            final GameReport gameReport = ttt.generateGameReport();
            Platform.runLater(() -> jfxGame.renderGame(gameReport));
          }
        }
        return null;
      }
    };
  }
}
