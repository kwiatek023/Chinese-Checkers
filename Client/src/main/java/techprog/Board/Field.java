package techprog.Board;

import javafx.scene.shape.Circle;

public class Field extends Circle {
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
