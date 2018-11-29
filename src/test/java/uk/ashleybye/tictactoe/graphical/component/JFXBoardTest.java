package uk.ashleybye.tictactoe.graphical.component;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JFXBoardTest extends JFXTest {

  private MockJFXGame tictactoe;

  @BeforeEach
  void setUp() {
    tictactoe = new MockJFXGame();
  }

  @Test
  void testAllSquaresAreEmptyWhenGameStarts() {
    JFXBoard board = new JFXBoard(tictactoe);

    assertEquals(" ", board.getSquare(1).getText());
    assertEquals(" ", board.getSquare(2).getText());
    assertEquals(" ", board.getSquare(3).getText());
    assertEquals(" ", board.getSquare(4).getText());
    assertEquals(" ", board.getSquare(5).getText());
    assertEquals(" ", board.getSquare(6).getText());
    assertEquals(" ", board.getSquare(7).getText());
    assertEquals(" ", board.getSquare(8).getText());
    assertEquals(" ", board.getSquare(9).getText());
  }

  @Test
  void testBoardNotifiesGameWhenClickReceived() {
    JFXBoard board = new JFXBoard(tictactoe);

    board.receiveSquareClicked(1);

    assertEquals(1, tictactoe.receivedClicksForSquare(1));
  }

  @Test
  void testNotifiesTicTacToeWhenClickReceived() {
    JFXBoard board = new JFXBoard(tictactoe);

    board.receiveSquareClicked(1);
    board.receiveSquareClicked(2);
    board.receiveSquareClicked(3);
    board.receiveSquareClicked(4);
    board.receiveSquareClicked(5);
    board.receiveSquareClicked(6);
    board.receiveSquareClicked(7);
    board.receiveSquareClicked(8);
    board.receiveSquareClicked(9);

    assertEquals(1, tictactoe.receivedClicksForSquare(1));
    assertEquals(1, tictactoe.receivedClicksForSquare(2));
    assertEquals(1, tictactoe.receivedClicksForSquare(3));
    assertEquals(1, tictactoe.receivedClicksForSquare(4));
    assertEquals(1, tictactoe.receivedClicksForSquare(5));
    assertEquals(1, tictactoe.receivedClicksForSquare(6));
    assertEquals(1, tictactoe.receivedClicksForSquare(7));
    assertEquals(1, tictactoe.receivedClicksForSquare(8));
    assertEquals(1, tictactoe.receivedClicksForSquare(9));
  }
}
