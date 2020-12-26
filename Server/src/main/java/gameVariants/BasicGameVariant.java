package gameVariants;

import logic.Point;

import java.util.Set;

public class BasicGameVariant extends GameVariant {
  public BasicGameVariant() {
    this.blockAllowed = true;
  }

  @Override
  public boolean isValidMove(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    String pawnColor = board.getPawn(oldVerticalID, oldHorizontalID).getColor();
    if (isInDestinationCorner(pawnColor, oldVerticalID, oldHorizontalID)) {
      if (!isInDestinationCorner(pawnColor, newVerticalID, newHorizontalID)) {
        return false;
      }
    }

    return upLeft(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
            || upRight(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
            || left(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
            || right(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
            || bottomLeft(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)
            || bottomRight(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
  }

  private boolean isInDestinationCorner(String pawnColor, int verticalID, int horizontalID) {
    Set<Point> destinationCorner = board.getDestinationCorner(pawnColor);

    System.out.println(pawnColor);
    for (Point p: destinationCorner) {
      System.out.println(p.getVerticalID() + " " + p.getHorizontalID());
    }

    System.out.println("Szukane: " + verticalID + " " + horizontalID);
    return destinationCorner.contains(new Point(verticalID, horizontalID));
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