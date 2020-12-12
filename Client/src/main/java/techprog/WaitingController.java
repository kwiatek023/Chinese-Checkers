package techprog;

import javafx.fxml.FXML;

import java.io.IOException;

public class WaitingController {
    private Client client;

    @FXML
    public void initialize() throws IOException {
        client = Client.getInstance();
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