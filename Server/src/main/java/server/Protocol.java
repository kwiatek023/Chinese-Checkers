package server;

import java.io.PrintWriter;

public class Protocol {
  private final PrintWriter output;

  public Protocol(PrintWriter printWriter) {
    this.output = printWriter;
    System.out.println("Server: protocol created.");
  }

  public void sendHandshake(String name) {
    output.println(name);
    System.out.println("Handshake sent to " + name);
  }

  public void welcome(String color, int noPlayers, String currentColor) {
    output.println("WELCOME " + color + " " + noPlayers + " " + currentColor);
    System.out.println("Welcome message sent to " + color);
  }

  public void nextTurn(String color) {
    output.println("NEXT " + color);
    System.out.println("Next turn message sent. Now is " + color + "' turn.");
  }

  public void endGame() {
    output.println("END");
    System.out.println("End message sent.");
  }

  public void opponentMoved(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    output.println("OPPONENT_MOVED " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
    System.out.println("Opponent moved message sent: " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
  }

  public void validMoveMsg(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    output.println("VALID_MOVE " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
    System.out.println("Valid move message sent: " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
  }

  public void hasFinished(String color) {
    output.println("HAS_FINISHED " + color);
    System.out.println("Has finished message sent. " + color + "finished.");
  }

  public void rageQuit(String color) {
    output.println("RAGE_QUIT " + color);
    System.out.println("Rage quit message sent. " + color + " left the game.");
  }
}
