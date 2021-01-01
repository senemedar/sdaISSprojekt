package functionality.managers;

import ISS.database.position.entity.Position;
import connection.IssApiCall;
import connection.RequestType;
import connection.WrongNumberOfArgumentsException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PositionManager implements Runnable {

	public PositionManager() {
//		startIssPositionQuery();
		// TODO: 01/01/2021 Pomyśleć, czy tworzenie nowego obiektu ma wywołać nowy wątek, czy chcemy to robić z Main-a
	}
	
	public Position getCurrentPosition() {
		return DatabaseManager.readLatestPositionFromDatabase();
	}

	public void startIssPositionQuery() {
		ScheduledExecutorService scheduledIssApiCall = Executors.newSingleThreadScheduledExecutor();
		scheduledIssApiCall.scheduleWithFixedDelay(this, 0, 10, TimeUnit.SECONDS);
	}
	
	@Override
	public void run() {
		IssApiCall issApiCall = new IssApiCall();
		String response = null;
			try {
				response = issApiCall.runRequest(RequestType.CURRENT_POSITION);
			} catch (WrongNumberOfArgumentsException e) {
				e.printStackTrace();
			}
			DatabaseManager.savePositionIntoDatabase(response);
	}
}
