package ISS.database.numberofastronauts.daoimpl;

import ISS.database.dao.Dao;
import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.utils.HibernateUtils;
import ISS.json.mapper.JsonMapper;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NumberOfAstronautsDaoImpl implements Dao<NumberOfAstronauts> {

    @Override
    public void save(String json){
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        JsonMapper<NumberOfAstronauts> mapper = new JsonMapper<>();

        NumberOfAstronauts astronauts = mapper.mapJsonToObject(json,NumberOfAstronauts.class);
        astronauts.setTimestamp(LocalDate.now().toEpochDay());
        session.save(astronauts);

        session.getTransaction().commit();
        session.close();
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

        List<NumberOfAstronauts> resultList = new ArrayList<>();

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