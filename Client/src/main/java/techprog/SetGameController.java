package techprog;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SetGameController {
    public TextField variantField;
    public TextField noPlayersField;
    private Client client;

    @FXML
    public void initialize() {
        client = Client.getInstance();
    }

    @FXML
    public void sendSettings() throws IOException {
        client.sendHandshake(variantField.getText(), noPlayersField.getText());
        App.setRoot("waiting");
    }
}