package uk.ashleybye.tictactoe.core.player;

import java.util.Objects;
import uk.ashleybye.tictactoe.core.ClientInterface;
import uk.ashleybye.tictactoe.core.Game;
import uk.ashleybye.tictactoe.core.board.Mark;

public class HumanPlayer implements Player {

  private final Mark mark;
  private final String name;
  private final ClientInterface clientInterface;

  public HumanPlayer(Mark mark, String name, ClientInterface clientInterface) {
    this.mark = mark;
    this.name = name;
    this.clientInterface = clientInterface;
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
    return clientInterface.getPlayersMove();
  }

  @Override
  public PlayerType getType() {
    return PlayerType.USER;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HumanPlayer that = (HumanPlayer) o;
    return Objects.equals(mark, that.mark) &&
        Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mark, name);
  }
}
