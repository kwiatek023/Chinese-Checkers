package logic;

public class Field {
  private int verticalID;
  private int horizontalID;

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
