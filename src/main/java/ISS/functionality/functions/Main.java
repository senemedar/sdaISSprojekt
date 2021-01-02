package ISS.functionality.functions;

import ISS.connection.IssApiCall;
import ISS.connection.RequestType;
import ISS.connection.WrongNumberOfArgumentsException;
import ISS.database.dao.Dao;
import ISS.database.position.daoimpl.PositionDaoImpl;
import ISS.database.position.entity.Position;
import ISS.json.mapper.JsonMapper;


public class Main {
	public static void main(String[] args) {
/*		IssApiCall issApiCall = new IssApiCall();
//		String response = ISS.connection.run("https://raw.github.com/square/okhttp/master/README.md");
//		String response1 = issApiCall.runRequest(RequestType.CURRENT_LOCATION);
		String response2 = null;
		try {
			response2 = issApiCall.runRequest(RequestType.PASS_TIMES, 0.1);
		} catch (WrongNumberOfArgumentsException e) {
			e.printStackTrace();
		}
//		String response3 = issApiCall.runRequest(RequestType.PASS_TIMES, 0.1, 0.1, 2);
//		System.out.println(response1);
		System.out.println(response2);
//		System.out.println(response3);*/


		System.out.println("Hello world");

		String json = "{\"timestamp\": 1609606085, \"iss_position\": {\"latitude\": \"-2.0539\", \"longitude\": \"-91.6543\"}, \"message\": \"success\"}";
		JsonMapper<Position> positionMapper = new JsonMapper<>();
		Position position = positionMapper.mapJsonToObject(json,Position.class);
		Dao<Position> positionDao = new PositionDaoImpl();
		positionDao.save(json);
		positionDao.findAll();



	}
}
