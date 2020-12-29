package techprog.board;

import javafx.scene.Group;

/**
 * A template of the game board.
 * Contains information about board's properties.
 */
public abstract class AbstractBoard extends Group {
    protected int noPlayers;
    protected int noRows;
    protected int[] noFieldsInRow;
    /**
     * Number of fields in every row to omit in order to simplify move validation.
     */
    protected int[] horizontalConstant;
    /**
     * Number of fields in every row to omit in order to simplify drawing board in BoardPane.
     */
    protected int[] noIgnoredFields;
    protected Field[][] fields;
    protected Pawn[][] pawns;

    public int getNoRows() {
        return noRows;
    }

    public int getNoFieldsInRow(int noRow) {
        return noFieldsInRow[noRow];
    }

    public int getHorizontalConstant(int noRow) {
        return horizontalConstant[noRow];
    }

    public int getNoIgnoredFields(int noRow) {
        return noIgnoredFields[noRow];
    }

    public Field getField(int verticalID, int horizontalID) {
        return fields[verticalID][horizontalID];
    }

    public Pawn getPawn(int verticalID, int horizontalID) {
        return pawns[verticalID][horizontalID];
    }
}
