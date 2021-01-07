package ISS.database.position.daoimpl;

import ISS.database.dao.Dao;
import ISS.database.position.entity.Position;
import ISS.database.utils.HibernateUtils;
import org.hibernate.Session;
import javax.persistence.NoResultException;
import java.util.List;

public class PositionDaoImpl implements Dao<Position> {

    @Override
    public void save(Position position){
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.save(position);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Position findFromTheEnd(int count) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        Position position = session
                .createQuery("from Position order by timestamp desc",Position.class)
                .getResultList()
                .get(count - 1);

        session.getTransaction().commit();
        session.close();

        return position;
    }

    @Override
    public Position findById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        Position position = null;

        try{
            position = session
                    .createQuery("from Position where id=:id",Position.class)
                    .setParameter("id",id)
                    .getSingleResult();
        }catch(NoResultException e) {
            e.printStackTrace();
        }

        session.getTransaction().commit();
        session.close();

        return position;
    }

    @Override
    public List<Position> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<Position> resultList;

        resultList = session
                .createQuery("from Position",Position.class)
                .getResultList();

        session.close();

        return resultList;
    }

    @Override
    public void deleteById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete from Position where id=:id")
                .setParameter("id",id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}
