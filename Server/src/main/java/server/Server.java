package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
  private ServerSocket server;
  private Socket client = null;
  private Scanner input = null;
  private PrintWriter output = null;
  private int noPlayers;
  private String gameVariant;
  private int connectedPlayers;


  public Server() throws IOException {
    server = new ServerSocket(3333);
    System.out.println("Server: Server is running.");
  }

  public void openRoom() {
    new Game(server);
    System.out.println("Server: Room is open.");
  }
}
