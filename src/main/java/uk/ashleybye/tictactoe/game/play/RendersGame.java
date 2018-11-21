package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.UserInterface;

public class RendersGame {
  private UserInterface userInterface;

  public RendersGame(UserInterface userInterface) {
    this.userInterface = userInterface;
  }

  public void render(Game game) {
    GameOverview gameOverview = game.getOverview();
    userInterface.update(gameOverview);
  }
}
