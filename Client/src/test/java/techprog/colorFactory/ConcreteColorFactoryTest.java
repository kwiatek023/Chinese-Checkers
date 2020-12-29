package techprog.colorFactory;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class ConcreteColorFactoryTest {

    @Test
    void getGreen() {
        Color color = new ConcreteColorFactory().getColor("GREEN");
        assertSame(color, Color.GREEN);
    }

    @Test
    void getWhite() {
        Color color = new ConcreteColorFactory().getColor("WHITE");
        assertSame(color, Color.WHITE);
    }

    @Test
    void getYellow() {
        Color color = new ConcreteColorFactory().getColor("YELLOW");
        assertSame(color, Color.YELLOW);
    }

    @Test
    void getRed() {
        Color color = new ConcreteColorFactory().getColor("RED");
        assertSame(color, Color.RED);
    }

    @Test
    void getBlack() {
        Color color = new ConcreteColorFactory().getColor("BLACK");
        assertSame(color, Color.BLACK);
    }

    @Test
    void getBlue() {
        Color color = new ConcreteColorFactory().getColor("BLUE");
        assertSame(color, Color.BLUE);
    }

    @Test
    void getOtherColor() {
        Color color = new ConcreteColorFactory().getColor("PINK");
        assertNull(color);
    }
}