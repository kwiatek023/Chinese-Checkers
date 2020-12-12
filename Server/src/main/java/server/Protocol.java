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

  public void welcome() {
    output.println("START");
  }
}
