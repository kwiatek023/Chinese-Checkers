package techprog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
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
public class GameController extends BoardController {
    @FXML
    public Label colorLabel;

    @FXML
    public Label turnLabel;

    @FXML
    public Label rankingLabel;

    private Color color;
    private String currentPlayer;
    private Pawn activePawn = null;
    private List<String> ranking;

    @Override
    public void initialize() {
        client = Client.getInstance();
        client.setBoardController(this);

        receiveWelcomeMessage();
        drawBoard();
        createRanking();

        new Thread(() -> client.handleCommunication()).start();
    }

    @Override
    protected void receiveWelcomeMessage() {
        var welcomeMessage = client.getWelcomeMessage();

        String colorName = welcomeMessage.getColor();
        colorLabel.setText("You are " + colorName);
        color = new ConcreteColorFactory().getColor(colorName);

        noPlayers = welcomeMessage.getNoPlayers();

        currentPlayer = welcomeMessage.getFirstPlayer();
        turnLabel.setText("Now is " + currentPlayer + "'s turn");
    }

    @Override
    protected void drawFields(int radius, int space) {
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

    @Override
    protected void drawPawns(int radius, int space) {
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
     * Displays dialog window to inform players about the end of the game when everybody has left.
     */
    public void rageQuit(String player) {
        endGameAlert("Rage quit :( " + player + " has gone");
    }

    /**
     * Sends "END_TURN" message to server when player clicked end turn button.
     */
    public void endTurn() {
        client.sendMessage("END_TURN");
    }
}

