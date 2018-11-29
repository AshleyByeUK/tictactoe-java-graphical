package uk.ashleybye.tictactoe.graphical.component;

import javafx.scene.control.Button;

public class JFXSquare extends Button {

  private final int position;
  private final JFXBoard board;
  private JFXMark mark;

  public JFXSquare(int position, JFXBoard board) {
    super();
    this.position = position;
    this.board = board;
    setOnAction(click -> handleClick(this));
  }

  public int getPosition() {
    return position;
  }

  public JFXMark getMark() {
    return mark;
  }

  public void setMark(JFXMark mark) {
    this.mark = mark;
    setText(mark.getText());
  }

  void handleClick(JFXSquare square) {
    if (mark.isEmpty()) {
      board.receiveSquareClicked(square.getPosition());
    }
  }
}
