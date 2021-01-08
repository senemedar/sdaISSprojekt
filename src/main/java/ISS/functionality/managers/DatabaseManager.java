package ISS.functionality.managers;

import ISS.database.dao.Dao;
import ISS.database.numberofastronauts.daoimpl.NumberOfAstronautsDaoImpl;
import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.position.daoimpl.PositionDaoImpl;
import ISS.database.position.entity.Position;
import ISS.json.mapper.JsonMapper;

public class DatabaseManager {

    JsonMapper<Position> positionMapper = new JsonMapper<>();
    JsonMapper<NumberOfAstronauts> numberOfAstronautsMapper = new JsonMapper<>();
    Dao<Position> positionDao = new PositionDaoImpl();
    Dao<NumberOfAstronauts> numberOfAstronautsDao = new NumberOfAstronautsDaoImpl();


    public void savePosition(String json){
        Position position = positionMapper.mapJsonToObject(json,Position.class);
        positionDao.save(position);
    }

    public Position[] getTwoLastPositions(){

        Position last = positionDao.findFromTheEnd(1);
        Position penultimate = positionDao.findFromTheEnd(2);

        return new Position[]{last,penultimate};
    }

    public Position getLastPosition(){
        return positionDao.findFromTheEnd(1);
    }

    public void saveNumberOfAstronauts(String json){
        NumberOfAstronauts number = numberOfAstronautsMapper.mapJsonToObject(json, NumberOfAstronauts.class);
        number.setTimestamp(System.currentTimeMillis() / 1000L);
        numberOfAstronautsDao.save(number);
    }

    public NumberOfAstronauts getLastNumberOfAstronauts(){

        return numberOfAstronautsDao.findFromTheEnd(1);
    }




}
