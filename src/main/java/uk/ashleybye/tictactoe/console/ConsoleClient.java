package uk.ashleybye.tictactoe.console;

import uk.ashleybye.tictactoe.console.view.SetPlayerMarkView.DuplicateMark;
import uk.ashleybye.tictactoe.console.view.View;

public class ConsoleClient {

  private final IOWrapper ioWrapper;
  private View view;

  public ConsoleClient(View mainMenu, IOWrapper ioWrapper) {
    this.ioWrapper = ioWrapper;
    view = mainMenu;
  }

  public void start() {
    while (!view.hasQuit()) {
      run();
    }
  }

  private void run() {
    try {
      handleMenuItem();
    } catch (InvalidMenuOption | DuplicateMark ex) {
      handleBadInput();
    }
  }

  private void handleMenuItem() {
    view.render();
    if (!view.willQuit()) {
      String input = getInput();
      view = view.handleInput(input);
      ioWrapper.clearScreen();
    }
  }

  private void handleBadInput() {
    ioWrapper.clearScreen();
    ioWrapper.render(view.handleBadInput());
  }

  private String getInput() {
    return ioWrapper.getInput();
  }

  public static class InvalidMenuOption extends RuntimeException {

  }
}
