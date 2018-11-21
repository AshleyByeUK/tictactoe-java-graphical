package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.UserInterface;

public class RendersGame {
  private final GeneratesGameOverview generatesGameOverview;
  private UserInterface userInterface;

  public RendersGame(GeneratesGameOverview generatesGameOverview, UserInterface userInterface) {
    this.generatesGameOverview = generatesGameOverview;
    this.userInterface = userInterface;
  }

  public void render(Game game) {
    GameOverview gameOverview = generatesGameOverview.parse(game);
    userInterface.update(gameOverview);
  }
}
