import server.Server;
import java.io.IOException;

/**
 * Main class that launches the server.
 */
public class Main {
  public static void main(String[] args) {
    try {
      Server server = new Server();
      server.openRoom();
    } catch (IOException e) {
      System.out.println("Unable to run the server.");
    }
  }
}