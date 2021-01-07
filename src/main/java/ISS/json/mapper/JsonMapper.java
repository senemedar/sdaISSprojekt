package ISS.json.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper<T> {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonMapper(){
    }

    public T mapJsonToObject(String json,Class<T> tClass){

        try {
            return mapper.readValue(json,tClass);
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
