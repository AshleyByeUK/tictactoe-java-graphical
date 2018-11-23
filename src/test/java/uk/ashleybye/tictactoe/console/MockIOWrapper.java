package uk.ashleybye.tictactoe.console;

import java.util.ArrayList;
import java.util.List;

public class MockIOWrapper extends IOWrapper {

  private List<String> input = new ArrayList<>();
  private int numberOfTimesRenderWasCalled = 0;
  private int numberOfTimesGetInputWasCalled = 0;

  @Override
  public void render(String text) {
    numberOfTimesRenderWasCalled++;
  }

  @Override
  public String getInput() {
    return input.get(numberOfTimesGetInputWasCalled++);
  }

  public void addMockInput(String input) {
    this.input.add(input);
  }

  public int getNumberOfTimesGetInputWasCalled() {
    return numberOfTimesGetInputWasCalled;
  }

  public int getNumberOfTimesRenderWasCalled() {
    return numberOfTimesRenderWasCalled;
  }
}
