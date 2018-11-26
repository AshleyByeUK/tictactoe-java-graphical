package uk.ashleybye.tictactoe.core.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.HumanTurnPublisher;
import uk.ashleybye.tictactoe.core.MockGame;
import uk.ashleybye.tictactoe.core.MockHumanTurnPublisher;
import uk.ashleybye.tictactoe.core.Player;

public class HumanPlayerTest {

  @Test
  void testCanGetAMoveWhenThereIsNoDelay() {
    HumanTurnPublisher turnPublisher = new MockHumanTurnPublisher(1, 0L);
    Player player = new HumanPlayer(new MockPlayerOneMark(), "Player", turnPublisher);

    Game game = new MockGame();

    assertEquals(1, player.choosePositionToPlay(game));
  }

  @Disabled
  @Test
  @Tag("slow")
  void testCanGetAMoveWhenThereIsADelay() {
    HumanTurnPublisher turnPublisher = new MockHumanTurnPublisher(1, 10000L);
    Player player = new HumanPlayer(new MockPlayerOneMark(), "Player", turnPublisher);

    Game game = new MockGame();

    assertEquals(1, player.choosePositionToPlay(game));
  }
}
