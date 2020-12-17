package ISS.database.numberofastronauts.daoimpl;

import ISS.database.dao.Dao;
import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.utils.HibernateUtils;
import ISS.json.mapper.JsonMapper;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NumberOfAstronautsDaoImplTest {

    Dao<NumberOfAstronauts> numberOfAstronautsDao = new NumberOfAstronautsDaoImpl();
    JsonMapper<NumberOfAstronauts> mapper = new JsonMapper<>();

    @BeforeEach
    void cleanTable(){
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session
                .createQuery("DELETE FROM NumberOfAstronauts")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    @Test
    void saving_and_finding_by_id() {
        //given
        String json = "{\"message\": \"success\", \"number\": 7, \"people\": [{\"craft\": \"ISS\", \"name\": \"Sergey Ryzhikov\"}, {\"craft\": \"ISS\", \"name\": \"Kate Rubins\"}, {\"craft\": \"ISS\", \"name\": \"Sergey Kud-Sverchkov\"}, {\"craft\": \"ISS\", \"name\": \"Mike Hopkins\"}, {\"craft\": \"ISS\", \"name\": \"Victor Glover\"}, {\"craft\": \"ISS\", \"name\": \"Shannon Walker\"}, {\"craft\": \"ISS\", \"name\": \"Soichi Noguchi\"}]}";
        NumberOfAstronauts number = mapper.mapJsonToObject(json,NumberOfAstronauts.class);
        number.setTimestamp(LocalDate.now().toEpochDay());

        //when
        numberOfAstronautsDao.save(json);
        NumberOfAstronauts savedNumber = numberOfAstronautsDao.findById(number.getTimestamp());

        //then
        assertEquals(number,savedNumber);
    }

    @Test
    void findAll() {
        //given
        NumberOfAstronauts n1 = new NumberOfAstronauts(10,5);
        String j1 = mapper.mapObjectToJson(n1);
        NumberOfAstronauts n2 = new NumberOfAstronauts(20,10);
        String j2 = mapper.mapObjectToJson(n2);
        NumberOfAstronauts n3 = new NumberOfAstronauts(30,15);
        String j3 = mapper.mapObjectToJson(n3);

        numberOfAstronautsDao.save(j1);
        numberOfAstronautsDao.save(j2);
        numberOfAstronautsDao.save(j3);
        List<NumberOfAstronauts> expectedList = List.of(n1,n2,n3);

        //when
        List<NumberOfAstronauts> numberList = numberOfAstronautsDao.findAll();

        //then
        assertEquals(expectedList.size(),numberList.size());

    }

    @Test
    void deleteById() {
        //given
        String json = "{\"message\": \"success\", \"number\": 7, \"people\": [{\"craft\": \"ISS\", \"name\": \"Sergey Ryzhikov\"}, {\"craft\": \"ISS\", \"name\": \"Kate Rubins\"}, {\"craft\": \"ISS\", \"name\": \"Sergey Kud-Sverchkov\"}, {\"craft\": \"ISS\", \"name\": \"Mike Hopkins\"}, {\"craft\": \"ISS\", \"name\": \"Victor Glover\"}, {\"craft\": \"ISS\", \"name\": \"Shannon Walker\"}, {\"craft\": \"ISS\", \"name\": \"Soichi Noguchi\"}]}";
        numberOfAstronautsDao.save(json);

        //when
        numberOfAstronautsDao.deleteById(LocalDate.now().toEpochDay());
        NumberOfAstronauts deleted = numberOfAstronautsDao.findById(LocalDate.now().toEpochDay());

        //then
        assertNull(deleted);

    }
}