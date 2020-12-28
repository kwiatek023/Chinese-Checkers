package techprog.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WelcomeMessageTest {
    @Test
    void testMessage() {
        WelcomeMessage welcomeMessage = new WelcomeMessage("GREEN", 2, "YELLOW");
        assertEquals("GREEN", welcomeMessage.getColor());
        assertEquals(2, welcomeMessage.getNoPlayers());
        assertEquals("YELLOW", welcomeMessage.getFirstPlayer());
    }
}