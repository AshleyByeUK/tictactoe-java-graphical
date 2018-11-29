package uk.ashleybye.tictactoe.core.player;

import java.util.List;
import java.util.Objects;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.board.Board;
import uk.ashleybye.tictactoe.core.board.Mark;

public class HardPlayer implements Player {

  private final Mark mark;
  private final String name;
  private Player opponent;

  public HardPlayer(Mark mark, String name) {
    this.mark = mark;
    this.name = name;
  }

  @Override
  public Mark getMark() {
    return mark;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int takeTurn(Game game) {
    opponent = game.getOtherPlayer();
    return findBestPosition(game);
  }

  private int findBestPosition(Game game) {
    int bestScore = Integer.MIN_VALUE;
    int bestPosition = Integer.MIN_VALUE;

    List<Integer> openPositions = game.listOpenPositions();
    for (int position : openPositions) {
      Board board = game.getBoard().markSquare(position, mark);
      Game updatedGame = new Game(opponent, this, board);
      int score = minimax(updatedGame, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
      if (score > bestScore) {
        bestScore = score;
        bestPosition = position;
      }
    }

    return bestPosition;
  }

  private int minimax(Game game, int depth, boolean maximising, int alpha, int beta) {
    if (game.isGameOver() || depth >= 5) {
      return score(game, depth);
    }

    if (maximising) {
      return maximise(game, depth, maximising, alpha, beta);
    } else {
      return minimise(game, depth, maximising, alpha, beta);
    }
  }

  private int maximise(Game game, int depth, boolean maximising, int alpha, int beta) {
    int maxScore = Integer.MIN_VALUE;

    List<Integer> openPositions = game.listOpenPositions();
    for (int position : openPositions) {
      Board board = game.getBoard().markSquare(position, mark);
      Game updatedGame = new Game(opponent, this, board);
      int score = minimax(updatedGame, depth + 1, !maximising, alpha, beta);
      maxScore = Math.max(maxScore, score);
      alpha = Math.max(maxScore, alpha);
      if (beta <= alpha) {
        break;
      }
    }

    return maxScore;
  }

  private int minimise(Game game, int depth, boolean maximising, int alpha, int beta) {
    int minScore = Integer.MAX_VALUE;

    List<Integer> openPositions = game.listOpenPositions();
    for (int position : openPositions) {
      Board board = game.getBoard().markSquare(position, opponent.getMark());
      Game updatedGame = new Game(this, opponent, board);
      int score = minimax(updatedGame, depth + 1, !maximising, alpha, beta);
      minScore = Math.min(minScore, score);
      beta = Math.max(minScore, beta);
      if (beta <= alpha) {
        break;
      }
    }

    return minScore;
  }

  private int score(Game game, int depth) {
    if (game.isWon(this)) {
      return 100 - depth;
    } else if (game.isWon(opponent)) {
      return -100 + depth;
    } else {
      return 0;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HardPlayer that = (HardPlayer) o;
    return Objects.equals(mark, that.mark) &&
        Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mark, name);
  }
}

