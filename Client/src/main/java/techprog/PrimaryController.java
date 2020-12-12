package techprog;

import javafx.fxml.FXML;

import java.io.IOException;

public class PrimaryController {
    private Client client;

    @FXML
    public void initialize() {
        client = Client.getInstance();
    }

    @FXML
    public void switchDependsOnPrivileges() throws IOException {
        if(client.isOwner()) {
            App.setRoot("setGame");
        } else {
            App.setRoot("waiting");
        }
    }
}
