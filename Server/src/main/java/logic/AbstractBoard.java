package logic;

import java.util.Map;
import java.util.Set;

public class AbstractBoard {
  int noPlayers;
  int noRows;
  int[] noFieldsInRow;
  int[] horizontalConstant;
  int[] noIgnoredFields;
  Field[][] fields;
  Pawn[][] pawns;
  Set<Point> upRightCorner;
  Set<Point> upCorner;
  Set<Point> upLeftCorner;
  Set<Point> bottomRightCorner;
  Set<Point> bottomCorner;
  Set<Point> bottomLeftCorner;
  Map<String, Set<Point>> colorToDestinationCorner;


  public int getNoPlayers() {
    return noPlayers;
  }

  public int getNoRows() {
    return noRows;
  }

  public int getNoFieldsInRow(int noRow) {
    return noFieldsInRow[noRow];
  }

  public Field getField(int verticalID, int horizontalID) {
    return fields[verticalID][horizontalID];
  }

  public Pawn getPawn(int verticalID, int horizontalID) {
    return pawns[verticalID][horizontalID];
  }

  public void setNoRows(int noRows) {
    this.noRows = noRows;
  }

  public void setNoFieldsInRow(int[] noFieldsInRow) {
    this.noFieldsInRow = noFieldsInRow;
  }

  public int getHorizontalConstant(int noRow) {
    return horizontalConstant[noRow];
  }

  public int getNoIgnoredFields(int noRow) {
    return noIgnoredFields[noRow];
  }

  public Set<Point> getDestinationCorner(String pawnColor) {
    return colorToDestinationCorner.get(pawnColor);
  }
}
