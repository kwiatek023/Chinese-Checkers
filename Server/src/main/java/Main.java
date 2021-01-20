import server.Server;

import java.io.IOException;

/**
 * Main class that launches the server.
 */
public class Main {
  public static void main(String[] args) {
    try {
      Server server = new Server();
      if (args.length == 0) {
        server.openRoom();
      } else {
        server.watchGame(Integer.parseInt(args[0]));
      }
    } catch (IOException e) {
      System.out.println("Unable to run the server.");
    }

//    ApplicationContext appContext = new ClassPathXmlApplicationContext("config/spring-configuration.xml");
//    GamesEntity game = new GamesEntity();
//    game.setNoPlayers(2);
//
//    MovesEntity move1 = new MovesEntity("GREEN", 13, 12, 12, 12, game);
//    MovesEntity move2 = new MovesEntity("RED", 3, 5, 4, 6, game);
//
//    List<MovesEntity> moves = new ArrayList<>();
//    moves.add(move1);
//    moves.add(move2);
//
//    game.setMoves(moves);
//    Dao gameDao = (Dao) appContext.getBean("dao");
//    gameDao.saveGame(game);
  }
}