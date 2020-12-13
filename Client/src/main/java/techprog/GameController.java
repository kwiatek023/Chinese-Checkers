package techprog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import techprog.Board.Board;
import techprog.Board.Field;

import static java.lang.Math.sqrt;

public class GameController {
    public BorderPane boardPane;
    public Label colorLabel;
    public Label turnLabel;
    private Client client;
    private String color;
    private int noPlayers;
    private String currentPlayer;
    private Board board;
    private Field[][] fields;

    @FXML
    public void initialize() {
        client = Client.getInstance();
        client.setGameController(this);

        var welcomeMessage = client.getWelcomeMessage();
        color = welcomeMessage.getColor();
        noPlayers = welcomeMessage.getNoPlayers();
        currentPlayer = welcomeMessage.getFirstPlayer();

        colorLabel.setText("You are " + color);
        turnLabel.setText("Now is " + currentPlayer + "'s turn");

        drawBoard();
        client.play();
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

        for(int i = 0; i < board.getNoRows(); i++) {
            double y = (i * (2 * radius + space)) * sqrt(3) / 2 + (radius + space);

            for(int j = 0; j < board.getNoFieldsInRow(i); j++) {
                Field field = board.getField(i, board.getHorizontalConstant(i) + j);
                int x = (board.getNoIgnoredFields(i) + j) * (2 * radius + space);

                if(i % 2 == 1) {
                    x += (2 * radius + space) / 2;
                }

                field.setRadius(radius);
                field.setCenterX(x);
                field.setCenterY(y);
                field.setFill(Color.GRAY);
                board.getChildren().addAll(field);
            }
        }
    }

    private void drawPawns() {
    }
}
