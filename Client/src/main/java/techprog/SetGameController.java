package techprog;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import techprog.client.Client;

import java.io.IOException;

public class SetGameController {
    @FXML
    public ComboBox<String> variantGame;

    @FXML
    public ComboBox<String> noPlayers;

    private Client client;

    @FXML
    public void initialize() {
        client = Client.getInstance();
    }

    @FXML
    public void sendSettings() throws IOException {
        client.setGame(variantGame.getSelectionModel().getSelectedItem(), noPlayers.getSelectionModel().getSelectedItem());
        App.setRoot("waiting");
    }
}