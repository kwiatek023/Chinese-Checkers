package server;

import gameVariants.ConcreteVariantFactory;
import gameVariants.GameVariant;
import logic.Board;

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
  private int noPlayersInGame;
  private List<Player> players;
  private Board board;
  private GameVariant gameVariant;
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
    System.out.println("Adding owner.");
    Player owner = new Player(socket.accept(), colors.get(0));
    players.add(owner);
    noConnectedPlayers = 1;
    System.out.println("Owner connected.");

    owner.protocol.sendHandshake("OWNER");

    if (owner.input.hasNextLine()) {
      System.out.println("Create game variant.");
      gameVariant = new ConcreteVariantFactory().getGameVariant(owner.input.nextLine());
    }

    if (gameVariant == null) {
      throw new IllegalArgumentException();
    }

    if (owner.input.hasNextLine()) {
      System.out.println("Create board.");
      noPlayers = Integer.parseInt(owner.input.nextLine());
      noPlayersInGame = noPlayers;
      board = new Board(noPlayers);
      gameVariant.setBoard(board);
    }

    matchColors(noPlayers);
    connectWithOtherPlayers();
    makeQueue();
    runPlayers();
  }

  private void connectWithOtherPlayers() throws IOException {
    while (noConnectedPlayers < noPlayers) {
      System.out.println("Waiting for players nr. " + noConnectedPlayers + 1);
      var player = new Player(socket.accept(), colors.get(noConnectedPlayers));
      System.out.println("Player connected.");
      player.protocol.sendHandshake("PLAYER");
      players.add(player);
      noConnectedPlayers++;
    }
  }

  private void makeQueue() {
    indexCurrentPlayer = new Random().nextInt(noPlayers);
    currentPlayer = players.get(indexCurrentPlayer);
    System.out.println("Queue created. " + currentPlayer.color + " begins.");

  }

  private void updateQueue() {
    do {
      indexCurrentPlayer = (indexCurrentPlayer + 1) % noPlayers;
      currentPlayer = players.get(indexCurrentPlayer);
    } while (currentPlayer.hasFinished);
    System.out.println("currentPlayer: " + currentPlayer.color);
  }

  private void endTurn() {
    System.out.println("Next turn.");
    updateQueue();

    for (Player player : players) {
      player.protocol.nextTurn(currentPlayer.color);
    }
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
    private boolean hasFinished = false;
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
      processCommands();

      try {
        socket.close();
      } catch (IOException e) {
      }
    }

    private void processCommands() {
      while (input.hasNextLine()) {
        var command = input.nextLine();
        if (command.startsWith("RAGE_QUIT")) {
          for (Player player : players) {
            if (player != this) {
              player.protocol.rageQuit(this.color);
            }
          }
          return;
        } else if (command.startsWith("QUIT")) {
          return;
        } else if (command.startsWith("MOVE")) {
          processMoveCommand(command.substring(5));
        } else if (command.startsWith("END_TURN")) {
          if (this == currentPlayer) {
            endTurn();
          }
        }
      }
    }

    private void processMoveCommand(String data) {
      String[] commands = data.split(" ");
      int oldVerticalID = Integer.parseInt(commands[0]);
      int oldHorizontalID = Integer.parseInt(commands[1]);
      int newVerticalID = Integer.parseInt(commands[2]);
      int newHorizontalID = Integer.parseInt(commands[3]);

      if (currentPlayer == this) {
        if (gameVariant.isValidMove(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID)) {
          board.updatePawns(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
          this.protocol.validMoveMsg(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);

          for (Player player : players) {
            if (player != this) {
              player.protocol.opponentMoved(oldVerticalID, oldHorizontalID, newVerticalID, newHorizontalID);
            }
          }

          if (gameVariant.hasFinished(currentPlayer.color)) {
            hasFinished = true;
            noPlayersInGame--;

            for (Player player : players) {
              player.protocol.hasFinished(currentPlayer.color);
            }
          }

          if (noPlayersInGame == 0) {
            for (Player player : players) {
              player.protocol.endGame();
            }
          } else if (!gameVariant.isNextJumpPossible()) {
            endTurn();
          }
        }
      }
    }
  }
}
