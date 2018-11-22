package uk.ashleybye.tictactoe;

import java.util.List;
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
    if (isTied())
      return GameState.GAME_OVER;
    if (listOpenPositions().size() < 9)
      return GameState.PLAYING;
    else
      return GameState.READY;
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
    Player player = getCurrentPlayer();
    int position = player.choosePositionToPlay();
    Board board = this.board.markSquare(position, player.getMark());
    Game game = new Game(playerOne, playerTwo, board);
    game.swapPlayers();
    return game;
  }

  private void swapPlayers() {
    currentPlayer = currentPlayer == 1 ? 2 : 1;
  }

  public boolean isWon(Player player) {
    return false;
  }

  public boolean isTied() {
    return listOpenPositions().size() == 0;
  }
}
