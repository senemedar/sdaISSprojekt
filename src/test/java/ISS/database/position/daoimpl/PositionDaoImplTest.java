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
        positionDao.save(json);
        Position savedPosition = positionDao.findById(1608206967L);

        //then
        assertEquals(expectedPosition,savedPosition);

    }

    @Test
    void findAll() {
        //given
        Position p1 = new Position(123456,10,20);
        String j1 = mapper.mapObjectToJson(p1);
        Position p2 = new Position(12345,15,25);
        String j2 = mapper.mapObjectToJson(p2);
        Position p3 = new Position(1234,20,30);
        String j3 = mapper.mapObjectToJson(p3);

        positionDao.save(j1);
        positionDao.save(j2);
        positionDao.save(j3);
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
        positionDao.save(json);

        //when
        positionDao.deleteById(1608206967L);
        Position deleted = positionDao.findById(1608206967L);

        //then
        assertNull(deleted);


    }
}