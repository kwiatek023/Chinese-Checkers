package gameVariants;

import logic.AbstractBoard;

public abstract class GameVariant {
  protected AbstractBoard board;

  public abstract boolean isValidMove(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID);

  public abstract boolean hasFinished(String pawnColor);

  public abstract boolean isNextJumpPossible();

  public void setBoard(AbstractBoard board) {
    this.board = board;
  }
}