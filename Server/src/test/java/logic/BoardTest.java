package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
  @Test
  public void testNumberOfPawnsForTwoPlayers() {
    Board board = new Board(2);
    int pawnCounter = 0;
    int expectedNumberOfPawns = 20;
    for (int i = 0; i < board.noRows; i++) {
      for (int j = 0; j < board.noRows; j++) {
        if (board.pawns[i][j] != null) {
          pawnCounter++;
        }
      }
    }
    assertEquals(expectedNumberOfPawns, pawnCounter);
  }

  @Test
  public void testNumberOfPawnsForThreePlayers() {
    Board board = new Board(3);
    int pawnCounter = 0;
    int expectedNumberOfPawns = 30;
    for (int i = 0; i < board.noRows; i++) {
      for (int j = 0; j < board.noRows; j++) {
        if (board.pawns[i][j] != null) {
          pawnCounter++;
        }
      }
    }
    assertEquals(expectedNumberOfPawns, pawnCounter);
  }

  @Test
  public void testNumberOfPawnsForFourPlayers() {
    Board board = new Board(4);
    int pawnCounter = 0;
    int expectedNumberOfPawns = 40;
    for (int i = 0; i < board.noRows; i++) {
      for (int j = 0; j < board.noRows; j++) {
        if (board.pawns[i][j] != null) {
          pawnCounter++;
        }
      }
    }
    assertEquals(expectedNumberOfPawns, pawnCounter);
  }

  @Test
  public void testNumberOfPawnsForSixPlayers() {
    Board board = new Board(6);
    int pawnCounter = 0;
    int expectedNumberOfPawns = 60;
    for (int i = 0; i < board.noRows; i++) {
      for (int j = 0; j < board.noRows; j++) {
        if (board.pawns[i][j] != null) {
          pawnCounter++;
        }
      }
    }
    assertEquals(expectedNumberOfPawns, pawnCounter);
  }

  @Test
  public void testInappropriateNoPlayers() {
    assertThrows(IllegalArgumentException.class, () -> new Board(5));
  }

  @Test
  public void testUpdatePawns() {
    Board board = new Board(2);
    int oldVerticalID = 3;
    int oldHorizontalID = 4;
    int newVerticalID = 4;
    int newHorizontalID = 4;
    assertNotNull(board.pawns[oldVerticalID][oldHorizontalID]);
    assertNull(board.pawns[newVerticalID][newHorizontalID]);
    board.updatePawns(3, 4, 4, 4);
    assertNull(board.pawns[oldVerticalID][oldHorizontalID]);
    assertNotNull(board.pawns[newVerticalID][newHorizontalID]);
    assertEquals(board.pawns[newVerticalID][newHorizontalID].getVerticalID(), 4);
    assertEquals(board.pawns[newVerticalID][newHorizontalID].getHorizontalID(), 4);
  }

  @Test
  public void testGetPawn() {
    Board board = new Board(2);

    assertEquals(board.pawns[0][4], board.getPawn(0, 4));
  }
}