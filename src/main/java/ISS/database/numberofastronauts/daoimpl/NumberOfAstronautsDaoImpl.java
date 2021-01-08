package ISS.database.numberofastronauts.daoimpl;

import ISS.database.dao.Dao;
import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.utils.HibernateUtils;
import org.hibernate.Session;
import javax.persistence.NoResultException;
import java.util.List;

public class NumberOfAstronautsDaoImpl implements Dao<NumberOfAstronauts> {

    @Override
    public void save(NumberOfAstronauts number){
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.save(number);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public NumberOfAstronauts findFromTheEnd(int count) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        NumberOfAstronauts number = session
                .createQuery("from NumberOfAstronauts order by timestamp desc",NumberOfAstronauts.class)
                .getResultList()
                .get(count - 1);

        session.getTransaction().commit();
        session.close();

        return number;
    }

    @Override
    public NumberOfAstronauts findById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        NumberOfAstronauts numberOfAstronauts = null;

        try{
            numberOfAstronauts = session
                    .createQuery("from NumberOfAstronauts where id=:id",NumberOfAstronauts.class)
                    .setParameter("id",id)
                    .getSingleResult();
        }catch(NoResultException e) {
            e.printStackTrace();
        }

        session.getTransaction().commit();
        session.close();
        return numberOfAstronauts;
    }

    @Override
    public List<NumberOfAstronauts> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<NumberOfAstronauts> resultList;

        resultList = session
                .createQuery("from NumberOfAstronauts",NumberOfAstronauts.class)
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

        session.createQuery("delete from NumberOfAstronauts where id=:id")
                .setParameter("id",id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}
