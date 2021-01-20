package techprog.client;

import javafx.application.Platform;
import techprog.GameController;
import techprog.WaitingController;
import techprog.WatchGameController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Responsible for communication with server. It is a singleton.
 */
public class Client {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private static final Client instance = new Client();
    private boolean isOwner = false;
    private boolean isWatcher = false;
    private WelcomeMessage welcomeMessage;
    private GameController gameController;
    private WaitingController waitingController;
    private WatchGameController watchGameController;

    private Client() {}

    public static Client getInstance() {
        return instance;
    }

    /**
     * Initializes connection to server.
     * Receives handshake from server - information if this client is the owner of the game.
     */
    public void initConnection() {
        initSocket();
        receiveHandshake();
    }

    private void initSocket() {
        try {
            socket = new Socket("localhost", 3333);
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
        var split = in.nextLine().split(" ");
        String role = split[0];

        isOwner = role.startsWith("OWNER");
        isWatcher = role.startsWith("WATCHER");

        if(isWatcher) {
            int numberOfPlayers = Integer.parseInt(split[1]);
            welcomeMessage = new WelcomeMessage(null, numberOfPlayers, null);
        }
    }

    public boolean isOwner() {
        return isOwner;
    }

    public boolean isWatcher() {
        return isWatcher;
    }

    /** Sends information to server about user game settings.
     * @param variant variant of the game
     * @param noPlayers number of players
     */
    public void setGame(String variant, String noPlayers) {
        out.println(variant);
        out.println(noPlayers);
    }

    public void setWaitingController(WaitingController waitingController) {
        this.waitingController = waitingController;
    }


    /**
     * Waits for welcome message from server and then notifies {@link WaitingController}.
     */
    public void waitForStart() {
        if (in.hasNextLine()) {
            var split = in.nextLine().split(" ");
            String color = split[1];
            int noPlayers = Integer.parseInt(split[2]);
            String firstPlayer = split[3];
            welcomeMessage = new WelcomeMessage(color, noPlayers, firstPlayer);
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

    /**
     * Defines the information flow during the game.
     * Depending on the received information, makes game controller to perform appropriate actions.
     */
    public void play() {
        System.out.println("Client: started playing");

        while (in.hasNextLine()) {
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
                case "PAWN_MOVED": {
                    Platform.runLater(() -> {
                        int oldVerticalID = Integer.parseInt(commands[1]);
                        int oldHorizontalID = Integer.parseInt(commands[2]);
                        int newVerticalID = Integer.parseInt(commands[3]);
                        int newHorizontalID = Integer.parseInt(commands[4]);
                        gameController.redrawBoard(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
                    });
                    break;
                }
                case "NEXT": {
                    Platform.runLater(() -> {
                        String nextPlayer = commands[1];
                        gameController.updateCurrentPlayer(nextPlayer);
                    });
                    break;
                }
                case "HAS_FINISHED": {
                    Platform.runLater(() -> {
                        String player = commands[1];
                        gameController.updateRanking(player);
                    });
                    break;
                }
                case "END": {
                    Platform.runLater(() -> gameController.allFinished());
                    break;
                }
                case "RAGE_QUIT": {
                    Platform.runLater(() -> {
                        String player = commands[1];
                        gameController.rageQuit(player);
                    });
                    break;
                }
            }
        }
    }

    public WelcomeMessage getWelcomeMessage() {
        return welcomeMessage;
    }

    /**
     * Sends messages to server.
     * @param message message to send.
     */
    public void sendMessage(String message) {
        out.println(message);
        System.out.println("Client: message sent to server: " + message);
    }

    /**
     * Sends message about quit to server and closes connection.
     */
    public void closeConnection() {
        System.out.println("Client is closing connection");
        sendMessage("QUIT");

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Platform.exit();
        System.exit(0);
    }

    public void setWatchController(WatchGameController watchGameController) {
        this.watchGameController = watchGameController;
    }

    public void watch() {
        System.out.println("Client: started watching game");

        while (in.hasNextLine()) {
            var response = in.nextLine();
            System.out.println("Response from server: " + response);
            String[] commands = response.split(" ");

            switch (commands[0]) {
                case "PAWN_MOVED": {
                    Platform.runLater(() -> {
                        int oldVerticalID = Integer.parseInt(commands[1]);
                        int oldHorizontalID = Integer.parseInt(commands[2]);
                        int newVerticalID = Integer.parseInt(commands[3]);
                        int newHorizontalID = Integer.parseInt(commands[4]);
                        watchGameController.redrawBoard(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
                    });
                    break;
                }
                case "END": {
                    Platform.runLater(() -> watchGameController.allFinished());
                    break;
                }
            }
        }
    }
}
