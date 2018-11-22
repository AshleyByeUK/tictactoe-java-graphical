package uk.ashleybye.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  void testNewGameIsCorrectlySetup() {
    Player playerOne = new MockPlayer(new MockPlayerOneMark());
    Player playerTwo = new MockPlayer(new MockPlayerTwoMark());
    Mark mark = new MockEmptyMark();
    Game game = new Game(playerOne, playerTwo, mark);

    Board emptyBoard = TestHelpers.generateBoard("- - - - - - - - -");
    assertEquals(emptyBoard.listUnmarkedSquares(), game.listUnmarkedSquares());
    
  }
}
