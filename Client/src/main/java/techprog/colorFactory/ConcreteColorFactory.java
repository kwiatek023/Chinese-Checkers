package techprog.colorFactory;

import javafx.scene.paint.Color;

public class ConcreteColorFactory implements ColorFactory {
    @Override
    public Color getColor(String color) {
        switch(color) {
            case "GREEN": return Color.GREEN;
            case "WHITE": return Color.WHITE;
            case "YELLOW": return Color.YELLOW;
            case "RED": return Color.RED;
            case "BLACK": return Color.BLACK;
            case "BLUE": return Color.BLUE;
            default: return null;
        }
    }
}
