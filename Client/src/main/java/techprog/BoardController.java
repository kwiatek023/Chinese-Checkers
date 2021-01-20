package techprog;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import techprog.board.Board;
import techprog.board.Field;
import techprog.board.Pawn;
import techprog.client.Client;

public abstract class BoardController {
    @FXML
    public BorderPane boardPane;

    protected Client client;
    protected int noPlayers;
    protected Board board;

    protected abstract void receiveWelcomeMessage();

    protected void drawBoard() {
        board = new Board(noPlayers);
        boardPane.setCenter(board);

        int radius = 20;
        int space = 10;

        drawFields(radius, space);
        drawPawns(radius, space);
    }

    protected abstract void drawFields(int radius, int space);

    protected abstract void drawPawns(int radius, int space);

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

    /**
     * Displays dialog window to inform about the end of the game when everybody has finished.
     */
    public void allFinished() {
        endGameAlert("All players have finished :)");
    }

    protected void endGameAlert(String content) {
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
}