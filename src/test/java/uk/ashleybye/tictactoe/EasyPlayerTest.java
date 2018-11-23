package uk.ashleybye.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EasyPlayerTest {

  @Test
  void testChoosesTheFirstAvailablePosition() {
    Game game = new MockGame();
    Player player = new EasyPlayer(new MockPlayerOneMark());

    assertEquals(4, player.choosePositionToPlay(game));
  }
}
