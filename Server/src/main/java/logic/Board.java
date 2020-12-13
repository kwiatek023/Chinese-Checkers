package logic;

import gameVariants.GameVariant;

public class Board {
  private GameVariant gameVariant;
  private int noPlayers;

  public Board(GameVariant gameVariant, int noPlayers) {
    this.gameVariant = gameVariant;
    if (noPlayers < 2 || noPlayers == 5 || noPlayers > 6) {
        throw new IllegalArgumentException("Invalid number of players.");
    }

    this.noPlayers = noPlayers;
  }
}
