package techprog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import techprog.Board.Board;
import techprog.Board.Field;
import techprog.Board.Pawn;

import static java.lang.Math.sqrt;

public class GameController {
    public BorderPane boardPane;
    public Label colorLabel;
    public Label turnLabel;
    private Client client;
    private Color color;
    private int noPlayers;
    private String currentPlayer;
    private Board board;
    private Pawn activePawn = null;

    @FXML
    public void initialize() {
        client = Client.getInstance();
        client.setGameController(this);

        var welcomeMessage = client.getWelcomeMessage();
        String colorName = welcomeMessage.getColor();
        noPlayers = welcomeMessage.getNoPlayers();
        currentPlayer = welcomeMessage.getFirstPlayer();

        colorLabel.setText("You are " + colorName);
        turnLabel.setText("Now is " + currentPlayer + "'s turn");

        color = new ConcreteColorFactory().getColor(colorName);

        drawBoard();

        new Thread(() -> {
            client.play();
        }).start();
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
                            activePawn = pawn;
                        }
                    });

                    board.getChildren().addAll(pawn);
                }
            }
        }
    }


    public void resetActivePawn() {
        this.activePawn = null;
    }

    public void redrawBoard(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
        board.updatePawns(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
        Field field = board.getField(newVerticalID, newHorizontalID);
        Pawn movedPawn = board.getPawn(newVerticalID, newHorizontalID);

        movedPawn.setCenterX(field.getCenterX());
        movedPawn.setCenterY(field.getCenterY());

    }
}
