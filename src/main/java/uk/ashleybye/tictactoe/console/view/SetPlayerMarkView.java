package uk.ashleybye.tictactoe.console.view;

import uk.ashleybye.tictactoe.console.gameClient.ConsoleGameConfiguration;
import uk.ashleybye.tictactoe.console.gameClient.ConsolePlayerConfiguration;

public class SetPlayerMarkView extends View {

  private static final String DUPLICATE_MARK = "Cannot have the same mark as %s, try again";
  private static final String ENTER_PLAYER_MARK = "Enter a new mark for %s";

  private final int player;
  private final int otherPlayer;
  private boolean duplicateMark = false;

  SetPlayerMarkView(View previousMenu, ConsoleGameConfiguration configuration, int player) {
    super(previousMenu, configuration);
    this.player = player;
    this.otherPlayer = player == 1 ? 2 : 1;
  }

  @Override
  public String launch() {
    return String.format(ENTER_PLAYER_MARK, String.format(PLAYER_HEADING, player)) + "\n\n"
        + PROMPT;
  }

  @Override
  public View handleInput(String input) {
    ConsolePlayerConfiguration playerConfiguration = configuration.getPlayerConfiguration(player);
    ConsolePlayerConfiguration otherPlayerConfiguration = configuration.getPlayerConfiguration(otherPlayer);
    playerConfiguration.setPlayerMark(input.strip().toUpperCase());
    configuration.setPlayerConfiguration(player, playerConfiguration);

    if (playerConfiguration.getPlayerMark().equals(otherPlayerConfiguration.getPlayerMark()))
      return handleDuplicateMark();
    else
      return previousMenu;
  }

  private View handleDuplicateMark() {
    duplicateMark = true;
    throw new DuplicateMark();
  }

  @Override
  public String handleBadInput() {
    if (duplicateMark)
      return textForDuplicateMark();
    else
      return super.handleBadInput();
  }

  private String textForDuplicateMark() {
    duplicateMark = false;
    return String.format(DUPLICATE_MARK, configuration.getPlayerConfiguration(otherPlayer).getPlayerName()) + "\n\n";
  }

  public static class DuplicateMark extends RuntimeException {}
}
