package techprog.board;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void testInheritedMethods() {
        Board board = new Board(2);

        assertEquals(board.noRows, board.getNoRows());
        assertEquals(board.noFieldsInRow[0], board.getNoFieldsInRow(0));
        assertEquals(board.horizontalConstant[0], board.getHorizontalConstant(0));
        assertEquals(board.noIgnoredFields[0], board.getNoIgnoredFields(0));
        assertEquals(board.fields[0][4], board.getField(0, 4));
        assertEquals(board.pawns[0][4], board.getPawn(0, 4));
    }

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
    public void testNumberOfPawnsForTwoPlayers() {
        Board board = new Board(2);
        Set<Color> colorSet = new HashSet<>();

        int pawnCounter = 0;
        int expectedNumberOfPawns = 20;

        for (int i = 0; i < board.noRows; i++) {
            for (int j = 0; j < board.noRows; j++) {
                if (board.pawns[i][j] != null) {
                    pawnCounter++;
                    colorSet.add(board.pawns[i][j].getColor());
                }
            }
        }

        assertEquals(expectedNumberOfPawns, pawnCounter);
        assertEquals(2, colorSet.size());
    }

    @Test
    public void testNumberOfPawnsForThreePlayers() {
        Board board = new Board(3);
        Set<Color> colorSet = new HashSet<>();

        int pawnCounter = 0;
        int expectedNumberOfPawns = 30;

        for (int i = 0; i < board.noRows; i++) {
            for (int j = 0; j < board.noRows; j++) {
                if (board.pawns[i][j] != null) {
                    pawnCounter++;
                    colorSet.add(board.pawns[i][j].getColor());
                }
            }
        }

        assertEquals(expectedNumberOfPawns, pawnCounter);
        assertEquals(3, colorSet.size());
    }

    @Test
    public void testNumberOfPawnsForFourPlayers() {
        Board board = new Board(4);
        Set<Color> colorSet = new HashSet<>();

        int pawnCounter = 0;
        int expectedNumberOfPawns = 40;

        for (int i = 0; i < board.noRows; i++) {
            for (int j = 0; j < board.noRows; j++) {
                if (board.pawns[i][j] != null) {
                    pawnCounter++;
                    colorSet.add(board.pawns[i][j].getColor());
                }
            }
        }
        assertEquals(expectedNumberOfPawns, pawnCounter);
        assertEquals(4, colorSet.size());
    }

    @Test
    public void testNumberOfPawnsForSixPlayers() {
        Board board = new Board(6);
        Set<Color> colorSet = new HashSet<>();

        int pawnCounter = 0;
        int expectedNumberOfPawns = 60;

        for (int i = 0; i < board.noRows; i++) {
            for (int j = 0; j < board.noRows; j++) {
                if (board.pawns[i][j] != null) {
                    pawnCounter++;
                    colorSet.add(board.pawns[i][j].getColor());
                }
            }
        }
        assertEquals(expectedNumberOfPawns, pawnCounter);
        assertEquals(6, colorSet.size());
    }

    @Test
    public void testIDsFieldsAndPawns() {
        Board board = new Board(2);

        for (int i = 0; i < board.noRows; i++) {
            for (int j = 0; j < board.noRows; j++) {
                if (board.fields[i][j] != null && board.pawns[i][j] != null) {
                    Field field = board.fields[i][j];
                    Pawn pawn = board.pawns[i][j];

                    assertEquals(field.getVerticalID(), pawn.getVerticalID());
                    assertEquals(field.getHorizontalID(), pawn.getHorizontalID());
                }
            }
        }
    }

    @Test
    public void testInappropriateNoPlayers() {
        assertThrows(IllegalArgumentException.class, () -> new Board(5));
    }

    @Test
    public void testUpdatePawns() {
        Board board = new Board(2);

        int oldVerticalID = 3;
        int oldHorizontalID = 4;

        int newVerticalID = 4;
        int newHorizontalID = 4;

        assertNotNull(board.pawns[oldVerticalID][oldHorizontalID]);
        assertNull(board.pawns[newVerticalID][newHorizontalID]);

        board.updatePawns(3, 4, 4, 4);

        assertNull(board.pawns[oldVerticalID][oldHorizontalID]);
        assertNotNull(board.pawns[newVerticalID][newHorizontalID]);

        assertEquals(board.pawns[newVerticalID][newHorizontalID].getVerticalID(), 4);
        assertEquals(board.pawns[newVerticalID][newHorizontalID].getHorizontalID(), 4);
    }
}