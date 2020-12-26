package logic;

import java.util.Objects;

public class Point {
  private int verticalID;
  private int horizontalID;

  public Point(int verticalID, int horizontalID) {
    this.verticalID = verticalID;
    this.horizontalID = horizontalID;
  }

  public int getVerticalID() {
    return verticalID;
  }

  public int getHorizontalID() {
    return horizontalID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return verticalID == point.verticalID && horizontalID == point.horizontalID;
  }

  @Override
  public int hashCode() {
    return Objects.hash(verticalID, horizontalID);
  }
}
