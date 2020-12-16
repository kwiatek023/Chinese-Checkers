package techprog.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {
    private int verticalID;
    private int horizontalID;
    private Color color;

    Pawn(int verticalID, int horizontalID, Color color) {
        this.verticalID = verticalID;
        this.horizontalID = horizontalID;
        this.color = color;
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1);
    }

    public int getVerticalID() {
        return verticalID;
    }

    public int getHorizontalID() {
        return horizontalID;
    }

    public Color getColor() {
        return color;
    }

    public void setVerticalID(int verticalID) {
        this.verticalID = verticalID;
    }

    public void setHorizontalID(int horizontalID) {
        this.horizontalID = horizontalID;
    }
}
