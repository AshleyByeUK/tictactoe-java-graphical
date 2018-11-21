package uk.ashleybye.tictactoe.game.play;

import uk.ashleybye.tictactoe.game.Game;

public class GeneratesGameOverview {
  public GameOverview parse(Game game) {
    GameOverview gameOverview = new GameOverview();
    gameOverview.currentBoard = game.getBoard();
    gameOverview.openPositions = game.getOpenPositions();
    gameOverview.currentGameState = game.getState().toString().toLowerCase();
    gameOverview.currentPlayer = game.getCurrentPlayer();
    gameOverview.lastMoveValid = game.isLastMoveValid() ? "valid" : "invalid";
    return gameOverview;
  }
}
