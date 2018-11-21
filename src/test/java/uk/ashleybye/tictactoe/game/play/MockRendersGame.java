package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.UserInterface;

public class MockRendersGame extends RendersGame {
  private int numberOfTimesRenderHasBeenCalled = 0;
  private Game game;
  private UserInterface userInterface;

  @Override
  public void render(Game game, UserInterface userInterface) {
    numberOfTimesRenderHasBeenCalled++;
    this.game = game;
    this.userInterface = userInterface;
  }

  public int getNumberOfTimesRenderHasBeenCalled() {
    return numberOfTimesRenderHasBeenCalled;
  }

  public boolean wasLastCalledWith(Game game, UserInterface userInterface) {
    return this.game.equals(game) && this.userInterface.equals(userInterface);
  }
}
