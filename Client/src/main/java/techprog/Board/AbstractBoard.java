package techprog.Board;

import javafx.scene.Group;

public class AbstractBoard extends Group {
    int noPlayers;
    int noRows;
    int[] noFieldsInRow;
    int[] horizontalConstant;
    int[] noIgnoredFields;
    Field[][] fields;

    public int getNoPlayers() {
        return noPlayers;
    }

    public int getNoRows() {
        return noRows;
    }

    public int getNoFieldsInRow(int noRow) {
        return noFieldsInRow[noRow];
    }

    public Field getField(int verticalID, int horizontalID) {
        return fields[verticalID][horizontalID];
    }

    public void setNoRows(int noRows) {
        this.noRows = noRows;
    }

    public void setNoFieldsInRow(int[] noFieldsInRow) {
        this.noFieldsInRow = noFieldsInRow;
    }

    public int getHorizontalConstant(int noRow) {
        return horizontalConstant[noRow];
    }

    public int getNoIgnoredFields(int noRow) {
        return noIgnoredFields[noRow];
    }
}
