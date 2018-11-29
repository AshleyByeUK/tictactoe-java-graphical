package uk.ashleybye.tictactoe.graphical.component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uk.ashleybye.tictactoe.graphical.component.JFXGame;
import uk.ashleybye.tictactoe.graphical.component.JFXMark;

public class MockJFXGame extends JFXGame {

  private Map<Integer, Integer> squaresClicked = new HashMap<>();
  private Map<Integer, JFXMark> boardRepresentation = new HashMap<>();

  public MockJFXGame() {
    for (int i = 0; i < 9; i++) {
      squaresClicked.put(i + 1, 0);
      boardRepresentation.put(i + 1, new JFXMark(" "));
    }
  }

  @Override
  public void receiveSquareClicked(int square) {
    int currentClicks = squaresClicked.get(square);
    squaresClicked.put(square, ++currentClicks);
  }

  public int receivedClicksForSquare(int square) {
    return squaresClicked.get(square);
  }

  public void whenSquareClickedReturn(String boardRepresentation) {
    List<String> marks = Arrays.asList(boardRepresentation.split(" "));
    for (int i = 0; i < marks.size(); i++) {
      JFXMark mark = marks.get(i).equals("-") ? new JFXMark(" ") : new JFXMark(marks.get(i));
      this.boardRepresentation.put(i + 1, mark);
    }
  }
}
