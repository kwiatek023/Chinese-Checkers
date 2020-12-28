package logic;

public class Field {
  private final int verticalID;
  private final int horizontalID;

  Field(int verticalID, int horizontalID) {
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
