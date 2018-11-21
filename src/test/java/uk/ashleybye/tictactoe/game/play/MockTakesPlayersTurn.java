package uk.ashleybye.tictactoe.game.play;

import java.util.List;
import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.UserInterface;

public class MockTakesPlayersTurn extends TakesPlayersTurn {
  private int numberOfTimesTakeTurnHasBeenCalled = 0;
  private List<Move> moves;
  private Game game;
  private UserInterface userInterface;

  @Override
  public Move takeTurn(Game game, UserInterface userInterface) {
    this.game = game;
    this.userInterface = userInterface;
    return moves.get(numberOfTimesTakeTurnHasBeenCalled++);
  }

  public void makeMovesInOrder(List<Move> moves) {
    this.moves = moves;
  }

  public int getNumberOfTimesTakeTurnHasBeenCalled() {
    return numberOfTimesTakeTurnHasBeenCalled;
  }

  public boolean wasLastCalledWith(Game game, UserInterface userInterface) {
    return this.game.equals(game) && this.userInterface.equals(userInterface);
  }
}
