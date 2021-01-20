package server;

import java.io.*;
import java.net.ServerSocket;

/**
 * Class responsible for running the game.
 */
public class Server {
  private final ServerSocket server;

  public Server() throws IOException {
    server = new ServerSocket(3333);
    System.out.println("Server: Server is running.");
  }

  /**
   * Creates a new game.
   */
  public void openRoom() {
    new Game(server);
    System.out.println("Server: Room is open.");
  }

  public void watchGame(int gameId) {
    try {
      new Watcher(server, gameId);
    } catch (IOException ex) {
      System.out.println("Server: unable to watch a game.");
    }
    System.out.println("Server: Started watching.");
  }
}
