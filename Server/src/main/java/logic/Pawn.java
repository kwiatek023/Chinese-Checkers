package logic;

public class Pawn {
  private int verticalID;
  private int horizontalID;
  private final String color;

  Pawn(int verticalID, int horizontalID, String color) {
    this.verticalID = verticalID;
    this.horizontalID = horizontalID;
    this.color = color;
  }

  public int getVerticalID() {
    return verticalID;
  }

  public int getHorizontalID() {
    return horizontalID;
  }

  public void setVerticalID(int verticalID) {
    this.verticalID = verticalID;
  }

  public void setHorizontalID(int horizontalID) {
    this.horizontalID = horizontalID;
  }

  public String getColor() {
    return color;
  }
}
