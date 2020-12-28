package server;

import java.io.PrintWriter;

public class Protocol {
  private final PrintWriter output;

  public Protocol(PrintWriter printWriter) {
    this.output = printWriter;
    System.out.println("Server: protocol created.");
  }

  /** Sends handshake message to a client.
   * @param name name of the client.
   */
  public void sendHandshake(String name) {
    output.println(name);
    System.out.println("Handshake sent to " + name);
  }

  /** Sends "WELCOME" message to client.
   * @param color color of client's pawns
   * @param noPlayers number of players
   * @param currentColor color of player who begins the game
   */
  public void welcome(String color, int noPlayers, String currentColor) {
    output.println("WELCOME " + color + " " + noPlayers + " " + currentColor);
    System.out.println("Welcome message sent to " + color);
  }

  /** Sends "NEXT" message to a client.
   * @param color color of next player
   */
  public void nextTurn(String color) {
    output.println("NEXT " + color);
    System.out.println("Next turn message sent. Now is " + color + "'s turn.");
  }

  /**
   * Sends "END" message to a client.
   */
  public void endGame() {
    output.println("END");
    System.out.println("End message sent.");
  }

  /** Sends "OPPONENT_MOVED" to a client.
   * @param oldVerticalID old vertical ID of a pawn
   * @param oldHorizontalID old horizontal ID of a pawn
   * @param newVerticalID new vertical ID of a pawn
   * @param newHorizontalID new horizontal ID of a pawn
   */
  public void opponentMoved(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    output.println("OPPONENT_MOVED " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
    System.out.println("Opponent moved message sent: " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
  }

  /** Sends "VALID_MOVE" to a client.
   * @param oldVerticalID old vertical ID of a pawn
   * @param oldHorizontalID old horizontal ID of a pawn
   * @param newVerticalID new vertical ID of a pawn
   * @param newHorizontalID new horizontal ID of a pawn
   */
  public void validMoveMsg(int oldVerticalID, int oldHorizontalID, int newVerticalID, int newHorizontalID) {
    output.println("VALID_MOVE " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
    System.out.println("Valid move message sent: " + oldVerticalID + " " + oldHorizontalID + " " + newVerticalID + " " + newHorizontalID);
  }

  /** Sends "HAS_FINISHED" message to a client.
   * @param color player who has finished
   */
  public void hasFinished(String color) {
    output.println("HAS_FINISHED " + color);
    System.out.println("Has finished message sent. " + color + "finished.");
  }

  /** Sends "RAGE_QUIT" message to a client.
   * @param color player who has left the game.
   */
  public void rageQuit(String color) {
    output.println("RAGE_QUIT " + color);
    System.out.println("Rage quit message sent. " + color + " left the game.");
  }
}
