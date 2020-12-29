package techprog;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import techprog.client.Client;

import java.io.IOException;

public class SetGameController {
    @FXML
    public ComboBox<String> variantBox;

    @FXML
    public ComboBox<String> playersBox;

    private Client client;

    @FXML
    public void initialize() {
        client = Client.getInstance();
    }

    @FXML
    public void sendSettings() throws IOException {
        String variantGame = variantBox.getSelectionModel().getSelectedItem();
        String noPlayers = playersBox.getSelectionModel().getSelectedItem();

        if (!variantGame.equals(variantBox.getPromptText()) && !noPlayers.equals(playersBox.getPromptText())) {
            client.setGame(variantGame, noPlayers);
            App.setRoot("waiting");
        }
    }
}