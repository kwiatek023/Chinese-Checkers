package server;

import java.io.PrintWriter;

public class Protocol {
  PrintWriter output;

  public Protocol(PrintWriter printWriter) {
    this.output = printWriter;
  }

  public void sendHandshake(String name) {
    output.println(name);
  }

  public void welcome(String color) {
    output.println("WELCOME " + color);

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
