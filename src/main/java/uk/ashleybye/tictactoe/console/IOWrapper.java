package uk.ashleybye.tictactoe.console;

import java.util.Scanner;

public class IOWrapper {

  public void render(String text) {
    System.out.print(text);
  }

  public String getInput() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
