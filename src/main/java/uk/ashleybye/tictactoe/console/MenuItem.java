package uk.ashleybye.tictactoe.console;

public abstract class MenuItem {

  protected static final String GOOD_BYE = "Good bye, thank you for playing";
  protected static final String INVALID_OPTION = "Invalid option, please try again";
  protected static final String PLAYER_CONFIGURATION = "%s\n%s\n%s\n%s\n";
  protected static final String PLAYER_HEADING = "Player %d";
  protected static final String PLAYER_MARK = "Mark: %s";
  protected static final String PLAYER_NAME = "Name: %s";
  protected static final String PLAYER_TYPE = "Type: %s";
  protected static final String PLAYER_TYPE_EASY = "Easy Computer";
  protected static final String PLAYER_TYPE_HUMAN = "User";
  protected static final String PROMPT = "> ";

  protected MenuItem previousMenu;
  protected final ConsoleGameConfiguration configuration;

  public MenuItem(MenuItem previousMenu, ConsoleGameConfiguration gameConfiguration) {
    this.previousMenu = previousMenu;
    configuration = gameConfiguration;
  }

  protected MenuItem(ConsoleGameConfiguration gameConfiguration) {
    this(null, gameConfiguration);
    this.previousMenu = new QuitMenuItem();
  }

  abstract public String launch();

  public abstract MenuItem handleInput(String input);

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
      default:
        return "";
    }
  }

  private class QuitMenuItem extends MenuItem {

    private boolean hasQuit = false;

    private QuitMenuItem() {
      super(null, null);
    }

    @Override
    public String launch() {
      hasQuit = true;
      return GOOD_BYE;
    }

    @Override
    public MenuItem handleInput(String input) {
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
