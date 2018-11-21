package uk.ashleybye.tictactoe.game;

import uk.ashleybye.tictactoe.game.play.PlaysGame;

public class TicTacToe {
  private final CreatesGame createsGame;
  private final PlaysGame playsGame;

  public TicTacToe(CreatesGame createsGame, PlaysGame playsGame) {
    this.createsGame = createsGame;
    this.playsGame = playsGame;
  }

  public void play(GameOptions gameOptions) {
    Game game = createsGame.create(gameOptions);
    playsGame.play(game);
  }
}
