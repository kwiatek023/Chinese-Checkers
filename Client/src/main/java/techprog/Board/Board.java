package techprog.Board;

public class Board extends AbstractBoard {
    public Board(int noPlayers) {
        if (noPlayers < 2 || noPlayers == 5 || noPlayers > 6) {
            throw new IllegalArgumentException("Invalid number of players.");
        }
        this.noPlayers = noPlayers;

        noRows = 17;
        noFieldsInRow = new int[] {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};
        noIgnoredFields = new int[] {4, 4, 4, 4, 0, 1, 2, 3, 4, 4, 4, 4, 4, 9, 10, 11, 12};
        createFields();
    }

    private void createFields() {
        fields = new Field[noRows][noRows];

        for(int i = 0; i < noRows; i++) {
            for(int j = 0; j < noFieldsInRow[i]; j++) {
                int verticalID = i;
                int horizontalID = noIgnoredFields[i]+j;
                fields[verticalID][horizontalID] = new Field(verticalID, horizontalID);
            }
        }
    }
}
