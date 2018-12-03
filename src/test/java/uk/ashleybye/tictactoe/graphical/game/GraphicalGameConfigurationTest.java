package uk.ashleybye.tictactoe.graphical.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.GameConfiguration;
import uk.ashleybye.tictactoe.core.player.PlayerConfiguration;
import uk.ashleybye.tictactoe.graphical.game.GraphicalGameConfiguration.InvalidPlayerNumber;
import uk.ashleybye.tictactoe.graphical.JavaFXTest;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;

class GraphicalGameConfigurationTest extends JavaFXTest {

  @BeforeEach
  void setUp() {
    GraphicalGameConfiguration.resetToDefaults();
  }

  @Test
  void testHasADefaultConfiguration() {
    GameConfiguration configuration = GraphicalGameConfiguration.getCurrentConfiguration();

    assertEquals(new GraphicalMark(" "), configuration.getEmptyMark());

    PlayerConfiguration playerOneConfiguration = configuration.getPlayerConfiguration(1);
    assertEquals(new GraphicalMark("X"), playerOneConfiguration.getPlayerMark());
    assertEquals("Player 1", playerOneConfiguration.getPlayerName());
    assertEquals("human", playerOneConfiguration.getPlayerType());

    PlayerConfiguration playerTwoConfiguration = configuration.getPlayerConfiguration(2);
    assertEquals(new GraphicalMark("O"), playerTwoConfiguration.getPlayerMark());
    assertEquals("Player 2", playerTwoConfiguration.getPlayerName());
    assertEquals("human", playerTwoConfiguration.getPlayerType());
  }

  @Test
  void testSingletonInstanceCanChangeConfigurationDetails() {
    GraphicalGameConfiguration configuration = GraphicalGameConfiguration.getCurrentConfiguration();
    configuration.setPlayerName(1, "New Player 1 Name");
    configuration.setPlayerMark(1, new GraphicalMark("A"));
    configuration.setPlayerType(1, "some_type");
    configuration.setPlayerName(2, "New Player 2 Name");
    configuration.setPlayerMark(2, new GraphicalMark("B"));
    configuration.setPlayerType(2, "some_other_type");

    GraphicalGameConfiguration updatedConfiguration = GraphicalGameConfiguration.getCurrentConfiguration();
    PlayerConfiguration playerOneConfiguration = updatedConfiguration.getPlayerConfiguration(1);
    assertEquals("New Player 1 Name", playerOneConfiguration.getPlayerName());
    assertEquals(new GraphicalMark("A"), playerOneConfiguration.getPlayerMark());
    assertEquals("some_type", playerOneConfiguration.getPlayerType());

    PlayerConfiguration playerTwoConfiguration = updatedConfiguration.getPlayerConfiguration(2);
    assertEquals(new GraphicalMark("B"), playerTwoConfiguration.getPlayerMark());
    assertEquals("New Player 2 Name", playerTwoConfiguration.getPlayerName());
    assertEquals("some_other_type", playerTwoConfiguration.getPlayerType());
  }

  @Test
  void testInvalidPlayerNumberThrowsInvalidPlayerNumber() {
    GraphicalGameConfiguration configuration = GraphicalGameConfiguration.getCurrentConfiguration();

    assertThrows(InvalidPlayerNumber.class, () -> configuration.setPlayerName(3, "Bad Player"));
    assertThrows(InvalidPlayerNumber.class, () -> configuration.setPlayerMark(3, new GraphicalMark("Z")));
    assertThrows(InvalidPlayerNumber.class, () -> configuration.setPlayerType(3, "bad_player"));
  }
}
