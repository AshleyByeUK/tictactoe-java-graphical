package uk.ashleybye.tictactoe.graphical;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ViewManagerTest {

  @Test
  void testGettingViewManagerBeforeInitialisationThrowsRuntimeException() {
    ViewManager.nullifyViewManager();
    assertThrows(RuntimeException.class, ViewManager::getViewManager);
  }
}
