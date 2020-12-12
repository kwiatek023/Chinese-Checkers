package server;

import players.Owner;
import players.Player;
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

    noConnectedPlayers = 1;
    Player owner = new Owner(socket.accept(), colors.get(0));
    pool.execute(owner);


  }


}
