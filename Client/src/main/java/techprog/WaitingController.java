package techprog;

import javafx.fxml.FXML;
import techprog.client.Client;

import java.io.IOException;

public class WaitingController {
    @FXML
    public void initialize() {
        Client client = Client.getInstance();
        client.setWaitingController(this);

        new Thread(() -> {
            try {
                client.waitForStart();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void startGame() throws IOException {
        App.setRoot("game");
    }
}