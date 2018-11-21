package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.UserInterface;

public class MockPlaysGame extends PlaysGame {
  private Game game;
  private UserInterface userInterface;

  public MockPlaysGame() {
    super(null, null, null);
  }

  @Override
  public void registerUserInterface(UserInterface userInterface) {
    this.userInterface = userInterface;
  }

  @Override
  public void play(Game game) {
    this.game = game;
  }

  public boolean wasCalledWith(Game game) {
    return this.game.equals(game);
  }

  public boolean registeredUserInterface() {
    return this.userInterface != null;
  }
}
