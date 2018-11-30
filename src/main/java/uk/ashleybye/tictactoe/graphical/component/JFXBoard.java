package uk.ashleybye.tictactoe.graphical.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import uk.ashleybye.tictactoe.core.board.Mark;

public class JFXBoard extends GridPane {

  private final JFXGame game;
  private Map<Integer, Coordinate> positions = new HashMap<>();

  public JFXBoard(JFXGame game) {
    super();
    this.game = game;

    setupBoard();
  }

  private void setupBoard() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        initialiseBoard(i, j);
        initialisePositions(i, j);
      }
    }
  }

  private void initialiseBoard(int row, int col) {
    int position = positionFor(row, col);
    JFXSquare square = new JFXSquare(position, this);
    square.setId(String.format("%s-%d", "square", position));
    square.setMinSize(30, 30);
    square.setMaxSize(30, 30);
    square.setMark(new JFXMark(" "));
    this.add(square, col, row);
  }

  private void initialisePositions(int row, int col) {
    Coordinate coordinate = new Coordinate(row, col);
    positions.put(positionFor(row, col), coordinate);
  }

  private int positionFor(int row, int col) {
    int rowBaseNumber = 3 * row;
    int columnOffsetByOne = col + 1;
    return rowBaseNumber + columnOffsetByOne;
  }

  JFXSquare getSquare(int square) {
    Coordinate coordinate = positions.get(square);
    List<Node> children = getChildren();
    for (Node node : children) {
      if (GridPane.getRowIndex(node) == coordinate.getRow() & GridPane.getColumnIndex(node) == coordinate.getCol()) {
        return (JFXSquare) node;
      }
    }
    throw new IllegalArgumentException("this square does not exist");
  }

  void update(Map<Integer, Mark> currentBoard) {
    for (Integer position : currentBoard.keySet()) {
      JFXSquare square = getSquare(position);
      square.setMark((JFXMark) currentBoard.get(position));
    }
  }

  void receiveSquareClicked(int square) {
    game.receiveSquareClicked(square);
  }

  private class Coordinate {

    private final int row;
    private final int col;

    Coordinate(int row, int col) {
      this.row = row;
      this.col = col;
    }

    public int getRow() {
      return row;
    }

    public int getCol() {
      return col;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Coordinate that = (Coordinate) o;
      return row == that.row &&
          col == that.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }
}
