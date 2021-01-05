package ISS.database.position.daoimpl;

import ISS.database.dao.Dao;
import ISS.database.position.entity.Position;
import ISS.database.utils.HibernateUtils;
import ISS.json.mapper.JsonMapper;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PositionDaoImplTest {

    Dao<Position> positionDao = new PositionDaoImpl();
    JsonMapper<Position> mapper = new JsonMapper<>();

    @BeforeEach
    void cleanTable(){
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session
                .createQuery("DELETE FROM Position")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void saving_and_finding_by_id() {
        //given
        String json = "{\"message\": \"success\", \"timestamp\": 1608206967, \"iss_position\": {\"latitude\": \"8.9186\", \"longitude\": \"65.3221\"}}";
        Position expectedPosition = mapper.mapJsonToObject(json,Position.class);

        //when
        positionDao.save(expectedPosition);
        Position savedPosition = positionDao.findById(1608206967L);

        //then
        assertEquals(expectedPosition,savedPosition);

    }

    @Test
    void findAll() {
        //given
        Position p1 = new Position(123456,10,20);
        Position p2 = new Position(12345,15,25);
        Position p3 = new Position(1234,20,30);

        positionDao.save(p1);
        positionDao.save(p2);
        positionDao.save(p3);
        List<Position> expectedList = List.of(p1,p2,p3);

        //when
        List<Position> positionList = positionDao.findAll();

        //then
        assertEquals(expectedList.size(),positionList.size());
    }

    @Test
    void deleteById() {
        //given
        String json = "{\"message\": \"success\", \"timestamp\": 1608206967, \"iss_position\": {\"latitude\": \"8.9186\", \"longitude\": \"65.3221\"}}";
        Position position = mapper.mapJsonToObject(json,Position.class);
        positionDao.save(position);

        //when
        positionDao.deleteById(1608206967L);
        Position deleted = positionDao.findById(1608206967L);

        //then
        assertNull(deleted);
    }

    @Test
    void find_last_position() {
        //given
        Position position1 = new Position();
        position1.setTimestamp(12345L);

        Position position2 = new Position();
        position2.setTimestamp(23456L);

        Position position3 = new Position();
        position3.setTimestamp(34567L);

        positionDao.save(position1);
        positionDao.save(position2);
        positionDao.save(position3);

        //when
        Position last = positionDao.findFromTheEnd(1);

        //then
        assertEquals(position3,last);
    }

    @Test
    void find_penultimate_position() {
        //given
        Position position1 = new Position();
        position1.setTimestamp(12345L);

        Position position2 = new Position();
        position2.setTimestamp(23456L);

        Position position3 = new Position();
        position3.setTimestamp(34567L);

        positionDao.save(position1);
        positionDao.save(position2);
        positionDao.save(position3);

        //when
        Position penultimate = positionDao.findFromTheEnd(2);

        //then
        assertEquals(position2,penultimate);
    }




}