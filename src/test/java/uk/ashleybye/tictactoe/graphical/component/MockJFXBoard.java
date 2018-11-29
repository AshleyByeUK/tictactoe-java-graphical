package uk.ashleybye.tictactoe.graphical.component;

import java.util.HashMap;
import java.util.Map;
import uk.ashleybye.tictactoe.graphical.component.JFXBoard;

public class MockJFXBoard extends JFXBoard {

  private Map<Integer, Integer> squaresClicked = new HashMap<>();

  public MockJFXBoard() {
    super(null);
    for (int i = 0; i < 9; i++) {
      squaresClicked.put(i + 1, 0);
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
}
