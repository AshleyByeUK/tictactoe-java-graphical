package uk.ashleybye.tictactoe.core.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.MockTicTacToe;
import uk.ashleybye.tictactoe.core.TicTacToe;
import uk.ashleybye.tictactoe.console.gameClient.MockGameConsole;

public class HumanPlayerTest {

  @Test
  void testCanGetAMoveWhenThereIsNoDelay() {
    HumanTurnPublisher turnPublisher = new MockHumanTurnPublisher(1, 0L);
    Player player = new HumanPlayer(new MockPlayerOneMark(), "Player", turnPublisher);

    TicTacToe ticTacToe = new MockTicTacToe();

    assertEquals(1, player.choosePositionToPlay(ticTacToe));
  }

  @Disabled
  @Test
  @Tag("slow")
  void testCanGetAMoveWhenThereIsADelay() {
    HumanTurnPublisher turnPublisher = new MockHumanTurnPublisher(1, 10000L);
    Player player = new HumanPlayer(new MockPlayerOneMark(), "Player", turnPublisher);

    TicTacToe ticTacToe = new MockTicTacToe();

    assertEquals(1, player.choosePositionToPlay(ticTacToe));
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
