package techprog;

import javafx.application.Platform;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Scanner in;
    private PrintWriter out;
    private static Client instance = new Client();
    private boolean isFirst = false;
    private GameController gameController;
    private WaitingController waitingController;

    private Client() {
        initSocket();
        checkOrder();
    };

    public static Client getInstance() {
        return instance;
    }

    private void initSocket() {
        try {
            Socket socket = new Socket("localhost", 3333);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
    }

    private void checkOrder() {
        var response = in.nextLine();
        isFirst = response.startsWith("FIRST");
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void sendHandshake(String variant, String noPlayers) {
        out.println(variant);
        out.println(noPlayers);
    }

    public void setWaitingController(WaitingController waitingController) {
        this.waitingController = waitingController;
    }

    public void waitForStart() throws IOException {
        var response = in.nextLine();
        Platform.runLater(() -> {
            try {
                waitingController.startGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void play() {
        System.out.println("PLAY");
    }
}
