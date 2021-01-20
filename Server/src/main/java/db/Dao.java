package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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


    public GamesEntity getGame(int id) {
        Session session = sessionFactory.openSession();

        Query<GamesEntity> query = session.createQuery("FROM GamesEntity WHERE id = (:id)", GamesEntity.class);
        query.setParameter("id", id);
        GamesEntity gamesEntity = query.getSingleResult();

        session.close();

        return gamesEntity;
    }

}
