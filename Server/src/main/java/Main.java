import db.DAO;
import db.GamesEntity;
import db.MovesEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class that launches the server.
 */
public class Main {
  public static void main(String[] args) {
//    try {
//      Server server = new Server();
//      server.openRoom();
//    } catch (IOException e) {
//      System.out.println("Unable to run the server.");
//    }
    ApplicationContext appContext = new ClassPathXmlApplicationContext(
            "config/spring-configuration.xml");


    GamesEntity game = new GamesEntity();

    MovesEntity move1 = new MovesEntity("GREEN", 1, 1, 2, 3);
//    MovesEntity move2 = new MovesEntity("RED", 1, 7, 9, 3);
    List<MovesEntity> moves = new ArrayList<>();
    moves.add(move1);
//    moves.add(move2);

    game.setDate(new Timestamp(System.currentTimeMillis()));
    game.setMoves(moves);
    DAO gameDao = (DAO) appContext.getBean("dao");
    gameDao.saveGame(game);

//    categoryDao.getArticlesForCategory(1);
  }
}