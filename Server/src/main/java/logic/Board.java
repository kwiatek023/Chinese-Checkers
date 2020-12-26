package logic;

import java.util.HashMap;
import java.util.HashSet;

public class Board extends AbstractBoard {
  public Board(int noPlayers) {
    if (noPlayers < 2 || noPlayers == 5 || noPlayers > 6) {
      throw new IllegalArgumentException("Invalid number of players.");
    }
    this.noPlayers = noPlayers;

    noRows = 17;
    this.noFieldsInRow = createNoFieldsInRow();
    this.horizontalConstant = createHorizontalConstant();
    this.noIgnoredFields = createNoIgnoredFields();

    createUpRightCorner();
    createUpCorner();
    createUpLeftCorner();
    createBottomLeftCorner();
    createBottomCorner();
    createBottomRightCorner();

    this.colorToDestinationCorner = new HashMap<>();
    createFields();
    createPawns(noPlayers);
  }

  private int[] createNoFieldsInRow() {
    return new int[]{1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};
  }

  private int[] createHorizontalConstant() {
    return new int[]{4, 4, 4, 4, 0, 1, 2, 3, 4, 4, 4, 4, 4, 9, 10, 11, 12};
  }

  private int[] createNoIgnoredFields() {
    return new int[]{6, 5, 5, 4, 0, 0, 1, 1, 2, 1, 1, 0, 0, 4, 5, 5, 6};
  }

  private void createFields() {
    fields = new Field[noRows][noRows];

    for (int i = 0; i < noRows; i++) {
      for (int j = 0; j < noFieldsInRow[i]; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
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
    for (int i = 9; i < 13; i++) {
      for (int j = 0; j < noPawnsInRow; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, "WHITE");
      }
      noPawnsInRow++;
    }
    colorToDestinationCorner.put("WHITE", upRightCorner);
  }

  private void createBluePawns() {
    for (int i = 9; i < 13; i++) {
      for (int j = 9; j < noFieldsInRow[i]; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, "BLUE");
      }
    }
    colorToDestinationCorner.put("BLUE", upLeftCorner);
  }

  private void createBlackPawns() {
    for (int i = 4; i < 8; i++) {
      for (int j = 9; j < noFieldsInRow[i]; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, "BLACK");
      }
    }
    colorToDestinationCorner.put("BLACK", bottomLeftCorner);
  }

  private void createYellowPawns() {
    int noPawnsInRow = 4;
    for (int i = 4; i < 8; i++) {
      for (int j = 0; j < noPawnsInRow; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, "YELLOW");
      }
      noPawnsInRow--;
    }
    colorToDestinationCorner.put("YELLOW", bottomRightCorner);
  }

  private void createRedPawns() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < noFieldsInRow[i]; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, "RED");
      }
    }
    colorToDestinationCorner.put("RED", bottomCorner);
  }

  private void createGreenPawns() {
    for (int i = 13; i < noRows; i++) {
      for (int j = 0; j < noFieldsInRow[i]; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        pawns[verticalID][horizontalID] = new Pawn(verticalID, horizontalID, "GREEN");
      }
    }

    colorToDestinationCorner.put("GREEN", upCorner);
  }

  public void updatePawns(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    pawns[newVerticalID][newHorizontalID] = pawns[oldVerticalID][oldHorizontalID];
    pawns[oldVerticalID][oldHorizontalID] = null;

    pawns[newVerticalID][newHorizontalID].setVerticalID(newVerticalID);
    pawns[newVerticalID][newHorizontalID].setHorizontalID(newHorizontalID);
  }

  private void createUpLeftCorner() {
    upLeftCorner = new HashSet<>();

    int noFieldsInRow = 4;
    for (int i = 4; i < 8; i++) {
      for (int j = 0; j < noFieldsInRow; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        upLeftCorner.add(new Point(verticalID, horizontalID));
      }
      noFieldsInRow--;
    }
  }

  private void createUpCorner() {
    upCorner = new HashSet<>();

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < noFieldsInRow[i]; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        upCorner.add(new Point(verticalID, horizontalID));
      }
    }
  }

  private void createUpRightCorner() {
    upRightCorner = new HashSet<>();

    for (int i = 4; i < 8; i++) {
      for (int j = 9; j < noFieldsInRow[i]; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        upRightCorner.add(new Point(verticalID, horizontalID));
      }
    }
  }

  private void createBottomRightCorner() {
    bottomRightCorner = new HashSet<>();

    for (int i = 9; i < 13; i++) {
      for (int j = 9; j < noFieldsInRow[i]; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        bottomRightCorner.add(new Point(verticalID, horizontalID));
      }
    }
  }

  private void createBottomCorner() {
    bottomCorner = new HashSet<>();

    for (int i = 13; i < noRows; i++) {
      for (int j = 0; j < noFieldsInRow[i]; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        bottomCorner.add(new Point(verticalID, horizontalID));
      }
    }
  }

  private void createBottomLeftCorner() {
    bottomLeftCorner = new HashSet<>();

    int noFieldsInRow = 1;
    for (int i = 9; i < 13; i++) {
      for (int j = 0; j < noFieldsInRow; j++) {
        int verticalID = i;
        int horizontalID = horizontalConstant[i] + j;
        bottomLeftCorner.add(new Point(verticalID, horizontalID));
      }
      noFieldsInRow++;
    }
  }
}