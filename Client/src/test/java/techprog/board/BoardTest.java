package techprog.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    @Test
    public void testNumberOfFields() {
        Board board = new Board(2);
        int fieldCounter = 0;
        int expectedNumberOfFields = 121;

        for (int i = 0; i < board.noRows; i++) {
            for (int j = 0; j < board.noRows; j++) {
                if (board.fields[i][j] != null) {
                    fieldCounter++;
                }
            }
        }

        assertEquals(expectedNumberOfFields, fieldCounter);
    }

    @Test
    public void testNumberOfPawns() {
        Board board = new Board(2);
        int pawnCounter = 0;
        int expectedNumberOfPawns = 20;

        for (int i = 0; i < board.noRows; i++) {
            for (int j = 0; j < board.noRows; j++) {
                if (board.pawns[i][j] != null) {
                    pawnCounter++;
                }
            }
        }

        assertEquals(expectedNumberOfPawns, pawnCounter);
    }
}