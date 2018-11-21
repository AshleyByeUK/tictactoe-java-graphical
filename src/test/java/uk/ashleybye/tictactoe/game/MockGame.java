package uk.ashleybye.tictactoe.game;

import java.util.List;
import uk.ashleybye.tictactoe.game.play.GameOverview;
import uk.ashleybye.tictactoe.game.play.Move;

public class MockGame extends Game {
  private int numberOfTimesGetStateHasBeenCalled = 0;
  private int numberOfTimesGetNextMoveHasBeenCalled = 0;
  private List<Move> moves;
  private List<GameState> gameStates;
  private GameOverview gameOverview;

  @Override
  public Move getNextMove() {
    return moves.get(numberOfTimesGetNextMoveHasBeenCalled++);
  }

  @Override
  public GameState getState() {
    return gameStates.get(++numberOfTimesGetStateHasBeenCalled);
  }

  @Override
  public GameOverview getOverview() {
    return gameOverview;
  }

  public void returnMovesInOrder(List<Move> moves) {
    this.moves = moves;
  }

  public void returnGameStatesInOrder(List<GameState> gameStates) {
    this.gameStates = gameStates;
  }

  public void returnGameOverview(GameOverview gameOverview) {
    this.gameOverview = gameOverview;
  }

  public int numberOfTimesGetNextMoveHasBeenCalled() {
    return numberOfTimesGetNextMoveHasBeenCalled;
  }
}
