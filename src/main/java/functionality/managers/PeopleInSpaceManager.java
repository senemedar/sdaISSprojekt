package functionality.managers;

import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import connection.IssApiCall;
import connection.RequestType;
import connection.WrongNumberOfArgumentsException;

public class PeopleInSpaceManager {
	public static NumberOfAstronauts getPeopleInSpace() {
		IssApiCall issApiCall = new IssApiCall();
		String response = null;
		try {
			response = issApiCall.runRequest(RequestType.PEOPLE_IN_SPACE);
		} catch (WrongNumberOfArgumentsException e) {
			e.printStackTrace();
		}
		
		return DatabaseManager.processAstronauts(response);
	}
}
