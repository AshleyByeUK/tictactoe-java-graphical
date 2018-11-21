package uk.ashleybye.tictactoe.game;

public class MockCreatesGame extends CreatesGame {
  private final MockGame game;
  private GameOptions gameOptions;

  public MockCreatesGame(MockGame game) {
    super(null);
    this.game = game;
  }

  @Override
  public Game create(GameOptions gameOptions) {
    this.gameOptions = gameOptions;
    return game;
  }

  public boolean wasCalledWith(GameOptions gameOptions) {
    return this.gameOptions.equals(gameOptions);
  }
}
