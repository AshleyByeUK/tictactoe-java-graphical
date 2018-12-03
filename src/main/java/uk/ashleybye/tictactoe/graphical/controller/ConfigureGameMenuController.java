package uk.ashleybye.tictactoe.graphical.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;
import uk.ashleybye.tictactoe.graphical.ClientContext;
import uk.ashleybye.tictactoe.graphical.GraphicalGameConfiguration;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;

public class ConfigureGameMenuController {

  private GraphicalGameConfiguration configuration;
  private ObservableList<String> playerTypeOptions = FXCollections.observableArrayList(
      "Easy Computer", "Hard Computer", "User");
  public TextField playerOneName = null;
  public TextField playerOneMark = null;
  public ComboBox<String> playerOneType = null;
  public TextField playerTwoName = null;
  public TextField playerTwoMark = null;
  public ComboBox<String> playerTwoType = null;
  public Button mainMenu = null;

  public void initialise(Stage stage) {
    loadCurrentConfiguration();
    configureChangeListeners(stage);
  }

  private void loadCurrentConfiguration() {
    configuration = GraphicalGameConfiguration.getCurrentConfiguration();
    loadCurrentPlayerConfiguration(1, playerOneName, playerOneMark, playerOneType);
    loadCurrentPlayerConfiguration(2, playerTwoName, playerTwoMark, playerTwoType);
  }

  private void loadCurrentPlayerConfiguration(
      int playerNumber, TextField nameField, TextField markField, ComboBox<String> typeComboBox) {
    PlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(playerNumber);
    nameField.textProperty().setValue(playerConfiguration.getPlayerName());
    markField.textProperty()
        .setValue(((GraphicalMark) playerConfiguration.getPlayerMark()).getText());
    typeComboBox.setItems(playerTypeOptions);
    typeComboBox.getSelectionModel().select(indexForPlayerType(playerConfiguration.getPlayerType()));
  }

  private int indexForPlayerType(String playerType) {
    int index;
    switch (playerType) {
      case "easy":
        index = 0;
        break;
      case "hard":
        index = 1;
        break;
      default:
        index = 2;
        break;
    }
    return index;
  }

  private void configureChangeListeners(Stage stage) {
    mainMenu.setOnAction(click-> handleClickOnMainMenu(stage));
    configureComponentChangeListenersForPlayer(1, playerOneName, playerOneMark, playerOneType);
    configureComponentChangeListenersForPlayer(2, playerTwoName, playerTwoMark, playerTwoType);
  }

  private void handleClickOnMainMenu(Stage stage) {
    stage.setScene(ClientContext.getMainMenuScene());
    stage.show();
  }

  private void configureComponentChangeListenersForPlayer(int playerNumber, TextField nameField, TextField markField,
      ComboBox<String> typeComboBox) {
    configurePlayerNameChangeListener(playerNumber, nameField);
    configurePlayerMarkChangeListener(playerNumber, markField);
    configurePlayerTypeChangeListener(playerNumber, typeComboBox);
  }

  private void configurePlayerNameChangeListener(int playerNumber, TextField nameField) {
    nameField.textProperty().addListener((obs, oldName, newName) -> {
      configuration.setPlayerName(playerNumber, newName.strip());
    });
  }

  private void configurePlayerMarkChangeListener(int playerNumber, TextField markField) {
    markField.focusedProperty().addListener((obs, oldMark, newMark) -> {
      if (!newMark) {
        if (markField.getText().matches("[A-Za-z]")) {
          configuration
              .setPlayerMark(playerNumber, new GraphicalMark(markField.textProperty().getValue().toUpperCase()));
        } else {
          markField.textProperty()
              .setValue(((GraphicalMark) configuration.getPlayerConfiguration(playerNumber).getPlayerMark()).getText());
        }
      }
    });
  }

  private void configurePlayerTypeChangeListener(int playerNumber, ComboBox<String> typeComboBox) {
    typeComboBox.valueProperty().addListener((observable, oldType, newType) -> {
      String type;
      switch (newType) {
        case "Easy Computer":
          type = "easy";
          break;
        case "Hard Computer":
          type = "hard";
          break;
        default:
          type = "human";
          break;
      }
      configuration.setPlayerType(playerNumber, type);
    });
  }
}
