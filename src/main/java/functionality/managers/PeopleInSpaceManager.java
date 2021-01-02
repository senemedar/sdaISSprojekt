package functionality.managers;

import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import connection.IssApiCall;
import connection.RequestType;
import connection.WrongNumberOfArgumentsException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PeopleInSpaceManager implements Runnable {
	
	public PeopleInSpaceManager() {
	}
	
	public boolean startIssPositionQuery() {
		try {
			ScheduledExecutorService scheduledIssApiCall = Executors.newSingleThreadScheduledExecutor();
			scheduledIssApiCall.scheduleWithFixedDelay(this, 0, 1, TimeUnit.DAYS);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public void run() {
		IssApiCall issApiCall = new IssApiCall();
		String response = null;
		try {
			response = issApiCall.runRequest(RequestType.PEOPLE_IN_SPACE);
		} catch (WrongNumberOfArgumentsException e) {
			e.printStackTrace();
		}
		DatabaseManager.saveAstronautsIntoDatabase(response);
	}
	
	public NumberOfAstronauts getPeopleInSpace() {
		return DatabaseManager.getPeopleInSpaceFromDatabase();
	}
}
