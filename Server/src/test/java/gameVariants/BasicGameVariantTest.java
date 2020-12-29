package gameVariants;

import logic.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicGameVariantTest {
  BasicGameVariant basicGameVariant;
  Board board;

  @BeforeEach
  public void prepareTest() {
    basicGameVariant = new BasicGameVariant();
    board = new Board(2);
    basicGameVariant.setBoard(board);
  }

  @Test
  public void testLeftBottomJump() {
    assertTrue(basicGameVariant.isValidMove(3, 4, 4, 4));
  }

  @Test
  public void testLeftUpJump() {
    assertTrue(basicGameVariant.isValidMove(13, 9, 12, 8));
  }

  @Test
  public void testRightBottomJump() {
    assertTrue(basicGameVariant.isValidMove(3, 4, 4, 5));
  }

  @Test
  public void testRightUpJump() {
    assertTrue(basicGameVariant.isValidMove(13, 9, 12, 9));
  }

  @Test
  public void testLeftJump() {
    board.updatePawns(3, 4, 4, 5);
    assertTrue(basicGameVariant.isValidMove(4, 5, 4, 4));
  }

  @Test
  public void testRightJump() {
    board.updatePawns(3, 4, 4, 5);
    assertTrue(basicGameVariant.isValidMove(4, 5, 4, 6));
  }

  @Test
  public void testRightJumpOverPawn() {
    board.updatePawns(3, 4, 4, 5);
    board.updatePawns(3, 5, 4, 4);
    assertTrue(basicGameVariant.isValidMove(4, 4, 4, 6));
  }

  @Test
  public void testLeftJumpOverPawn() {
    board.updatePawns(3, 4, 4, 5);
    board.updatePawns(3, 5, 4, 4);
    assertTrue(basicGameVariant.isValidMove(4, 5, 4, 3));
  }

  @Test
  public void testLeftBottomJumpOverPawn() {
    assertTrue(basicGameVariant.isValidMove(2, 4, 4, 4));
  }

  @Test
  public void testLeftUpJumpOverPawn() {
    assertTrue(basicGameVariant.isValidMove(14, 10, 12, 8));
  }

  @Test
  public void testRightBottomJumpOverPawn() {
    assertTrue(basicGameVariant.isValidMove(2, 4, 4, 6));
  }

  @Test
  public void testRightUpJumpOverPawn() {
    assertTrue(basicGameVariant.isValidMove(14, 12, 12, 12));
  }

  @Test
  public void testJumpOutFromDestination() {
    board.updatePawns(3, 4, 4, 5);
    board.updatePawns(13, 9, 3, 4);
    assertFalse(basicGameVariant.isValidMove(3, 4, 4, 4));
  }

  @Test
  public void testWrongJump() {
    board.updatePawns(3, 4, 4, 5);
    assertFalse(basicGameVariant.isValidMove(13, 9, 3, 4));
  }

  @Test
  public void testHasFinished() {
    assertFalse(basicGameVariant.hasFinished("GREEN"));
  }

  @Test
  public void testIfNextJumpIsPossible() {
    assertFalse(basicGameVariant.isNextJumpPossible());
  }
}