package gameVariants;

public class BasicGameVariant extends GameVariant {

  public BasicGameVariant() {
    this.blockAllowed = true;
  }

  @Override
  public boolean isValidMove(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    return upLeft(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
        || upRight(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
        || left(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
        || right(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
        || bottomLeft(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
        || bottomRight(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
  }

  private boolean upLeft(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    if (newVerticalID - oldVerticalID == -2 && newHorizontalID - oldHorizontalID == -2) {
      if (board.getPawn(newVerticalID + 1, newHorizontalID + 1) != null) {
        return true;
      }
    } else if (newVerticalID - oldVerticalID == -1 && newHorizontalID - oldHorizontalID == -1) {
      return true;
    }
    return false;
  }

  private boolean upRight(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    if (newVerticalID - oldVerticalID == -2 && newHorizontalID - oldHorizontalID == 0) {
      if (board.getPawn(newVerticalID + 1, newHorizontalID) != null) {
        return true;
      }
    } else if (newVerticalID - oldVerticalID == -1 && newHorizontalID - oldHorizontalID == 0) {
      return true;
    }
    return false;
  }

  private boolean right(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    if (newVerticalID - oldVerticalID == 0 && newHorizontalID - oldHorizontalID == 2) {
      if (board.getPawn(newVerticalID, newHorizontalID + 1) != null) {
        return true;
      }
    } else if (newVerticalID - oldVerticalID == 0 && newHorizontalID - oldHorizontalID == 1) {
      return true;
    }
    return false;
  }

  private boolean left(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    if (newVerticalID - oldVerticalID == 0 && newHorizontalID - oldHorizontalID == -2) {
      if (board.getPawn(newVerticalID, newHorizontalID - 1) != null) {
        return true;
      }
    } else if (newVerticalID - oldVerticalID == 0 && newHorizontalID - oldHorizontalID == -1) {
      return true;
    }
    return false;
  }

  private boolean bottomRight(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    if (newVerticalID - oldVerticalID == 2 && newHorizontalID - oldHorizontalID == 2) {
      if (board.getPawn(newVerticalID - 1, newHorizontalID - 1) != null) {
        return true;
      }
    } else if (newVerticalID - oldVerticalID == 1 && newHorizontalID - oldHorizontalID == 1) {
      return true;
    }
    return false;
  }

  private boolean bottomLeft(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    if (newVerticalID - oldVerticalID == 2 && newHorizontalID - oldHorizontalID == 0) {
      if (board.getPawn(newVerticalID - 1, newHorizontalID) != null) {
        return true;
      }
    } else if (newVerticalID - oldVerticalID == 1 && newHorizontalID - oldHorizontalID == 0) {
      return true;
    }
    return false;
  }
}