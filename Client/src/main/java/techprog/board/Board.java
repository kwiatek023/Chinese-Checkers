package techprog.board;

import javafx.scene.paint.Color;

public class Board extends AbstractBoard {
    public Board(int noPlayers) {
        if (noPlayers < 2 || noPlayers == 5 || noPlayers > 6) {
            throw new IllegalArgumentException("Invalid number of players.");
        }
        this.noPlayers = noPlayers;

        this.noRows = 17;
        this.noFieldsInRow = createNoFieldsInRow();
        this.horizontalConstant = createHorizontalConstant();
        this.noIgnoredFields = createNoIgnoredFields();

        createFields();
        createPawns(noPlayers);
    }

    private int[] createNoFieldsInRow() {
        return new int[] {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};
    }

    private int[] createHorizontalConstant() {
        return new int[] {4, 4, 4, 4, 0, 1, 2, 3, 4, 4, 4, 4, 4, 9, 10, 11, 12};
    }

    private int[] createNoIgnoredFields() {
        return new int[] {6, 5, 5, 4, 0, 0, 1, 1, 2, 1, 1, 0, 0, 4, 5, 5, 6};
    }

    private void createFields() {
        fields = new Field[noRows][noRows];

        for(int i = 0; i < noRows; i++) {
            for(int j = 0; j < noFieldsInRow[i]; j++) {
                int verticalID = i;
                int horizontalID = horizontalConstant[i]+j;
                fields[verticalID][horizontalID] = new Field(verticalID, horizontalID);
            }
        }
    }

    private void createPawns(int noPlayers) {
        pawns = new Pawn[noRows][noRows];

        switch (noPlayers) {
            case 2: {
                createGreenPawns();
                createRedPawns();
                break;
            }
            case 3: {
                createGreenPawns();
                createYellowPawns();
                createBlackPawns();
                break;
            }
            case 4: {
                createGreenPawns();
                createYellowPawns();
                createRedPawns();
                createBluePawns();
                break;
            }
            case 6: {
                createGreenPawns();
                createWhitePawns();
                createYellowPawns();
                createRedPawns();
                createBlackPawns();
                createBluePawns();
                break;
            }
        }
    }

    private void createWhitePawns() {
        int noPawnsInRow = 1;
        for(int i = 9; i < 13; i++) {
            for(int j = 0; j < noPawnsInRow; j++) {
                int verticalID = i;
                int horizontalID = horizontalConstant[i]+j;
                pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, Color.WHITE);
            }
            noPawnsInRow++;
        }
    }

    private void createBluePawns() {
        for(int i = 9; i < 13; i++) {
            for(int j = 9; j < noFieldsInRow[i]; j++) {
                int verticalID = i;
                int horizontalID = horizontalConstant[i]+j;
                pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, Color.BLUE);
            }
        }
    }

    private void createBlackPawns() {
        for(int i = 4; i < 8; i++) {
            for(int j = 9; j < noFieldsInRow[i]; j++) {
                int verticalID = i;
                int horizontalID = horizontalConstant[i]+j;
                pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, Color.BLACK);
            }
        }
    }

    private void createYellowPawns() {
        int noPawnsInRow = 4;
        for(int i = 4; i < 8; i++) {
            for(int j = 0; j < noPawnsInRow; j++) {
                int verticalID = i;
                int horizontalID = horizontalConstant[i]+j;
                pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, Color.YELLOW);
            }
            noPawnsInRow--;
        }
    }

    private void createRedPawns() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < noFieldsInRow[i]; j++) {
                int verticalID = i;
                int horizontalID = horizontalConstant[i]+j;
                pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, Color.RED);
            }
        }
    }


    private void createGreenPawns() {
        for(int i = 13; i < noRows; i++) {
            for(int j = 0; j < noFieldsInRow[i]; j++) {
                int verticalID = i;
                int horizontalID = horizontalConstant[i]+j;
                pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, Color.GREEN);
            }
        }
    }

    public void updatePawns(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
        pawns[newVerticalID][newHorizontalID] = pawns[oldVerticalID][oldHorizontalID];
        pawns[oldVerticalID][oldHorizontalID] = null;

        pawns[newVerticalID][newHorizontalID].setVerticalID(newVerticalID);
        pawns[newVerticalID][newHorizontalID].setHorizontalID(newHorizontalID);

    }
}
