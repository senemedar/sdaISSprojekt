package ISS.functionality.managers;

import ISS.connection.IssApiCall;
import ISS.connection.RequestType;
import ISS.connection.WrongNumberOfArgumentsException;
import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.functionality.userComms.MainWindow;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PeopleInSpaceManager implements Runnable{

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
        MainWindow.getDatabaseManager().saveNumberOfAstronauts(response);
    }

    public NumberOfAstronauts getPeopleInSpace() {
        return MainWindow.getDatabaseManager().getLastNumberOfAstronauts();
    }
}
