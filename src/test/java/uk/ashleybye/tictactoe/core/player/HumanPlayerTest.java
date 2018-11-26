package uk.ashleybye.tictactoe.core.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.HumanTurnPublisher;
import uk.ashleybye.tictactoe.core.MockGame;
import uk.ashleybye.tictactoe.core.MockHumanTurnPublisher;
import uk.ashleybye.tictactoe.core.Player;
import uk.ashleybye.tictactoe.ui.console.MockGameConsole;

public class HumanPlayerTest {

  @Test
  void testCanGetAMoveWhenThereIsNoDelay() {
    HumanTurnPublisher turnPublisher = new MockHumanTurnPublisher(1, 0L);
    Player player = new HumanPlayer(new MockPlayerOneMark(), "Player", turnPublisher);

    Game game = new MockGame();

    assertEquals(1, player.choosePositionToPlay(game));
  }

  @Test
  @Tag("slow")
  void testCanGetAMoveWhenThereIsADelay() {
    HumanTurnPublisher turnPublisher = new MockHumanTurnPublisher(1, 10000L);
    Player player = new HumanPlayer(new MockPlayerOneMark(), "Player", turnPublisher);

    Game game = new MockGame();

    assertEquals(1, player.choosePositionToPlay(game));
  }

  @Test
  void testEquality() {
    Player player = new HumanPlayer(new MockPlayerOneMark(), "Player 1", new MockGameConsole());
    Player otherPlayer = new HumanPlayer(new MockPlayerTwoMark(), "Player 2", new MockGameConsole());

    assertEquals(player, player);
    assertEquals(player, new HumanPlayer(new MockPlayerOneMark(), "Player 1", new MockGameConsole()));
    assertEquals(player.hashCode(),
        (new HumanPlayer(new MockPlayerOneMark(), "Player 1", new MockGameConsole())).hashCode());
    assertNotEquals(player, otherPlayer);
    assertNotEquals(player, "not a player");
    assertNotEquals(player, null);
    assertNotEquals(player.hashCode(), otherPlayer.hashCode());
  }
}
