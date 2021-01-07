package ISS.functionality.managers;

import ISS.connection.IssApiCall;
import ISS.connection.RequestType;
import ISS.connection.WrongNumberOfArgumentsException;
import ISS.functionality.isspasses.ISSPass;
import ISS.functionality.isspasses.ISSPassesResponse;
import ISS.json.mapper.JsonMapper;

public class ISSPassesManager {

    IssApiCall apiCall = new IssApiCall();

    public ISSPass[] getPasses(double latitude, double longitude, int passesCount){

        ISSPass[] ISSPasses = {};
        try {

            String json = apiCall.runRequest(RequestType.PASS_TIMES,latitude,longitude,passesCount);
            JsonMapper<ISSPassesResponse> responseMapper = new JsonMapper<>();
            ISSPassesResponse response = responseMapper.mapJsonToObject(json,ISSPassesResponse.class);
            ISSPasses = response.getPasses();

        } catch (WrongNumberOfArgumentsException e) {
            e.printStackTrace();
        }

        return ISSPasses;
    }
}
