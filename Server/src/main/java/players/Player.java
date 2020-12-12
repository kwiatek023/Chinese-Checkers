package players;

import server.Protocol;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Runnable{
  private Scanner input = null;
  private PrintWriter output = null;
  private Socket socket;
  private String color;
  Protocol protocol;

  public Player(Socket socket, String color) {
    this.socket = socket;
    this.color = color;
    Protocol protocol;

    try {
      input = new Scanner(socket.getInputStream());
      output = new PrintWriter(socket.getOutputStream(), true);
      protocol = new Protocol(output);
    } catch (IOException e) {
      System.out.println("Game: error occurred while creating new player.");
    }

  }

  @Override
  public void run() {

  }
}
