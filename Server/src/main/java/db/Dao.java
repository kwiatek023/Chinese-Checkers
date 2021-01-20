package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class Dao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveGame(GamesEntity game) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(game);
        session.getTransaction().commit();
        session.close();
    }


    public List<MovesEntity> getMovesForGame(int id) {
        Session session = sessionFactory.openSession();
        String sqlQuery = "FROM MovesEntity WHERE game_id=" + id;

        @SuppressWarnings("unchecked")
        List<MovesEntity> moves = (List<MovesEntity>) session.createQuery(sqlQuery).list();

        session.close();

        return moves;
    }

}
