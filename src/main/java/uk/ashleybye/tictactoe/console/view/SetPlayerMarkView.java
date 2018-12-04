package uk.ashleybye.tictactoe.console.view;

import uk.ashleybye.tictactoe.console.IOWrapper;
import uk.ashleybye.tictactoe.console.game.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.game.ConsoleMark;
import uk.ashleybye.tictactoe.console.game.ConsolePlayerConfiguration;

public class SetPlayerMarkView extends View {

  private static final String DUPLICATE_MARK = "Cannot have the same mark as %s, try again";
  private static final String ENTER_PLAYER_MARK = "Enter a new mark for %s";

  private final int player;
  private final int otherPlayer;
  private boolean duplicateMark = false;

  SetPlayerMarkView(View previousMenu,
      ConsoleGameConfiguration configuration,
      int player,
      IOWrapper ioWrapper) {
    super(previousMenu, configuration, ioWrapper);
    this.player = player;
    this.otherPlayer = player == 1 ? 2 : 1;
  }

  @Override
  public void render() {
    ioWrapper.render(String.format("%s\n\n%s",
        String.format(ENTER_PLAYER_MARK, String.format(PLAYER_HEADING, player)),
        PROMPT));
  }

  @Override
  public View handleInput(String input) {
    ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(player);
    changeMark(input, playerConfiguration);
    return applyChangeIfNotDuplicateMark(playerConfiguration);
  }

  private void changeMark(String input, ConsolePlayerConfiguration playerConfiguration) {
    ConsoleMark oldMark = (ConsoleMark) playerConfiguration.getPlayerMark();
    ConsoleMark newMark = new ConsoleMark(input.strip().toUpperCase(), oldMark.getColour());
    playerConfiguration.setPlayerMark(newMark);
    configuration.setPlayerConfiguration(player, playerConfiguration);
  }

  private View applyChangeIfNotDuplicateMark(ConsolePlayerConfiguration playerConfiguration) {
    ConsolePlayerConfiguration otherPlayerConfiguration = configuration.getPlayerConfiguration(otherPlayer);
    if (playerConfiguration.getPlayerMark().equals(otherPlayerConfiguration.getPlayerMark())) {
      return handleDuplicateMark();
    } else {
      return previousMenu;
    }
  }

  private View handleDuplicateMark() {
    duplicateMark = true;
    throw new DuplicateMark();
  }

  @Override
  public String handleBadInput() {
    if (duplicateMark) {
      return textForDuplicateMark();
    } else {
      return super.handleBadInput();
    }
  }

  private String textForDuplicateMark() {
    duplicateMark = false;
    return String.format(DUPLICATE_MARK, configuration.getPlayerConfiguration(otherPlayer).getPlayerName()) + "\n\n";
  }

  public static class DuplicateMark extends RuntimeException {

  }
}
