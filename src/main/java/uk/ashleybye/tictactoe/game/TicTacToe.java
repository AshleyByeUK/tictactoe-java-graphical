package uk.ashleybye.tictactoe.game;

import uk.ashleybye.tictactoe.game.play.PlaysGame;

public class TicTacToe {
  private final CreatesGame createsGame;
  private final PlaysGame playsGame;
  private PlayerFactory playerFactory;

  public TicTacToe(CreatesGame createsGame, PlaysGame playsGame) {
    this.createsGame = createsGame;
    this.playsGame = playsGame;
  }

  public void registerUserInterface(UserInterface userInterface) {
    playsGame.registerUserInterface(userInterface);
  }

  public void registerPlayerFactory(PlayerFactory playerFactory) {
    this.playerFactory = playerFactory;
  }

  public void play(GameOptions gameOptions) {
    Game game = createsGame.create(gameOptions, playerFactory);
    playsGame.play(game);
  }
}
