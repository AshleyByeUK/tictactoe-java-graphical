package uk.ashleybye.tictactoe.graphical.component;

import java.util.Objects;
import javafx.scene.control.Label;
import uk.ashleybye.tictactoe.core.board.Mark;

public class GraphicalMark extends Label implements Mark {

  public GraphicalMark(String mark) {
    super(mark, null);
  }

  @Override
  public boolean isEmpty() {
    return getText().equals(" ");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GraphicalMark that = (GraphicalMark) o;
    return Objects.equals(getText(), that.getText());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getText());
  }
}
