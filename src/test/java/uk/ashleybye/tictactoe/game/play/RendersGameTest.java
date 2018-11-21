package uk.ashleybye.tictactoe.game.play;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.MockGame;
import uk.ashleybye.tictactoe.game.MockUserInterface;

public class RendersGameTest {
  @Test
  void testUserInterfaceIsInstructedToUpdate() {
    MockGeneratesGameOverview generatesGameOverview = new MockGeneratesGameOverview();
    GameOverview gameOverview = new MockGameOverview();
    generatesGameOverview.returnGameOverview(gameOverview);
    MockUserInterface userInterface = new MockUserInterface();
    RendersGame rendersGame = new RendersGame(generatesGameOverview, userInterface);
    MockGame game = new MockGame();

    rendersGame.render(game);

    assertTrue(generatesGameOverview.wasCalledWith(game));
    assertTrue(userInterface.updateWasCalledWith(gameOverview));
  }
}
