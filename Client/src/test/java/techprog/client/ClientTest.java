package techprog.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import techprog.GameController;
import techprog.WaitingController;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    static Client client;

    @BeforeAll
    static void setUp() {
        client = Client.getInstance();
    }

    @Test
    void isSingleton() {
        Client c1 = Client.getInstance();
        Client c2 = Client.getInstance();

        assertEquals(c1, c2);
    }

    @Test
    void isOwner() {
        assertFalse(client.isOwner());
    }

    @Test
    void setGame() {
        assertThrows(NullPointerException.class, () -> client.setGame("BASIC", "2"));
    }

    @Test
    void waitForStart() {
        client.setWaitingController(new WaitingController());
        assertThrows(NullPointerException.class, () -> client.waitForStart());
    }

    @Test
    void play() {
        client.setBoardController(new GameController());
        assertThrows(NullPointerException.class, () -> client.handleCommunication());
    }

    @Test
    void getWelcomeMessage() {
        assertNull(client.getWelcomeMessage());
    }

    @Test
    void sendMessage() {
        assertThrows(NullPointerException.class, () -> client.sendMessage("OWNER"));
    }
}