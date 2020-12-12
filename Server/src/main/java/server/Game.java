package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Game {
  private ServerSocket socket;
  private int noConnectedPlayers;
//  graczy
//  isOwner
//  Player( .accept(), true);

//  handshake
//  while


  public Game(ServerSocket socket) {
    this.socket = socket;
  }

  private void start() {

  }

  class Player {
    private Scanner input = null;
    private PrintWriter output = null;
    private boolean isOwner;
    Socket socket;
    String color;
    Protocol protocol;

    public Player(boolean isOwner, Socket socket, String color) {
      this.isOwner = isOwner;
      this.socket = socket;
      this.color = color;
      try {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);

      } catch (IOException e) {
        System.out.println("Game: error occurred while creating new player.");
      }
    }
  }
}
