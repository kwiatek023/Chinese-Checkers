package techprog.colorFactory;

import javafx.scene.paint.Color;

public interface ColorFactory {
    /** Color getter
     * @param color (String) specifies which color (Color) should be returned
     * @return instance of Color.
     */
    Color getColor(String color);
}
