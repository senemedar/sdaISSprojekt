package ISS.functionality.managers;

import ISS.connection.IssApiCall;
import ISS.connection.RequestType;
import ISS.connection.WrongNumberOfArgumentsException;
import ISS.functionality.userComms.MainWindow;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PositionManager implements Runnable{

    public PositionManager() {
//		startIssPositionQuery();
        // TODO: 01/01/2021 Pomyśleć, czy tworzenie nowego obiektu ma wywołać nowy wątek, czy chcemy to robić z Main-a
    }

    public boolean startIssPositionQuery() {
        try {
            ScheduledExecutorService scheduledIssApiCall = Executors.newSingleThreadScheduledExecutor();
            scheduledIssApiCall.scheduleWithFixedDelay(this, 0, 10, TimeUnit.SECONDS);
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
            response = issApiCall.runRequest(RequestType.CURRENT_POSITION);
        } catch (WrongNumberOfArgumentsException e) {
            e.printStackTrace();
        }
    
        MainWindow.getDatabaseManager().savePosition(response);
    }
}
