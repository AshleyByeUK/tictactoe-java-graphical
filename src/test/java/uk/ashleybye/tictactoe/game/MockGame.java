package uk.ashleybye.tictactoe.game;

import java.util.List;

public class MockGame extends Game {
  private int numberOfTimesGetStateHasBeenCalled = 0;
  private int numberOfTimesGetNextMoveHasBeenCalled = 0;
  private List<Move> moves;
  private Move lastAppliedMove;
  private List<GameState> gameStates;
  private int currentPlayerNumber;
  private List<Integer> currentBoard;
  private List<Integer> currentOpenPositions;
  private boolean lastMoveValid;

  @Override
  public Move getNextMove() {
    return moves.get(numberOfTimesGetNextMoveHasBeenCalled++);
  }

  @Override
  public Game applyMove(Move move) {
    this.lastAppliedMove = move;
    return this;
  }

  @Override
  public GameState getState() {
    return gameStates.get(numberOfTimesGetStateHasBeenCalled++);
  }

  @Override
  public List<Integer> getBoard() {
    return currentBoard;
  }

  @Override
  public List<Integer> getOpenPositions() {
    return currentOpenPositions;
  }

  @Override
  public Integer getCurrentPlayer() {
    return currentPlayerNumber;
  }

  @Override
  public Boolean isLastMoveValid() {
    return lastMoveValid;
  }

  public void returnMovesInOrder(List<Move> moves) {
    this.moves = moves;
  }

  public void returnGameStatesInOrder(List<GameState> gameStates) {
    this.gameStates = gameStates;
  }

  public int numberOfTimesGetNextMoveHasBeenCalled() {
    return numberOfTimesGetNextMoveHasBeenCalled;
  }

  public boolean applyMoveWasLastCalledWith(Move move) {
    return lastAppliedMove.equals(move);
  }

  public void returnCurrentPlayer(int playerNumber) {
    currentPlayerNumber = playerNumber;
  }

  public void returnCurrentBoard(List<Integer> board) {
    currentBoard = board;
  }

  public void returnOpenPositions(List<Integer> openPositions) {
    currentOpenPositions = openPositions;
  }

  public void returnLastMoveValid(boolean lastMoveValid) {
    this.lastMoveValid = lastMoveValid;
  }
}
