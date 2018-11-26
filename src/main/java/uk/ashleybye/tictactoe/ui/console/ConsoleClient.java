package uk.ashleybye.tictactoe.ui.console;

import uk.ashleybye.tictactoe.ui.console.menu.ConfigurePlayerMenuItem.DuplicateMark;

public class ConsoleClient {

  private final IOWrapper ioWrapper;
  private MenuItem menuItem;

  public ConsoleClient(MenuItem mainMenu, IOWrapper ioWrapper) {
    this.ioWrapper = ioWrapper;
    menuItem = mainMenu;
  }

  public void start() {
    while (!menuItem.hasQuit())
      run();
  }

  private void run() {
    try {
      handleMenuItem();
    } catch (InvalidMenuOption | DuplicateMark ex) {
      handleBadInput();
    }
  }

  private void handleMenuItem() {
    ioWrapper.render(menuItem.launch());
    if (!menuItem.willQuit()) {
      String input = getInput();
      menuItem = menuItem.handleInput(input);
      ioWrapper.clearScreen();
    }
  }

  private void handleBadInput() {
    ioWrapper.clearScreen();
    ioWrapper.render(menuItem.handleBadInput());
  }

  private String getInput() {
    return ioWrapper.getInput();
  }

  public static class InvalidMenuOption extends RuntimeException {

  }
}
