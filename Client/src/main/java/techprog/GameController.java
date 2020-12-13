package techprog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import techprog.Board.Board;
import techprog.Board.Field;

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

    }

    private void drawPawns() {
    }
}
