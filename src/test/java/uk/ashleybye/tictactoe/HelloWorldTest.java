package uk.ashleybye.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloWorldTest {
  @Test
  void testHelloWorld() {
    HelloWorld hello = new HelloWorld();
    assertEquals("Hello, world!", hello.world());
  }
}
