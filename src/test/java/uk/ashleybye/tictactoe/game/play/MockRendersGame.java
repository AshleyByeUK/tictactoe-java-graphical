package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.UserInterface;

public class MockRendersGame extends RendersGame {
  private int numberOfTimesRenderHasBeenCalled = 0;
  private Game game;
  private UserInterface userInterface;

  @Override
  public void registerUserInterface(UserInterface userInterface) {
    this.userInterface = userInterface;
  }

  @Override
  public void render(Game game) {
    numberOfTimesRenderHasBeenCalled++;
    this.game = game;
  }

  public int getNumberOfTimesRenderHasBeenCalled() {
    return numberOfTimesRenderHasBeenCalled;
  }

  public boolean wasLastCalledWith(Game game) {
    return this.game.equals(game);
  }

  public boolean registeredUserInterface() {
    return this.userInterface != null;
  }
}
