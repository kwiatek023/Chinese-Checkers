package techprog;

import javafx.fxml.FXML;
import techprog.client.Client;

import java.io.IOException;

/**
 * Displays waiting window before all players are connected.
 */
public class WaitingController {
    /**
     * Makes Client to wait for start of the game. See also {@link Client}.
     */
    @FXML
    public void initialize() {
        Client client = Client.getInstance();
        client.setWaitingController(this);

        new Thread(client::waitForStart).start();
    }

    /** Launches game window.
     * @throws IOException if unsuccessful
     */
    public void startGame() throws IOException {
        App.setRoot("game");
    }
}