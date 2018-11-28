package uk.ashleybye.tictactoe;

import uk.ashleybye.tictactoe.core.board.Mark;
import uk.ashleybye.tictactoe.core.board.Board;
import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

public class TestHelpers {

  public static Board generateBoard(String boardRepresentation) {
    Board board = new Board(new MockEmptyMark());
    String[] marks = boardRepresentation.split(" ");
    for (int i = 0; i < marks.length; i++)
      board = board.markSquare(i + 1, getMarkFor(marks[i]));
    return board;
  }

  private static Mark getMarkFor(String markFor) {
    Mark mark;
    switch (markFor) {
      case "X":
        mark = new MockPlayerOneMark();
        break;
      case "O":
        mark = new MockPlayerTwoMark();
        break;
      case "-":
        mark = new MockEmptyMark();
        break;
      default:
        throw new RuntimeException("invalid character in board representation, may only by X, O or -");
    }
    return mark;
  }

  public static String colourisedMark(String mark, String markColour, String terminalColour) {
    return (char) 27 + markColour + mark + (char) 27 + terminalColour;
  }
}
