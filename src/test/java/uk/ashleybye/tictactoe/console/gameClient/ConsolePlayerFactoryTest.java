package uk.ashleybye.tictactoe.console.gameClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.PlayerFactory;
import uk.ashleybye.tictactoe.core.player.EasyPlayer;
import uk.ashleybye.tictactoe.core.player.HardPlayer;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;
import uk.ashleybye.tictactoe.core.player.Player;

class ConsolePlayerFactoryTest {

  private GameConsole console;
  private PlayerFactory factory;

  @BeforeEach
  void setUp() {
    console = new MockGameConsole();
    factory = new ConsolePlayerFactory(console);
  }

  @Test
  void testProvidesListOfValidTypes() {
    assertEquals("human", factory.listPlayerTypes().get(0));
    assertEquals("easy", factory.listPlayerTypes().get(1));
    assertEquals("hard", factory.listPlayerTypes().get(2));
  }

  @Test
  void testCreatesHumanPlayer() {
    Player expected = new HumanPlayer(new ConsoleMark("X"), "Player 1", console);
    Player player = factory.make("human", "Player 1", new ConsoleMark("X"));

    assertEquals(expected, player);
  }

  @Test
  void testCreatesEasyPlayer() {
    Player expected = new EasyPlayer(new ConsoleMark("X"), "Player 1");
    Player player = factory.make("easy", "Player 1", new ConsoleMark("X"));

    assertEquals(expected, player);
  }

  @Test
  void testCreatesHardPlayer() {
    Player expected = new HardPlayer(new ConsoleMark("X"), "Player 1");
    Player player = factory.make("hard", "Player 1", new ConsoleMark("X"));

    assertEquals(expected, player);
  }

  @Test
  void testUnknownPlayerTypeThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> factory.make("unknown", "Player", new ConsoleMark("U")));
  }
}
