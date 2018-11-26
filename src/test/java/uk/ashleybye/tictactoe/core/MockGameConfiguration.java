package uk.ashleybye.tictactoe.core;

import uk.ashleybye.tictactoe.core.player.MockEmptyMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerOneMark;
import uk.ashleybye.tictactoe.core.player.MockPlayerTwoMark;

public class MockGameConfiguration implements GameConfiguration {

  @Override
  public PlayerConfiguration getPlayerConfiguration(int player) {
    if (player == 1)
      return new PlayerConfiguration() {
        @Override
        public String getPlayerType() {
          return "1";
        }

        @Override
        public String getPlayerName() {
          return "Player 1";
        }

        @Override
        public Mark getPlayerMark() {
          return new MockPlayerOneMark();
        }
      };
    else
      return new PlayerConfiguration() {
        @Override
        public String getPlayerType() {
          return "2";
        }

        @Override
        public String getPlayerName() {
          return "Player 2";
        }

        @Override
        public Mark getPlayerMark() {
          return new MockPlayerTwoMark();
        }
      };
  }

  @Override
  public Mark getEmptyMark() {
    return new MockEmptyMark();
  }
}
