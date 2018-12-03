package uk.ashleybye.tictactoe.graphical.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.PlayerFactory;
import uk.ashleybye.tictactoe.core.player.EasyPlayer;
import uk.ashleybye.tictactoe.core.player.HardPlayer;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.graphical.JavaFXTest;
import uk.ashleybye.tictactoe.graphical.component.GraphicalMark;
import uk.ashleybye.tictactoe.graphical.controller.GameController;

class GraphicalPlayerFactoryTest extends JavaFXTest {

  private GameController controller;
  private PlayerFactory factory;

  @BeforeEach
  void setUp() {
    controller = new GameController();
    factory = new GraphicalPlayerFactory(controller);
  }

  @Test
  void testProvidesListOfValidTypes() {
    assertEquals("human", factory.listPlayerTypes().get(0));
    assertEquals("easy", factory.listPlayerTypes().get(1));
    assertEquals("hard", factory.listPlayerTypes().get(2));
  }

  @Test
  void testCreatesHumanPlayer() {
    Player expected = new HumanPlayer(new GraphicalMark("X"), "Player 1", controller);
    Player player = factory.make("human", "Player 1", new GraphicalMark("X"));

    assertEquals(expected, player);
  }

  @Test
  void testCreatesEasyPlayer() {
    Player expected = new EasyPlayer(new GraphicalMark("X"), "Player 1");
    Player player = factory.make("easy", "Player 1", new GraphicalMark("X"));

    assertEquals(expected, player);
  }

  @Test
  void testCreatesHardPlayer() {
    Player expected = new HardPlayer(new GraphicalMark("X"), "Player 1");
    Player player = factory.make("hard", "Player 1", new GraphicalMark("X"));

    assertEquals(expected, player);
  }

  @Test
  void testUnknownPlayerTypeThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> factory.make("unknown", "Player", new GraphicalMark("U")));
  }
}
