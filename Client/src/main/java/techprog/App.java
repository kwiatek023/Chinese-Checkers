package techprog;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import techprog.client.Client;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Client client = Client.getInstance();
        client.initConnection();

        String resource;
        if (client.isOwner()) {
            resource = "setGame";
        } else {
            resource = "waiting";
        }

        scene = new Scene(loadFXML(resource), 1100, 825);
        stage.setScene(scene);
        stage.setTitle("Chinese Checkers");
        stage.show();
        stage.setOnCloseRequest(e -> {
            client.sendMessage("RAGE_QUIT");
            client.closeConnection();
            Platform.exit();
            System.exit(0);
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}