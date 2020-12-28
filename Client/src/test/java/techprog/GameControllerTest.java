package techprog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameControllerTest {
    private static GameController instance;

    @BeforeAll
    static void setUp() {
        instance = new GameController();
    }

    @Test
    void initialize() {
        assertThrows(NullPointerException.class, () -> instance.initialize());
    }

    @Test
    void resetActivePawn() {
        assertThrows(NullPointerException.class, () -> instance.resetActivePawn());
    }

    @Test
    void redrawBoard() {
        assertThrows(NullPointerException.class, () -> instance.redrawBoard(3, 4, 4, 4));
    }

    @Test
    void updateCurrentPlayer() {
        assertThrows(NullPointerException.class, () -> instance.updateCurrentPlayer("GREEN"));
    }

    @Test
    void updateRanking() {
        assertThrows(NullPointerException.class, () -> instance.updateRanking("GREEN"));
    }

    @Test
    void endTurn() {
        assertThrows(NullPointerException.class, () -> instance.endTurn());
    }

}
