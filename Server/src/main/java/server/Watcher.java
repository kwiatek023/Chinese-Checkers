package server;

import db.Dao;
import db.GamesEntity;
import db.MovesEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Watcher {
  private final ServerSocket server;
  private final Protocol protocol;
  private final int gameId;
  private Socket socket;
  private GamesEntity gamesEntity;

  public Watcher(ServerSocket server, int gameId) throws IOException {
    this.server = server;
    this.gameId = gameId;
    socket = server.accept();
    protocol = new Protocol(new PrintWriter(socket.getOutputStream(), true));
    watchGame();
  }

  private void watchGame() {
    ApplicationContext appContext = new ClassPathXmlApplicationContext("config/spring-configuration.xml");
    Dao dao = (Dao) appContext.getBean("dao");
    gamesEntity = dao.getGame(gameId);

    protocol.sendHandshake("WATCHER " + gamesEntity.getNoPlayers());

    for (MovesEntity move : gamesEntity.getMoves()) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      protocol.pawnMoved(move.getOldVerticalId(), move.getOldHorizontalId(), move.getNewVerticalId(), move.getNewHorizontalId());
    }

    protocol.endGame();
  }
}
