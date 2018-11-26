package uk.ashleybye.tictactoe.core.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.MockGame;
import uk.ashleybye.tictactoe.core.Player;

public class EasyPlayerTest {

  @Test
  void testChoosesTheFirstAvailablePosition() {
    Game game = new MockGame();
    Player player = new EasyPlayer(new MockPlayerOneMark(), "Player");

    assertEquals(4, player.choosePositionToPlay(game));
  }
}
