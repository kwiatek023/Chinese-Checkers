package techprog;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import techprog.board.Board;
import techprog.board.Field;
import techprog.board.Pawn;
import techprog.client.Client;
import techprog.colorFactory.ConcreteColorFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * Responsible for controlling flow of the game.
 * Creates a board on BoardPane.
 * Receives information about user's action and pass it to {@link Client}.
 * Displays information about color of client, color of current player and ranking.
 */
public class GameController {
    @FXML
    public BorderPane boardPane;

    @FXML
    public Label colorLabel;

    @FXML
    public Label turnLabel;

    @FXML
    public Label rankingLabel;

    private Client client;
    private Color color;
    private int noPlayers;
    private String currentPlayer;
    private Board board;
    private Pawn activePawn = null;
    private List<String> ranking;

    /**
     * Informs the client who is its controller.
     * Handles welcome message (see also {@link techprog.client.WelcomeMessage}).
     * Draws a board and creates ranking.
     * Creates new thread for client to handle its communication with server during the game.
     */
    @FXML
    public void initialize() {
        client = Client.getInstance();
        client.setGameController(this);

        receiveWelcomeMessage();
        drawBoard();
        createRanking();

        new Thread(() -> client.play()).start();
    }

    private void receiveWelcomeMessage() {
        var welcomeMessage = client.getWelcomeMessage();

        String colorName = welcomeMessage.getColor();
        colorLabel.setText("You are " + colorName);
        color = new ConcreteColorFactory().getColor(colorName);

        noPlayers = welcomeMessage.getNoPlayers();

        currentPlayer = welcomeMessage.getFirstPlayer();
        turnLabel.setText("Now is " + currentPlayer + "'s turn");
    }

    private void drawBoard() {
        board = new Board(noPlayers);
        boardPane.setCenter(board);

        int radius = 20;
        int space = 10;

        drawFields(radius, space);
        drawPawns(radius, space);
    }

    private void drawFields(int radius, int space) {
        for (int i = 0; i < board.getNoRows(); i++) {
            double y = (i * (2 * radius + space)) * sqrt(3) / 2 + (radius + space);

            for (int j = 0; j < board.getNoFieldsInRow(i); j++) {
                Field field = board.getField(i, board.getHorizontalConstant(i) + j);
                int x = (board.getNoIgnoredFields(i) + j) * (2 * radius + space);

                if (i % 2 == 1) {
                    x += (2 * radius + space) / 2;
                }

                field.setRadius(radius);
                field.setCenterX(x);
                field.setCenterY(y);
                field.setFill(Color.GRAY);

                field.setOnMouseClicked(event -> {
                    if (activePawn != null) {
                        client.sendMessage("MOVE " + activePawn.getVerticalID() + " " + activePawn.getHorizontalID() + " "
                                + field.getVerticalID() + " " + field.getHorizontalID());
                    }
                });

                board.getChildren().addAll(field);
            }
        }
    }

    private void drawPawns(int radius, int space) {
        for (int i = 0; i < board.getNoRows(); i++) {
            double y = (i * (2 * radius + space)) * sqrt(3) / 2 + (radius + space);

            for (int j = 0; j < board.getNoFieldsInRow(i); j++) {
                Pawn pawn = board.getPawn(i, board.getHorizontalConstant(i) + j);
                if (pawn != null) {
                    int x = (board.getNoIgnoredFields(i) + j) * (2 * radius + space);

                    if (i % 2 == 1) {
                        x += (2 * radius + space) / 2;
                    }

                    pawn.setRadius(radius);
                    pawn.setCenterX(x);
                    pawn.setCenterY(y);
                    pawn.setFill(pawn.getColor());

                    pawn.setOnMouseClicked(event -> {
                        if (pawn.getColor().equals(this.color)) {
                            if (activePawn != null) {
                                resetActivePawn();
                            }

                            activePawn = pawn;
                            activePawn.setStroke(Color.DARKGREY);
                            activePawn.setStrokeWidth(5);
                            System.out.println("Active pawn IDS: " + activePawn.getVerticalID() + " " + activePawn.getHorizontalID());
                        }
                    });

                    board.getChildren().addAll(pawn);
                }
            }
        }
    }

    private void createRanking() {
        ranking = new ArrayList<>();
        rankingLabel.setText("Ranking");
        rankingLabel.setVisible(false);
    }

    /**
     * Makes a pawn inactive - sets its layout to default.
     */
    public void resetActivePawn() {
        this.activePawn.setStroke(Color.BLACK);
        this.activePawn.setStrokeWidth(1);
        this.activePawn = null;
    }

    /** Updates a board on the BoardPane.
     * Changes pawns' location.
     * @param oldVerticalID   old vertical ID of a pawn
     * @param oldHorizontalID old horizontal ID of a pawn
     * @param newVerticalID   new vertical ID of a pawn
     * @param newHorizontalID new horizontal ID of a pawn
     */
    public void redrawBoard(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
        board.updatePawns(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
        Field field = board.getField(newVerticalID, newHorizontalID);
        Pawn movedPawn = board.getPawn(newVerticalID, newHorizontalID);

        movedPawn.setCenterX(field.getCenterX());
        movedPawn.setCenterY(field.getCenterY());
    }

    /** Updates label with turn information.
     * @param nextPlayer player who will perform next move.
     */
    public void updateCurrentPlayer(String nextPlayer) {
        currentPlayer = nextPlayer;
        turnLabel.setText("Now is " + currentPlayer + "'s turn");
    }

    /** Adds player to ranking.
     * @param player player to be added to ranking
     */
    public void updateRanking(String player) {
        ranking.add(player);
        rankingLabel.setText(rankingLabel.getText() + "\n" + ranking.size() + ". " + player);
        rankingLabel.setVisible(true);
    }

    /**
     * Displays dialog window to inform players about the end of the game when everybody has finished.
     */
    public void allFinished() {
        endGameAlert("All players have finished. Congratulation!");
    }

    /**
     * Displays dialog window to inform players about the end of the game when everybody has left.
     */
    public void rageQuit(String player) {
        endGameAlert("Rage quit :( " + player + " has gone");
    }

    private void endGameAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The end of the game");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.setOnCloseRequest(e -> client.closeConnection());

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            client.closeConnection();
        }
    }

    /**
     * Sends "END_TURN" message to server when player clicked end turn button.
     */
    public void endTurn() {
        client.sendMessage("END_TURN");
    }
}

