package techprog.board;

import javafx.scene.Group;

public class AbstractBoard extends Group {
    int noPlayers;
    int noRows;
    int[] noFieldsInRow;
    int[] horizontalConstant;
    int[] noIgnoredFields;
    Field[][] fields;
    Pawn[][] pawns;

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
