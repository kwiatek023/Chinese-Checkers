package techprog;

import javafx.fxml.FXML;

public class GameController {
    private Client client;

    @FXML
    public void initialize() {
        client = Client.getInstance();
        client.setGameController(this);
        client.play();
    }
}
