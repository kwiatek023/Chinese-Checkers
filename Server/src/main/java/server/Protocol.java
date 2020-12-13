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
    output.println("NEXT" + color);
  }

  public void invalidMove() {
    output.println("INVALID MOVE");
  }

  public void endGame(String color) {
    output.println("END " + color);
  }

}
