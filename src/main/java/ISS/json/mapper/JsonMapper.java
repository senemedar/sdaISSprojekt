package ISS.json.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper<T> {

    private ObjectMapper mapper = new ObjectMapper();

    public JsonMapper(){

    }

    public T mapJsonToObject(String json,Class<T> tClass){

        try {
            T tObject = mapper.readValue(json,tClass);
            return tObject;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String mapObjectToJson(T tObject){
        try {
            return mapper.writeValueAsString(tObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
