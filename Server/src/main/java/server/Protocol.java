package server;

import java.io.PrintWriter;

public class Protocol {
  PrintWriter output;

  public Protocol(PrintWriter printWriter) {
    this.output = printWriter;
    System.out.println("Server: protocol created.");
  }

  public void sendHandshake(String name) {
    output.println(name);
  }

  public void welcome(String color, int noPlayers, String currentColor) {
    output.println("WELCOME " + color + " " + noPlayers + " " + currentColor);
  }

  public void nextTurn(String color) {
    output.println("NEXT " + color);
  }

  public void endGame() {
    output.println("END");
  }

  public void opponentMoved(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    output.println("OPPONENT_MOVED " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
  }

  public void validMoveMsg(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    output.println("VALID_MOVE " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
  }

  public void hasFinished(String color) {
    output.println("HAS_FINISHED " + color);
  }

  public void rageQuit(String color) {
    output.println("RAGE_QUIT " + color);
  }
}
