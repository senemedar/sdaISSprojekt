package ISS.json.mapper;

import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.position.entity.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

    @Test
    void should_map_position_object_from_json_string() {
        //given
        String json = "{\"iss_position\": {\"longitude\": \"-72.7933\", \"latitude\": \"29.1579\"}, \"timestamp\": 1608204955, \"message\": \"success\"}";
        JsonMapper<Position> mapper = new JsonMapper<>();
        Position expectedPosition = new Position(1608204955,29.1579,-72.7933);

        //when
        Position mappedPosition = mapper.mapJsonToObject(json,Position.class);

        //then
        assertNotNull(mappedPosition);
        assertEquals(expectedPosition,mappedPosition);
    }

    @Test
    void should_map_number_of_astronauts_object_from_json_string() {
        //given
        String json = "{\"message\": \"success\", \"number\": 7, \"people\": [{\"craft\": \"ISS\", \"name\": \"Sergey Ryzhikov\"}, {\"craft\": \"ISS\", \"name\": \"Kate Rubins\"}, {\"craft\": \"ISS\", \"name\": \"Sergey Kud-Sverchkov\"}, {\"craft\": \"ISS\", \"name\": \"Mike Hopkins\"}, {\"craft\": \"ISS\", \"name\": \"Victor Glover\"}, {\"craft\": \"ISS\", \"name\": \"Shannon Walker\"}, {\"craft\": \"ISS\", \"name\": \"Soichi Noguchi\"}]}";
        JsonMapper<NumberOfAstronauts> mapper = new JsonMapper<>();
        NumberOfAstronauts expectedNumber = new NumberOfAstronauts(0,7);

        //when
        NumberOfAstronauts mappedNumber = mapper.mapJsonToObject(json,NumberOfAstronauts.class);

        //then
        assertNotNull(mappedNumber);
        assertEquals(expectedNumber,mappedNumber);
    }

    @Test
    void should_return_null_object_when_incorrect_json_is_given(){
        //given
        String json = "";
        JsonMapper<Object> mapper = new JsonMapper<>();

        //when
        Object object = mapper.mapJsonToObject(json,Object.class);

        //then
        assertNull(object);

    }


    @Test
    void should_map_json_string_from_position_object() {
        //given
        Position position = new Position(1608204955,29.1579,-72.7933);
        JsonMapper<Position> mapper = new JsonMapper<>();
        String expectedJson = "{\"timestamp\":1608204955,\"latitude\":29.1579,\"longitude\":-72.7933}";

        //when
        String mappedString = mapper.mapObjectToJson(position);

        //then
        assertNotNull(mappedString);
        assertEquals(expectedJson,mappedString);

    }

    @Test
    void should_map_json_string_from_number_of_astronauts_object() {
        //given
        NumberOfAstronauts number = new NumberOfAstronauts(0,7);
        JsonMapper<NumberOfAstronauts> mapper = new JsonMapper<>();
        String expectedJson = "{\"timestamp\":0,\"number\":7}";

        //when
        String mappedString = mapper.mapObjectToJson(number);

        //then
        assertNotNull(mappedString);
        assertEquals(expectedJson,mappedString);

    }



}