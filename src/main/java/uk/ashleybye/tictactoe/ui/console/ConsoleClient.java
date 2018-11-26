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
    render(menuItem.launch());
    if (!menuItem.willQuit()) {
      String input = getInput();
      menuItem = menuItem.handleInput(input);
    }
  }

  private void handleBadInput() {
    render(menuItem.handleBadInput());
  }

  private void render(String text) {
    ioWrapper.render(text);
  }

  private String getInput() {
    return ioWrapper.getInput();
  }

  public static class InvalidMenuOption extends RuntimeException {

  }
}
