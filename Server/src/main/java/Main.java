import server.Server;

import java.io.IOException;

/**
 * Main class that launches the server.
 */
public class Main {
  public static void main(String[] args) {
    try {
      Server server = new Server();
      server.openRoom();
    } catch (IOException e) {
      System.out.println("Unable to run the server.");
    }
//    ApplicationContext appContext = new ClassPathXmlApplicationContext(
//            "config/spring-configuration.xml");
//
//
//    GamesEntity game = new GamesEntity(new Timestamp(System.currentTimeMillis()));
//
//    MovesEntity move1 = new MovesEntity("GREEN", 1, 1, 2, 3, game);
//    MovesEntity move2 = new MovesEntity("RED", 1, 7, 9, 3, game);
//
//    List<MovesEntity> moves = new ArrayList<>();
//    moves.add(move1);
//    moves.add(move2);
//
//    game.setMoves(moves);
//    Dao gameDao = (Dao) appContext.getBean("dao");
//    gameDao.saveGame(game);
//
//    List<MovesEntity> retrievedMoves = gameDao.getMovesForGame(1);
//    for(MovesEntity movesEntity: retrievedMoves) {
//      System.out.println(movesEntity.getMoveId());
//    }
  }
}