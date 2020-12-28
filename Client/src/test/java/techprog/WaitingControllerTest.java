package techprog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaitingControllerTest {
    @Test
    void startGame() {
        WaitingController waitingController = new WaitingController();
        assertThrows(ExceptionInInitializerError.class, waitingController::startGame);
    }
}