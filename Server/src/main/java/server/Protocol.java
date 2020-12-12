package server;

import gameVariants.GameVariant;
import java.io.PrintWriter;

public class Protocol {
  PrintWriter output;

  public Protocol(PrintWriter printWriter) {
    this.output = printWriter;
  }
}
