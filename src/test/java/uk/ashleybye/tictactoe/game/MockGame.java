package uk.ashleybye.tictactoe.game;

import java.util.Arrays;
import java.util.List;

public class MockGame extends Game {

  public MockGame() {
    super(null, null, new MockEmptyMark());
  }

  @Override
  public List<Integer> listOpenPositions() {
    return Arrays.asList(4, 5, 6);
  }
}
