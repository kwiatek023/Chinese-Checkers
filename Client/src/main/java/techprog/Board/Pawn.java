package techprog.Board;

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

}
