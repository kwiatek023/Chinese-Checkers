package logic;

import java.util.Map;
import java.util.Set;

public class AbstractBoard {
  protected int noPlayers;
  protected int noRows;
  protected int[] noFieldsInRow;
  protected int[] horizontalConstant;
  protected int[] noIgnoredFields;
  protected Pawn[][] pawns;
  protected Set<Point> upRightCorner;
  protected Set<Point> upCorner;
  protected Set<Point> upLeftCorner;
  protected Set<Point> bottomRightCorner;
  protected Set<Point> bottomCorner;
  protected Set<Point> bottomLeftCorner;
  protected Map<String, Set<Point>> colorToDestinationCorner;

  /** Getter for a concrete pawn.
   * @param verticalID verticalID of a pawn
   * @param horizontalID horizontalID of a pawn
   * @return pawn
   */
  public Pawn getPawn(int verticalID, int horizontalID) {
    return pawns[verticalID][horizontalID];
  }

  /** Player's destination corner getter
   * @param pawnColor Player's color
   * @return Set of points, which belong to destination corner
   */
  public Set<Point> getDestinationCorner(String pawnColor) {
    return colorToDestinationCorner.get(pawnColor);
  }
}
