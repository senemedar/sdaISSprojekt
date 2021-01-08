package ISS.database.numberofastronauts.daoimpl;

import ISS.database.dao.Dao;
import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.position.entity.Position;
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
        numberOfAstronautsDao.save(number);
        NumberOfAstronauts savedNumber = numberOfAstronautsDao.findById(number.getTimestamp());

        //then
        assertEquals(number,savedNumber);
    }

    @Test
    void findAll() {
        //given
        NumberOfAstronauts n1 = new NumberOfAstronauts(10,5);
        NumberOfAstronauts n2 = new NumberOfAstronauts(20,10);
        NumberOfAstronauts n3 = new NumberOfAstronauts(30,15);

        numberOfAstronautsDao.save(n1);
        numberOfAstronautsDao.save(n2);
        numberOfAstronautsDao.save(n3);
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
        NumberOfAstronauts number = mapper.mapJsonToObject(json,NumberOfAstronauts.class);
        number.setTimestamp(1);
        numberOfAstronautsDao.save(number);

        //when
        numberOfAstronautsDao.deleteById(1L);
        NumberOfAstronauts deleted = numberOfAstronautsDao.findById(1L);

        //then
        assertNull(deleted);

    }

    @Test
    void find_last_position() {
        //given
        NumberOfAstronauts number1 = new NumberOfAstronauts();
        number1.setTimestamp(12345L);

        NumberOfAstronauts number2 = new NumberOfAstronauts();
        number2.setTimestamp(23456L);

        NumberOfAstronauts number3 = new NumberOfAstronauts();
        number3.setTimestamp(34567L);

        numberOfAstronautsDao.save(number1);
        numberOfAstronautsDao.save(number2);
        numberOfAstronautsDao.save(number3);

        //when
        NumberOfAstronauts last = numberOfAstronautsDao.findFromTheEnd(1);

        //then
        assertEquals(number3,last);
    }

    @Test
    void find_penultimate_position() {
        //given
        NumberOfAstronauts number1 = new NumberOfAstronauts();
        number1.setTimestamp(12345L);

        NumberOfAstronauts number2 = new NumberOfAstronauts();
        number2.setTimestamp(23456L);

        NumberOfAstronauts number3 = new NumberOfAstronauts();
        number3.setTimestamp(34567L);

        numberOfAstronautsDao.save(number1);
        numberOfAstronautsDao.save(number2);
        numberOfAstronautsDao.save(number3);

        //when
        NumberOfAstronauts penultimate = numberOfAstronautsDao.findFromTheEnd(2);

        //then
        assertEquals(number2,penultimate);
    }
}