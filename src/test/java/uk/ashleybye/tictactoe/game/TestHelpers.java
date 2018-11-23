package uk.ashleybye.tictactoe.game;

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
}
