package uk.ashleybye.tictactoe.graphical.component;

import javafx.scene.control.Button;

public class GraphicalSquare extends Button {

  private GraphicalMark mark;

  public GraphicalMark getMark() {
    return mark;
  }

  public void setMark(GraphicalMark mark) {
    this.mark = mark;
    setText(mark.getText());
  }
}
