package ISS.json.mapper;

import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.position.entity.Position;
import ISS.functionality.isspasses.ISSPassesRequest;
import ISS.functionality.isspasses.ISSPassesResponse;
import ISS.functionality.isspasses.ISSPass;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

    JsonMapper<Position> positionMapper = new JsonMapper<>();
    JsonMapper<NumberOfAstronauts> numberOfAstronautsMapper = new JsonMapper<>();
    JsonMapper<ISSPassesResponse> issPassesResponseMapper = new JsonMapper<>();


    @Test
    void should_map_position_object_from_json_string() {
        //given
        String json = "{\"iss_position\": {\"longitude\": \"-72.7933\", \"latitude\": \"29.1579\"}, \"timestamp\": 1608204955, \"message\": \"success\"}";
        Position expectedPosition = new Position(1608204955,29.1579,-72.7933);

        //when
        Position mappedPosition = positionMapper.mapJsonToObject(json,Position.class);

        //then
        assertNotNull(mappedPosition);
        assertEquals(expectedPosition,mappedPosition);
    }

    @Test
    void should_map_number_of_astronauts_object_from_json_string() {
        //given
        String json = "{\"message\": \"success\", \"number\": 7, \"people\": [{\"craft\": \"ISS\", \"name\": \"Sergey Ryzhikov\"}, {\"craft\": \"ISS\", \"name\": \"Kate Rubins\"}, {\"craft\": \"ISS\", \"name\": \"Sergey Kud-Sverchkov\"}, {\"craft\": \"ISS\", \"name\": \"Mike Hopkins\"}, {\"craft\": \"ISS\", \"name\": \"Victor Glover\"}, {\"craft\": \"ISS\", \"name\": \"Shannon Walker\"}, {\"craft\": \"ISS\", \"name\": \"Soichi Noguchi\"}]}";
        NumberOfAstronauts expectedNumber = new NumberOfAstronauts(0,7);

        //when
        NumberOfAstronauts mappedNumber = numberOfAstronautsMapper.mapJsonToObject(json,NumberOfAstronauts.class);

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
        String expectedJson = "{\"timestamp\":1608204955,\"latitude\":29.1579,\"longitude\":-72.7933}";

        //when
        String mappedString = positionMapper.mapObjectToJson(position);

        //then
        assertNotNull(mappedString);
        assertEquals(expectedJson,mappedString);

    }

    @Test
    void should_map_json_string_from_number_of_astronauts_object() {
        //given
        NumberOfAstronauts number = new NumberOfAstronauts(0,7);
        String expectedJson = "{\"timestamp\":0,\"number\":7}";

        //when
        String mappedString = numberOfAstronautsMapper.mapObjectToJson(number);

        //then
        assertNotNull(mappedString);
        assertEquals(expectedJson,mappedString);

    }


    @Test
    void should_map_passes_response(){
        //given
        String json = "{\n" +
                "  \"message\": \"success\", \n" +
                "  \"request\": {\n" +
                "    \"altitude\": 100, \n" +
                "    \"datetime\": 1609692121, \n" +
                "    \"latitude\": 52.229676, \n" +
                "    \"longitude\": 21.012229, \n" +
                "    \"passes\": 3\n" +
                "  }, \n" +
                "  \"response\": [\n" +
                "    {\n" +
                "      \"duration\": 551, \n" +
                "      \"risetime\": 1609715393\n" +
                "    }, \n" +
                "    {\n" +
                "      \"duration\": 645, \n" +
                "      \"risetime\": 1609721117\n" +
                "    }, \n" +
                "    {\n" +
                "      \"duration\": 656, \n" +
                "      \"risetime\": 1609726911\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        ISSPass[] ISSPasses = {new ISSPass(1609715393,551),new ISSPass(1609721117,645),new ISSPass(1609726911,656)};
        ISSPassesRequest request = new ISSPassesRequest(52.229676,21.012229,100,3,1609692121);
        ISSPassesResponse expectedResponse = new ISSPassesResponse("success",request, ISSPasses);

        //when
        ISSPassesResponse mappedResponse = issPassesResponseMapper.mapJsonToObject(json,ISSPassesResponse.class);
        String expected = Arrays.toString(expectedResponse.getPasses());
        String mapped = Arrays.toString(mappedResponse.getPasses());

        //then
        System.out.println(expected);
        System.out.println(mapped);
        assertEquals(expected,mapped);

    }


}