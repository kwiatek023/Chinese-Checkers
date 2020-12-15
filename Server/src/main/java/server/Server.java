package server;

import java.io.*;
import java.net.ServerSocket;

public class Server {
  private ServerSocket server;

  public Server() throws IOException {
    server = new ServerSocket(3333);
    System.out.println("Server: Server is running.");
  }

  public void openRoom() {
    new Game(server);
    System.out.println("Server: Room is open.");
  }
}
