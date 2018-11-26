package uk.ashleybye.tictactoe.ui.console;

import java.util.ArrayList;
import java.util.List;

public class MockIOWrapper extends IOWrapper {

  private List<String> input = new ArrayList<>();
  private int numberOfTimesRenderWasCalled = 0;
  private int numberOfTimesGetInputWasCalled = 0;
  private boolean retainAllRenderedText = false;
  private List<String> renderedText = new ArrayList<>();

  @Override
  public void render(String text) {
    renderedText.add(text);
    numberOfTimesRenderWasCalled++;
  }

  @Override
  public String getInput() {
    return input.get(numberOfTimesGetInputWasCalled++);
  }

  public void retainAllRenderedText() {
    retainAllRenderedText = true;
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

  public String getRenderedText() {
    if (retainAllRenderedText)
      return allRenderedText();
    else
      return renderedText.get(numberOfTimesRenderWasCalled - 1);
  }

  private String allRenderedText() {
    String text = "";
    for (String str : renderedText)
      text += str;
    return text;
  }
}
