package uk.ashleybye.tictactoe.graphical;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import uk.ashleybye.tictactoe.graphical.controller.ConfigureGameMenuController;
import uk.ashleybye.tictactoe.graphical.controller.GameController;
import uk.ashleybye.tictactoe.graphical.controller.MainMenuController;

public class ViewManager {

  private static ViewManager viewManager = null;
  private Map<ContextElement, Object> scenes = new HashMap<>();
  private Map<ContextElement, Object> controllers = new HashMap<>();

  private ViewManager() throws IOException {
    loadElementFromFXML(ContextElement.MAIN_MENU, "fxml/MainMenu.fxml");
    loadElementFromFXML(ContextElement.PLAY_GAME, "fxml/Game.fxml");
    loadElementFromFXML(ContextElement.CONFIGURE_GAME, "fxml/ConfigureGameMenu.fxml");
  }

  public static void initialiseViewManager() throws IOException {
    if (viewManager == null) {
      viewManager = new ViewManager();
    }
  }

  // For testing purposes only.
  static void nullifyViewManager() {
    viewManager = null;
  }

  public static ViewManager getViewManager() {
    if (viewManager == null) {
      throw new RuntimeException("ViewManager has not been initialised");
    }
    return viewManager;
  }

  private void loadElementFromFXML(ContextElement element, String path) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(path));
    scenes.put(element, new Scene(loader.load()));
    controllers.put(element, loader.getController());
  }

  public ConfigureGameMenuController getConfigureGameMenuController() {
    return (ConfigureGameMenuController) controllers.get(ContextElement.CONFIGURE_GAME);
  }

  public Scene getConfigureGameMenuScene() {
    return (Scene) scenes.get(ContextElement.CONFIGURE_GAME);
  }

  public GameController getGameController() {
    return (GameController) controllers.get(ContextElement.PLAY_GAME);
  }

  public Scene getGameScene() {
    return (Scene) scenes.get(ContextElement.PLAY_GAME);
  }

  public MainMenuController getMainMenuController() {
    return (MainMenuController) controllers.get(ContextElement.MAIN_MENU);
  }

  public Scene getMainMenuScene() {
    return (Scene) scenes.get(ContextElement.MAIN_MENU);
  }

  private enum ContextElement {
    CONFIGURE_GAME, MAIN_MENU, PLAY_GAME,
  }
}
