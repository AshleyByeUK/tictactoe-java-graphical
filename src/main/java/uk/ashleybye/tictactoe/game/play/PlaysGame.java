package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.UserInterface;

public class PlaysGame {
  private final TakesPlayersTurn takesPlayersTurn;
  private final UpdatesGame updatesGame;
  private final RendersGame rendersGame;

  public PlaysGame(TakesPlayersTurn takesPlayersTurn, UpdatesGame updatesGame, RendersGame rendersGame) {
    this.takesPlayersTurn = takesPlayersTurn;
    this.updatesGame = updatesGame;
    this.rendersGame = rendersGame;
  }

  public void play(Game game, UserInterface userInterface) {
    while (game.getState() == GameState.IN_PROGRESS) {
      Move move = takesPlayersTurn.takeTurn(game, userInterface);
      game = updatesGame.apply(move, game);
      rendersGame.render(game, userInterface);
    }
  }
}
