package logic;

import gameVariants.GameVariant;

public class MoveController {
  Board board;
  GameVariant gameVariant;

  public MoveController(Board board, GameVariant gameVariant) {
    this.board = board;
    this.gameVariant = gameVariant;
  }

  public boolean validMove(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    return gameVariant.validMove();
  }
}
