package uk.ashleybye.tictactoe.console.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.core.player.EasyPlayer;
import uk.ashleybye.tictactoe.core.player.HardPlayer;
import uk.ashleybye.tictactoe.core.player.HumanPlayer;
import uk.ashleybye.tictactoe.core.player.Player;
import uk.ashleybye.tictactoe.core.player.PlayerFactory;

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
    ConsolePlayerConfiguration playerConfiguration = new ConsolePlayerConfiguration();
    playerConfiguration.setPlayerName("Player 1");
    playerConfiguration.setPlayerMark(new ConsoleMark("X"));
    playerConfiguration.setPlayerType("human");

    Player player = factory.make(playerConfiguration);

    assertEquals(expected, player);
  }

  @Test
  void testCreatesEasyPlayer() {
    Player expected = new EasyPlayer(new ConsoleMark("X"), "Player 1");
    ConsolePlayerConfiguration playerConfiguration = new ConsolePlayerConfiguration();
    playerConfiguration.setPlayerName("Player 1");
    playerConfiguration.setPlayerMark(new ConsoleMark("X"));
    playerConfiguration.setPlayerType("easy");

    Player player = factory.make(playerConfiguration);

    assertEquals(expected, player);
  }

  @Test
  void testCreatesHardPlayer() {
    Player expected = new HardPlayer(new ConsoleMark("X"), "Player 1");
    ConsolePlayerConfiguration playerConfiguration = new ConsolePlayerConfiguration();
    playerConfiguration.setPlayerName("Player 1");
    playerConfiguration.setPlayerMark(new ConsoleMark("X"));
    playerConfiguration.setPlayerType("hard");

    Player player = factory.make(playerConfiguration);

    assertEquals(expected, player);
  }

  @Test
  void testUnknownPlayerTypeThrowsException() {
    ConsolePlayerConfiguration playerConfiguration = new ConsolePlayerConfiguration();
    playerConfiguration.setPlayerName("Player");
    playerConfiguration.setPlayerMark(new ConsoleMark("U"));
    playerConfiguration.setPlayerType("unknown");

    assertThrows(IllegalArgumentException.class,
        () -> factory.make(playerConfiguration));
  }
}
