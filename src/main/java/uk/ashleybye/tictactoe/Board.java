package uk.ashleybye.tictactoe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Board implements Iterable<Square> {

  private final List<Square> squares;

  public Board(Mark emptyMark) {
    squares = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      squares.add(new Square(i + 1, emptyMark));
    }
  }

  private Board(Board board) {
    this.squares = new ArrayList<>();
    for (Square square : board)
      this.squares.add(square);
  }

  public List<Square> listUnmarkedSquares() {
    List<Square> unmarkedSquares = new ArrayList<>();
    for (Square square : squares)
      if (!square.isMarked())
        unmarkedSquares.add(square);
    return unmarkedSquares;
  }

  public Board markSquare(int squareNumber, Mark mark) {
    throwExceptionIfInvalid(squareNumber);

    Board board = new Board(this);
    board.squares.set(squareNumber - 1, new Square(squareNumber, mark));
    return board;
  }

  private void throwExceptionIfInvalid(int squareNumber) {
    if (squareNumber < 1 || squareNumber > squares.size())
      throw new InvalidSquareNumber();
    if (listUnmarkedSquares().size() == 0 || squares.get(squareNumber - 1).isMarked())
      throw new SquareUnavailable();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Board board = (Board) o;
    return Objects.equals(squares, board.squares);
  }

  @Override
  public int hashCode() {
    return Objects.hash(squares);
  }

  @Override
  public Iterator<Square> iterator() {
    return new BoardIterator();
  }

  private class BoardIterator implements Iterator<Square> {

    int currentIteration = 0;

    @Override
    public boolean hasNext() {
      return currentIteration < squares.size();
    }

    @Override
    public Square next() {
      if (!hasNext())
        throw new NoSuchElementException("attempting to access non-existing square on board");
      return squares.get(currentIteration++);
    }

  }

  class SquareUnavailable extends RuntimeException {

    SquareUnavailable() {
      super("square has already been marked");
    }
  }

  class InvalidSquareNumber extends RuntimeException {

    InvalidSquareNumber() {
      super("invalid square number provided");
    }

  }
}
