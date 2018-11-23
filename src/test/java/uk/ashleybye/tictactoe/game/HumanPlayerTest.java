package uk.ashleybye.tictactoe.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class HumanPlayerTest {

  @Test
  void testCanGetAMoveWhenThereIsNoDelay() {
    HumanMovePublisher observable = new MockHumanMovePublisher(1, 0L);
    Player player = new HumanPlayer(new MockPlayerOneMark(), observable);

    Game game = new MockGame();

    assertEquals(1, player.choosePositionToPlay(game));
  }

  @Disabled
  @Test
  @Tag("slow")
  void testCanGetAMoveWhenThereIsADelay() {
    HumanMovePublisher observable = new MockHumanMovePublisher(1, 10000L);
    Player player = new HumanPlayer(new MockPlayerOneMark(), observable);

    Game game = new MockGame();

    assertEquals(1, player.choosePositionToPlay(game));
  }
}
