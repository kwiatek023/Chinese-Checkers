package server;

import gameVariants.GameVariant;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Game {
  private ServerSocket socket;
  private int noConnectedPlayers;
  private List<String> colors = new ArrayList<>(Arrays.asList("red", "yellow", "blue", "green", "white", "black"));
  private String gameVariant;
  private int noPlayers;
//  graczy
//  isOwner
//  Player( .accept(), true);

//  handshake
//  while


  public Game(ServerSocket socket) {
    this.socket = socket;
    try {
      start();
    } catch (IOException e) {

    }
  }

  private void start() throws IOException {
    var pool = Executors.newFixedThreadPool(6);

    Player owner = new Player(socket.accept(), colors.get(0));
    noConnectedPlayers = 1;

    owner.protocol.sendHandshake("OWNER");

    if (owner.input.hasNextLine()) {
      gameVariant = owner.input.nextLine();
    }

    if (owner.input.hasNextLine()) {
      noPlayers = Integer.parseInt(owner.input.nextLine());

      if (noPlayers < 2 || noPlayers == 5 || noPlayers > 6) {
        throw new IllegalArgumentException("Invalid number of players.");
      }
    }

    pool.execute(owner);

    while (noConnectedPlayers < noPlayers) {
      var player = new Player(socket.accept(), colors.get(noConnectedPlayers));
      player.protocol.sendHandshake("PLAYER");
      pool.execute(player);
      noConnectedPlayers++;
    }
  }

  class Player implements Runnable {
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
}
