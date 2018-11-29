package uk.ashleybye.tictactoe.graphical.component;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.GameReport;
import uk.ashleybye.tictactoe.graphical.MockGameReport;

class JFXGameTest extends JFXTest {

  @Test
  void testNotifiesGameWhenHumanMovePlayed() {
    JFXGame game = new JFXGame();
    game.receiveSquareClicked(1);

    assertEquals(1, game.getPlayersMove());
  }

  @Test
  void testGameReportsUpdateGame() {
    GameReport gameReport = MockGameReport.tiedGame();
    JFXGame game = new JFXGame();

    game.renderGame(gameReport);

    JFXBoard board = game.getBoard();
    assertEquals("X", board.getSquare(1).getMark().getText());
    assertEquals("X", board.getSquare(2).getMark().getText());
    assertEquals("O", board.getSquare(3).getMark().getText());
    assertEquals("O", board.getSquare(4).getMark().getText());
    assertEquals("X", board.getSquare(5).getMark().getText());
    assertEquals("X", board.getSquare(6).getMark().getText());
    assertEquals("X", board.getSquare(7).getMark().getText());
    assertEquals("O", board.getSquare(8).getMark().getText());
    assertEquals("O", board.getSquare(9).getMark().getText());
  }
}
