package gameVariants;

import logic.AbstractBoard;

public abstract class GameVariant {
  protected AbstractBoard board;
  protected boolean blockAllowed;

  public abstract boolean isValidMove(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID);

  public void setBoard(AbstractBoard board) {
    this.board = board;
  }
}
