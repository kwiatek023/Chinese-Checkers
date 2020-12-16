package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
  @Test
  public void testNumberOfFields() {
    Board board = new Board(2);
    int fieldCounter = 0;
    int expectedNumberOfFields = 121;

    for (int i = 0; i < board.noRows; i++) {
      for (int j = 0; j < board.noRows; j++) {
        if (board.fields[i][j] != null) {
          fieldCounter++;
        }
      }
    }

    assertEquals(expectedNumberOfFields, fieldCounter);
  }
}