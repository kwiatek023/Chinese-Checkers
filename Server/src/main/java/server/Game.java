package server;

import gameVariants.ConcreteVariantFactory;
import gameVariants.GameVariant;
import logic.Board;
import logic.MoveController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executors;

public class Game {
  private ServerSocket socket;
  private int noConnectedPlayers;
  private List<String> colors = new ArrayList<>(Arrays.asList("GREEN"));
  private int noPlayers;
  private List<Player> players;
  private Board board;
  private MoveController controller;
  private int indexCurrentPlayer;
  private Player currentPlayer;


  public Game(ServerSocket socket) {
    this.socket = socket;
    this.players = createPlayersList();
    try {
      start();
    } catch (IOException e) {

    }
  }

  private List<Player> createPlayersList() {
    return new ArrayList<>();
  }

  private void start() throws IOException {
    GameVariant gameVariant = null;
    Player owner = new Player(socket.accept(), colors.get(0));
    players.add(owner);
    noConnectedPlayers = 1;

    owner.protocol.sendHandshake("OWNER");

    if (owner.input.hasNextLine()) {
      gameVariant = new ConcreteVariantFactory().getGameVariant(owner.input.nextLine());
    }

    if (owner.input.hasNextLine()) {
      noPlayers = Integer.parseInt(owner.input.nextLine());
      board = new Board(gameVariant, noPlayers);
      controller = new MoveController(board);

    }

    matchColors(noPlayers);
    connectWithOtherPlayers();
    makeQueue();
    runPlayers();
  }

  private void connectWithOtherPlayers() throws IOException {
    while (noConnectedPlayers < noPlayers) {
      var player = new Player(socket.accept(), colors.get(noConnectedPlayers));
      player.protocol.sendHandshake("PLAYER");
      players.add(player);
      noConnectedPlayers++;
    }
  }

  private void makeQueue() {
    indexCurrentPlayer = new Random().nextInt(noPlayers);
    currentPlayer = players.get(indexCurrentPlayer);
  }

  private void matchColors(int noPlayers) {
    switch (noPlayers) {
      case 2: {
        colors.add("RED");
        break;
      }
      case 3: {
        colors.add("YELLOW");
        colors.add("BLACK");
        break;
      }
      case 4: {
        colors.add("YELLOW");
        colors.add("RED");
        colors.add("BLUE");
        break;
      }
      case 6: {
        colors.add("WHITE");
        colors.add("YELLOW");
        colors.add("RED");
        colors.add("BLACK");
        colors.add("BLUE");
        break;
      }
    }
  }


  private void runPlayers() {
    var pool = Executors.newFixedThreadPool(6);

    for (Player player : players) {
      pool.execute(player);
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
      protocol.welcome(color, noPlayers, currentPlayer.color);
    }

    private void processCommands() {
      while (input.hasNextLine()) {
        var command = input.nextLine();
        if (command.startsWith("QUIT")) {
          return;
        } else if (command.startsWith("MOVE")) {
          processMoveCommand(command.substring(5));
        }
      }
    }

    private void processMoveCommand(String data) {

    }
  }
}
