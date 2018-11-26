package uk.ashleybye.tictactoe;

import uk.ashleybye.tictactoe.console.ConfigurePlayerMenuItem;
import uk.ashleybye.tictactoe.console.ConsoleClient;
import uk.ashleybye.tictactoe.console.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.ConsoleMark;
import uk.ashleybye.tictactoe.console.ConsolePlayerConfiguration;
import uk.ashleybye.tictactoe.console.ConsolePlayerFactory;
import uk.ashleybye.tictactoe.console.GameConsole;
import uk.ashleybye.tictactoe.console.IOWrapper;
import uk.ashleybye.tictactoe.console.MainMenuItem;
import uk.ashleybye.tictactoe.console.PlayGameMenuItem;
import uk.ashleybye.tictactoe.core.PlayerFactory;

public class ConsoleApplication {

  public static void main(String[] args) {
    IOWrapper ioWrapper = new IOWrapper();
    GameConsole gameConsole = new GameConsole(ioWrapper);
    PlayerFactory playerFactory = new ConsolePlayerFactory(gameConsole);

    ConsolePlayerConfiguration playerOneConfiguration = new ConsolePlayerConfiguration();
    ConsolePlayerConfiguration playerTwoConfiguration = new ConsolePlayerConfiguration();

    playerOneConfiguration.setPlayerMark("X");
    playerOneConfiguration.setPlayerName("Player 1");
    playerOneConfiguration.setPlayerType(playerFactory.listPlayerTypes().get(0));

    playerTwoConfiguration.setPlayerMark("O");
    playerTwoConfiguration.setPlayerName("Player 2");
    playerTwoConfiguration.setPlayerType(playerFactory.listPlayerTypes().get(1));

    ConsoleGameConfiguration gameConfiguration = new ConsoleGameConfiguration();
    gameConfiguration.addPlayerConfiguration(1, playerOneConfiguration);
    gameConfiguration.addPlayerConfiguration(2, playerTwoConfiguration);
    gameConfiguration.setEmptyMark(ConsoleMark.emptyMark());

    MainMenuItem mainMenu = new MainMenuItem(gameConfiguration);
    ConfigurePlayerMenuItem configurePlayerOneMenuItem = new ConfigurePlayerMenuItem(mainMenu, gameConfiguration, 1, playerFactory);
    ConfigurePlayerMenuItem configurePlayerTwoMenuItem = new ConfigurePlayerMenuItem(mainMenu, gameConfiguration, 2, playerFactory);
    PlayGameMenuItem playGameMenuItem = new PlayGameMenuItem(mainMenu, gameConfiguration, playerFactory, gameConsole);
    mainMenu.setConfigurePlayerOneMenuItem(configurePlayerOneMenuItem);
    mainMenu.setConfigurePlayerTwoMenuItem(configurePlayerTwoMenuItem);
    mainMenu.setPlayGameMenuItem(playGameMenuItem);

    ConsoleClient consoleClient = new ConsoleClient(mainMenu, ioWrapper);
    consoleClient.start();
  }
}
