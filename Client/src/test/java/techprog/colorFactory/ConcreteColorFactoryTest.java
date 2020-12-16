package techprog.colorFactory;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class ConcreteColorFactoryTest {

    @Test
    void getColor() {
        Color color = new ConcreteColorFactory().getColor("GREEN");
        assertSame(color, Color.GREEN);
    }
}