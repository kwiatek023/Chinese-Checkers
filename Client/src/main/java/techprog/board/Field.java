package techprog.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Field extends Circle {
    private final int verticalID;
    private final int horizontalID;

    Field(int verticalID, int horizontalID) {
        this.verticalID = verticalID;
        this.horizontalID = horizontalID;

        setOnMousePressed(event -> {
            this.setStroke(Color.DARKGREY);
            this.setStrokeWidth(5);
        });
        setOnMouseReleased(event -> this.setStrokeWidth(0));
    }

    public int getVerticalID() {
        return verticalID;
    }

    public int getHorizontalID() {
        return horizontalID;
    }
}
