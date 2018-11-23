package uk.ashleybye.tictactoe.game;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Game {

  private final Player playerOne;
  private final Player playerTwo;
  private Board board;
  private int currentPlayer;

  public Game(Player playerOne, Player playerTwo, Mark emptyMark) {
    this(playerOne, playerTwo, new Board(emptyMark));
  }

  Game(Player playerOne, Player playerTwo, Board board) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    this.board = board;
    currentPlayer = 1;
  }

  public Board getBoard() {
    return board;
  }

  public Player getCurrentPlayer() {
    return currentPlayer == 1 ? playerOne : playerTwo;
  }

  public Player getOtherPlayer() {
    return currentPlayer == 1 ? playerTwo : playerOne;
  }

  public GameState getGameState() {
    if (isGameOver())
      return GameState.GAME_OVER;
    if (listOpenPositions().size() < 9)
      return GameState.PLAYING;
    else
      return GameState.READY;
  }

  private boolean isGameOver() {
    return isTied() || isWon(playerOne) || isWon(playerTwo);
  }

  public List<Integer> listOpenPositions() {
    return board
        .listUnmarkedSquares()
        .stream()
        .filter(square -> !square.isMarked())
        .map(square -> square.getPosition())
        .collect(Collectors.toList());
  }

  public Game playNextTurn() {
    if (isGameOver())
      return new Game(playerOne, playerTwo, board);
    else
      return gameWithTurnApplied();
  }

  private Game gameWithTurnApplied() {
    return new Game(playerOne, playerTwo, boardWithTurnApplied())
        .swapPlayers();
  }

  private Board boardWithTurnApplied() {
    return this.board.markSquare(getCurrentPlayer().choosePositionToPlay(this), getCurrentPlayer().getMark());
  }

  private Game swapPlayers() {
    currentPlayer = currentPlayer == 1 ? 2 : 1;
    return this;
  }

  public boolean isWon(Player player) {
    return board
        .listPossibleWinningSquares()
        .stream()
        .anyMatch(wc -> isWiningCombination(wc, player.getMark()));
  }

  private boolean isWiningCombination(List<Square> possibleWinningCombination, Mark mark) {
    return possibleWinningCombination.get(0).getMark().equals(mark)
        && possibleWinningCombination.get(1).getMark().equals(mark)
        && possibleWinningCombination.get(2).getMark().equals(mark);
  }

  public boolean isTied() {
    return listOpenPositions().size() == 0 && !(isWon(playerOne) || isWon(playerTwo));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Game game = (Game) o;
    return currentPlayer == game.currentPlayer &&
        Objects.equals(playerOne, game.playerOne) &&
        Objects.equals(playerTwo, game.playerTwo) &&
        Objects.equals(board, game.board);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerOne, playerTwo, board, currentPlayer);
  }
}
