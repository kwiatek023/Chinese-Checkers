package gameVariants;

import logic.AbstractBoard;

/**
 * Defines rules of the game.
 */
public abstract class GameVariant {
  /**
   * Rules are applied to this board.
   */
  protected AbstractBoard board;

  /** This method checks if move is correct.
   * @param oldVerticalID old vertical ID of a pawn
   * @param oldHorizontalID old horizontal ID of a pawn
   * @param newVerticalID new vertical ID of a pawn
   * @param newHorizontalID new horizontal ID of a pawn
   * @return true if move is correct, false otherwise
   */
  public abstract boolean isValidMove(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID);

  /** Checks if player has finished the game.
   * @param pawnColor color of player
   * @return true if player has finished, false otherwise
   */
  public abstract boolean hasFinished(String pawnColor);

  /** Checks if next jump is possible.
   * @return true if possible, false otherwise
   */
  public abstract boolean isNextJumpPossible();

  public void setBoard(AbstractBoard board) {
    this.board = board;
  }
}