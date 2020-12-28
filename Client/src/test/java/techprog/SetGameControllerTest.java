package techprog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SetGameControllerTest {
    static SetGameController instance;

    @BeforeAll
    static void setUp() {
        instance = new SetGameController();
    }

    @Test
    void initialize() {
        instance.initialize();
    }

    @Test
    void sendSettings() {
        assertThrows(NullPointerException.class, instance::sendSettings);
    }
}