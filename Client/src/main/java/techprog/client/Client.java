package techprog.client;

import javafx.application.Platform;
import techprog.GameController;
import techprog.WaitingController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Scanner in;
    private PrintWriter out;
    private static final Client instance = new Client();
    private boolean isOwner = false;
    private WelcomeMessage welcomeMessage;
    private GameController gameController;
    private WaitingController waitingController;

    private Client() {
        initSocket();
        receiveHandshake();
    }

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

    private void receiveHandshake() {
        var response = in.nextLine();
        isOwner = response.startsWith("OWNER");
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setGame(String variant, String noPlayers) {
        out.println(variant);
        out.println(noPlayers);
    }

    public void setWaitingController(WaitingController waitingController) {
        this.waitingController = waitingController;
    }

    public void waitForStart() throws IOException {
        if(in.hasNextLine()) {
            var split = in.nextLine().split(" ");
            welcomeMessage = new techprog.client.WelcomeMessage(split[1], Integer.parseInt(split[2]), split[3]);
        }

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
        System.out.println("Client: started playing");
        while(in.hasNextLine()) {
            var response = in.nextLine();
            System.out.println("Response from server: " + response);
            String[] commands = response.split(" ");

            switch (commands[0]) {
                case "VALID_MOVE": {
                    Platform.runLater(() -> {
                        int oldVerticalID = Integer.parseInt(commands[1]);
                        int oldHorizontalID = Integer.parseInt(commands[2]);
                        int newVerticalID = Integer.parseInt(commands[3]);
                        int newHorizontalID = Integer.parseInt(commands[4]);

                        gameController.resetActivePawn();
                        gameController.redrawBoard(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
                    });
                    break;
                }
                case "OPPONENT_MOVED": {
                    Platform.runLater(() -> {
                        int oldVerticalID = Integer.parseInt(commands[1]);
                        int oldHorizontalID = Integer.parseInt(commands[2]);
                        int newVerticalID = Integer.parseInt(commands[3]);
                        int newHorizontalID = Integer.parseInt(commands[4]);
                        gameController.redrawBoard(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
                    });
                }
                case "NEXT": {
                    Platform.runLater(() -> {
                        String nextPlayer = commands[1];
                        gameController.updateCurrentPlayer(nextPlayer);
                    });
                }
            }
        }
    }

    public WelcomeMessage getWelcomeMessage() {
        return welcomeMessage;
    }

    public void sendMessage(String message) {
        out.println(message);
        System.out.println("Client: message sent to server: " + message);
    }
}
