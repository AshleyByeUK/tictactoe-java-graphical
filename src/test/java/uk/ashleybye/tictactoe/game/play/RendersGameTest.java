package uk.ashleybye.tictactoe.game.play;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.MockGame;
import uk.ashleybye.tictactoe.game.MockUserInterface;

public class RendersGameTest {
  @Test
  void testUserInterfaceIsInstructedToUpdate() {
    MockUserInterface userInterface = new MockUserInterface();
    RendersGame rendersGame = new RendersGame(userInterface);
    GameOverview gameOverview = new MockGameOverview();
    MockGame game = new MockGame();
    game.returnGameOverview(gameOverview);

    rendersGame.render(game);

    assertTrue(userInterface.updateWasCalledWith(gameOverview));
  }
}
