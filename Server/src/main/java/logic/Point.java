package logic;

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
}
