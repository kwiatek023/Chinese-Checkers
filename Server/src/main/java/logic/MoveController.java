package logic;

import gameVariants.GameVariant;

public class MoveController {
  Board board;
  GameVariant gameVariant;

  public MoveController(Board board, GameVariant gameVariant) {
    this.board = board;
    this.gameVariant = gameVariant;
  }

  private boolean validMove() {
    return gameVariant.validMove();
  }


}
