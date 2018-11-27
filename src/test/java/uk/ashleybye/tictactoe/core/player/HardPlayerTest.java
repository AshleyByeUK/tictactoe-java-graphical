package uk.ashleybye.tictactoe.core.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.TestHelpers;
import uk.ashleybye.tictactoe.core.TicTacToe;
import uk.ashleybye.tictactoe.core.board.Board;

public class HardPlayerTest {

  private Player hardPlayer;
  private Player opponent;

  @BeforeEach
  void setUp() {
    hardPlayer = new HardPlayer(new MockPlayerOneMark(), "Player 1");
    opponent = new MockPlayer(new MockPlayerTwoMark(), "Player 2");
  }

  @Test
  void testChoosesTieSquareWhenOnlyOneSquareRemains() {
    Board board = TestHelpers.generateBoard("X X O O O - X O X");
    TicTacToe ticTacToe = new TicTacToe(hardPlayer, opponent, board);

    int position = hardPlayer.choosePositionToPlay(ticTacToe);

    assertEquals(6, position);
  }

  @Test
  void testChoosesWinningSquareWhenOnlyOneSquareRemains() {
    Board board = TestHelpers.generateBoard("X X O X X O O O -");
    TicTacToe ticTacToe = new TicTacToe(hardPlayer, opponent, board);

    int position = hardPlayer.choosePositionToPlay(ticTacToe);

    assertEquals(9, position);
  }

  @Test
  void testChoosesWinningSquareWhenTwoSquaresRemain() {
    Board board = TestHelpers.generateBoard("X O X X O O - - O");
    TicTacToe ticTacToe = new TicTacToe(hardPlayer, opponent, board);

    int position = hardPlayer.choosePositionToPlay(ticTacToe);

    assertEquals(7, position);
  }

  @Test
  void testBlocksOpponentFromWinning() {
    Board board = TestHelpers.generateBoard("X - - - O O - - -");
    TicTacToe ticTacToe = new TicTacToe(hardPlayer, opponent, board);

    int position = hardPlayer.choosePositionToPlay(ticTacToe);

    assertEquals(4, position);
  }

  @Test
  void testAlsoBlocksOpponentFromWinning() {
    Board board = TestHelpers.generateBoard("- X O X O - - - -");
    TicTacToe ticTacToe = new TicTacToe(hardPlayer, opponent, board);

    int position = hardPlayer.choosePositionToPlay(ticTacToe);

    assertEquals(7, position);
  }

  @Test
  void testPlaysForWinningMove() {
    Board board = TestHelpers.generateBoard("X - X O O - - X O");
    TicTacToe ticTacToe = new TicTacToe(hardPlayer, opponent, board);

    int position = hardPlayer.choosePositionToPlay(ticTacToe);

    assertEquals(2, position);
  }

  @Test
  void testPlaysForkingMove() {
    Board board = TestHelpers.generateBoard("- X - - - O - O X");
    TicTacToe ticTacToe = new TicTacToe(hardPlayer, opponent, board);

    int position = hardPlayer.choosePositionToPlay(ticTacToe);

    assertEquals(1, position);
  }

  @Test
  void testEquality() {
    Player player = new HardPlayer(new MockPlayerOneMark(), "Player 1");
    Player otherPlayer = new HardPlayer(new MockPlayerTwoMark(), "Player 2");

    assertEquals(player, player);
    assertEquals(player, new HardPlayer(new MockPlayerOneMark(), "Player 1"));
    assertEquals(player.hashCode(), (new HardPlayer(new MockPlayerOneMark(), "Player 1")).hashCode());
    assertNotEquals(player, otherPlayer);
    assertNotEquals(player, "not a player");
    assertNotEquals(player, null);
    assertNotEquals(player.hashCode(), otherPlayer.hashCode());
  }
}
