package uk.ashleybye.tictactoe.graphical;

import javafx.scene.Scene;
import uk.ashleybye.tictactoe.graphical.controller.ConfigureGameMenuController;
import uk.ashleybye.tictactoe.graphical.controller.MainMenuController;
import uk.ashleybye.tictactoe.graphical.controller.GameController;

public class ClientContext {

  private static ConfigureGameMenuController configureGameMenuController;
  private static Scene configureGameMenuScene;
  private static GameController gameController;
  private static Scene gameScene;
  private static MainMenuController mainMenuController;
  private static Scene mainMenuScene;

  public static ConfigureGameMenuController getConfigureGameMenuController() {
    return configureGameMenuController;
  }

  public static Scene getConfigureGameMenuScene() {
    return configureGameMenuScene;
  }

  public static void setConfigureGameMenuView(Scene scene, ConfigureGameMenuController controller) {
    configureGameMenuScene = scene;
    configureGameMenuController = controller;
  }

  public static GameController getGameController() {
    return gameController;
  }

  public static Scene getGameScene() {
    return gameScene;
  }

  public static void setGameView(Scene scene, GameController controller) {
    ClientContext.gameScene = scene;
    ClientContext.gameController = controller;
  }

  public static MainMenuController getMainMenuController() {
    return mainMenuController;
  }

  public static Scene getMainMenuScene() {
    return mainMenuScene;
  }

  public static void setMainMenuView(Scene scene, MainMenuController controller) {
    ClientContext.mainMenuScene = scene;
    ClientContext.mainMenuController = controller;
  }
}
