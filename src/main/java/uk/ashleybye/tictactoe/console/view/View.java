package uk.ashleybye.tictactoe.console.view;

import uk.ashleybye.tictactoe.console.IOWrapper;
import uk.ashleybye.tictactoe.console.game.ConsoleGameConfiguration;

public abstract class View {

  protected static final String GOOD_BYE = "Good bye, thank you for playing";
  protected static final String INVALID_OPTION = "Invalid option, please try again";
  protected static final String PLAYER_CONFIGURATION = "%s\n%s\n%s\n%s\n";
  protected static final String PLAYER_HEADING = "Player %d";
  protected static final String PLAYER_MARK = "Mark: %s";
  protected static final String PLAYER_NAME = "Name: %s";
  protected static final String PLAYER_TYPE = "Type: %s";
  protected static final String PLAYER_TYPE_EASY = "Easy Computer";
  protected static final String PLAYER_TYPE_HARD = "Hard Computer";
  protected static final String PLAYER_TYPE_HUMAN = "User";
  protected static final String PROMPT = "> ";
  protected final ConsoleGameConfiguration configuration;
  protected final IOWrapper ioWrapper;
  protected View previousMenu;

  public View(View previousMenu, ConsoleGameConfiguration gameConfiguration, IOWrapper ioWrapper) {
    this.previousMenu = previousMenu;
    configuration = gameConfiguration;
    this.ioWrapper = ioWrapper;
  }

  protected View(ConsoleGameConfiguration gameConfiguration, IOWrapper ioWrapper) {
    this(null, gameConfiguration, ioWrapper);
    this.previousMenu = new QuitView(ioWrapper);
  }

  abstract public void render();

  public abstract View handleInput(String input);

  public String handleBadInput() {
    return String.format("%s\n\n%s", INVALID_OPTION, PROMPT);
  }

  public boolean hasQuit() {
    return false;
  }

  public boolean willQuit() {
    return false;
  }

  protected String textForPlayerType(String type) {
    switch (type) {
      case "human":
        return PLAYER_TYPE_HUMAN;
      case "easy":
        return PLAYER_TYPE_EASY;
      case "hard":
        return PLAYER_TYPE_HARD;
      default:
        return "";
    }
  }

  private class QuitView extends View {

    private boolean hasQuit = false;

    private QuitView(IOWrapper ioWrapper) {
      super(null, null, ioWrapper);
    }

    @Override
    public void render() {
      hasQuit = true;
      ioWrapper.render(String.format("%s\n\n", GOOD_BYE));
    }

    @Override
    public View handleInput(String input) {
      return this;
    }

    @Override
    public boolean hasQuit() {
      return hasQuit;
    }

    @Override
    public boolean willQuit() {
      return true;
    }
  }
}
