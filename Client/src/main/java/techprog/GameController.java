package techprog;

import javafx.event.ActionEvent;
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

    @FXML
    public void initialize() {
        client = Client.getInstance();
        client.setGameController(this);

        receiveWelcomeMessage();
        drawBoard();
        createRanking();

        new Thread(() -> {
            client.play();
        }).start();
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
        drawFields();
        drawPawns();
    }

    private void drawFields() {
        int radius = 20;
        int space = 10;

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

    private void drawPawns() {
        int radius = 20;
        int space = 10;

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
                            if(activePawn != null) {
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

    public void resetActivePawn() {
        this.activePawn.setStroke(Color.BLACK);
        this.activePawn.setStrokeWidth(1);
        this.activePawn = null;
    }

    public void redrawBoard(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
        board.updatePawns(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
        Field field = board.getField(newVerticalID, newHorizontalID);
        Pawn movedPawn = board.getPawn(newVerticalID, newHorizontalID);

        movedPawn.setCenterX(field.getCenterX());
        movedPawn.setCenterY(field.getCenterY());
    }

    public void updateCurrentPlayer(String nextPlayer) {
        currentPlayer = nextPlayer;
        turnLabel.setText("Now is " + currentPlayer + "'s turn");
    }

    public void updateRanking(String player) {
        ranking.add(player);
        rankingLabel.setText(rankingLabel.getText() + "\n" + ranking.size() + ". " + player);
        rankingLabel.setVisible(true);
    }

    public void endGame() {
        endGameAlert("All players have finished. Congratulation!");
    }

    public void rageQuit(String player) {
        endGameAlert("Rage quit :( " + player + " has gone");
    }

    private void endGameAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The end of the game");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.setOnCloseRequest(e -> {
            client.closeConnection();
        });

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            client.closeConnection();
        }
    }

    public void endTurn(ActionEvent actionEvent) {
        client.sendMessage("END_TURN");
    }
}

