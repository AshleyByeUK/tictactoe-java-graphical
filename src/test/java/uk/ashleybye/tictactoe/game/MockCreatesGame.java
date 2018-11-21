package uk.ashleybye.tictactoe.game;

public class MockCreatesGame extends CreatesGame {
  private final MockGame game;
  private GameOptions gameOptions;
  private PlayerFactory playerFactory;

  public MockCreatesGame(MockGame game) {
    this.game = game;
  }

  @Override
  public Game create(GameOptions gameOptions, PlayerFactory playerFactory) {
    this.gameOptions = gameOptions;
    this.playerFactory = playerFactory;
    return game;
  }

  public boolean wasCalledWith(GameOptions gameOptions, PlayerFactory playerFactory) {
    return this.gameOptions.equals(gameOptions) && this.playerFactory.equals(playerFactory);
  }
}
