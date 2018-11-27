package uk.ashleybye.tictactoe.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.TestHelpers;
import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayer;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

class GameReportTest {

  private static final boolean NOT_TIED = false;
  private static final boolean TIED = true;
  private static final boolean NOT_WON = false;
  private static final boolean WON = true;

  private Mark empty = new MockEmptyMark();
  private Mark one = new MockPlayerOneMark();
  private Mark two = new MockPlayerTwoMark();
  private MockPlayer playerOne = new MockPlayer(one, "Player 1");
  private MockPlayer playerTwo = new MockPlayer(two, "Player 2");
  private Map<Integer, Mark> boardRepresentation;

  @BeforeEach
  void setUp() {
    boardRepresentation = new HashMap<>();
    boardRepresentation.put(1, empty);
    boardRepresentation.put(2, empty);
    boardRepresentation.put(3, empty);
    boardRepresentation.put(4, empty);
    boardRepresentation.put(5, empty);
    boardRepresentation.put(6, empty);
    boardRepresentation.put(7, empty);
    boardRepresentation.put(8, empty);
    boardRepresentation.put(9, empty);
  }


  @Test
  void testGeneratesCorrectReportForReadyGame() {
    GameReport report = new GameReport();
    report.setCurrentBoard(TestHelpers.generateBoard("- - - - - - - - -"));
    report.setCurrentState(GameState.READY);
    report.setCurrentPlayer(playerOne);
    report.setLastPlayer(playerTwo);
    report.setResult(NOT_TIED, NOT_WON);

    assertEquals(boardRepresentation, report.getCurrentBoard());
    assertEquals(playerOne.getName(), report.getCurrentPlayer());
    assertEquals("", report.getLastPlayer());
    assertEquals("ready", report.getCurrentState());
    assertEquals("", report.getResult());
    assertEquals("", report.getWinner());
  }

  @Test
  void testGeneratesCorrectReportForOneMoveMadeGame() {
    boardRepresentation.put(1, one);
    boardRepresentation.put(2, two);
    boardRepresentation.put(3, one);
    boardRepresentation.put(4, two);
    boardRepresentation.put(5, one);

    GameReport report = new GameReport();
    report.setCurrentBoard(TestHelpers.generateBoard("X O X O X - - - -"));
    report.setCurrentState(GameState.PLAYING);
    report.setCurrentPlayer(playerTwo);
    report.setLastPlayer(playerOne);
    report.setResult(NOT_TIED, NOT_WON);

    assertEquals(boardRepresentation, report.getCurrentBoard());
    assertEquals(playerTwo.getName(), report.getCurrentPlayer());
    assertEquals(playerOne.getName(), report.getLastPlayer());
    assertEquals("playing", report.getCurrentState());
    assertEquals("", report.getResult());
    assertEquals("", report.getWinner());
  }

  @Test
  void testGeneratesCorrectReportForTiedGame() {
    boardRepresentation.put(1, one);
    boardRepresentation.put(2, one);
    boardRepresentation.put(3, two);
    boardRepresentation.put(4, two);
    boardRepresentation.put(5, one);
    boardRepresentation.put(6, one);
    boardRepresentation.put(7, one);
    boardRepresentation.put(8, two);
    boardRepresentation.put(9, two);

    GameReport report = new GameReport();
    report.setCurrentBoard(TestHelpers.generateBoard("X X O O X X X O O"));
    report.setCurrentState(GameState.GAME_OVER);
    report.setCurrentPlayer(playerTwo);
    report.setLastPlayer(playerOne);
    report.setResult(TIED, NOT_WON);

    assertEquals(boardRepresentation, report.getCurrentBoard());
    assertEquals(playerTwo.getName(), report.getCurrentPlayer());
    assertEquals(playerOne.getName(), report.getLastPlayer());
    assertEquals("game_over", report.getCurrentState());
    assertEquals("tied", report.getResult());
    assertEquals("", report.getWinner());
  }

  @Test
  void testGeneratesCorrectReportForWonGame() {
    boardRepresentation.put(1, one);
    boardRepresentation.put(2, one);
    boardRepresentation.put(3, one);
    boardRepresentation.put(4, two);
    boardRepresentation.put(5, two);

    GameReport report = new GameReport();
    report.setCurrentBoard(TestHelpers.generateBoard("X X X O O - - - -"));
    report.setCurrentState(GameState.GAME_OVER);
    report.setCurrentPlayer(playerTwo);
    report.setLastPlayer(playerOne);
    report.setResult(NOT_TIED, WON);

    assertEquals(boardRepresentation, report.getCurrentBoard());
    assertEquals(playerTwo.getName(), report.getCurrentPlayer());
    assertEquals(playerOne.getName(), report.getLastPlayer());
    assertEquals("game_over", report.getCurrentState());
    assertEquals("won", report.getResult());
    assertEquals(playerOne.getName(), report.getWinner());
  }
}
