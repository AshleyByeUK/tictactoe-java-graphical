package uk.ashleybye.tictactoe.game;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.play.MockPlaysGame;

class TicTacToeTest {
  @Test
  void testTicTacToeCreatesAndPlaysGame() {
    MockGame game = new MockGame();
    MockCreatesGame createsGame = new MockCreatesGame(game);
    MockPlaysGame playsGame = new MockPlaysGame();
    UserInterface userInterface = new MockUserInterface();
    PlayerFactory playerFactory = new MockPlayerFactory();

    GameOptions gameOptions = new GameOptions();
    TicTacToe tictactoe = new TicTacToe(createsGame, playsGame);
    tictactoe.registerUserInterface(userInterface);
    tictactoe.registerPlayerFactory(playerFactory);
    tictactoe.play(gameOptions);

    assertTrue(createsGame.wasCalledWith(gameOptions, playerFactory));
    assertTrue(playsGame.wasCalledWith(game, userInterface));
  }
}
