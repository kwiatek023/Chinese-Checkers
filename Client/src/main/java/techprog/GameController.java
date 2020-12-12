package techprog;

import javafx.fxml.FXML;

public class GameController {
    private Client client;
    private String color;

    @FXML
    public void initialize() {
        client = Client.getInstance();
        client.setGameController(this);
        color = client.getColor();
        client.play();
    }
}
