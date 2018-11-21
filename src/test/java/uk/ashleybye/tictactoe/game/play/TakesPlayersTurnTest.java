package uk.ashleybye.tictactoe.game.play;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.MockGame;

public class TakesPlayersTurnTest {
  @Test
  void testCanGetAHumanPlayersMove() {
    Move move = new MockMove(1);
    MockGame game = new MockGame();
    game.returnMovesInOrder(Arrays.asList(move));

    TakesPlayersTurn takesPlayersTurn = new TakesPlayersTurn();
    Move playersMove = takesPlayersTurn.takeTurn(game);

    assertEquals(1, game.numberOfTimesGetNextMoveHasBeenCalled());
    assertEquals(move, playersMove);
  }
}
