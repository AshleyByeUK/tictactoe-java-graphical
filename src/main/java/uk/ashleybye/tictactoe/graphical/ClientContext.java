package uk.ashleybye.tictactoe.graphical;

import javafx.scene.Scene;
import uk.ashleybye.tictactoe.graphical.controller.MainMenuController;
import uk.ashleybye.tictactoe.graphical.controller.TicTacToeController;

public class ClientContext {

  private static Scene mainMenuScene;
  private static MainMenuController mainMenuController;
  private static Scene ticTacToeScene;
  private static TicTacToeController tictacToeController;

  public static void setMainMenuView(Scene scene, MainMenuController controller) {
    ClientContext.mainMenuScene = scene;
    ClientContext.mainMenuController = controller;
  }

  public static Scene getMainMenuScene() {
    return mainMenuScene;
  }

  public static MainMenuController getMainMenuController() {
    return mainMenuController;
  }

  public static void setGameView(Scene scene, TicTacToeController controller) {
    ClientContext.ticTacToeScene = scene;
    ClientContext.tictacToeController = controller;
  }

  public static Scene getTicTacToeScene() {
    return ticTacToeScene;
  }

  public static TicTacToeController getTictacToeController() {
    return tictacToeController;
  }
}
