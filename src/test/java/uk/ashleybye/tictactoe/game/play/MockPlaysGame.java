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
  public void play(Game game, UserInterface userInterface) {
    this.game = game;
    this.userInterface = userInterface;
  }

  public boolean wasCalledWith(Game game, UserInterface userInterface) {
    return this.game.equals(game) && this.userInterface.equals(userInterface);
  }
}
