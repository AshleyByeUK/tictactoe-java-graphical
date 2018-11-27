package uk.ashleybye.tictactoe.console;

import java.util.Scanner;

public class IOWrapper {

  public void clearScreen() {
    render("\033[2J");
  }

  public void render(String text) {
    System.out.print(text);
  }

  public String getInput() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
