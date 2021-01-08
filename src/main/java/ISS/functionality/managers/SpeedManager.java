package ISS.functionality.managers;

import ISS.database.position.entity.Position;
import ISS.functionality.functions.Maths;
import ISS.functionality.userComms.MainWindow;

public class SpeedManager {
    public double getSpeed(){
        Position[] issPositions = MainWindow.getDatabaseManager().getTwoLastPositions();

        double lat1 = issPositions[0].getLatitude(); //18.0200;
        double lat2 = issPositions[1].getLatitude(); //18.2896;
        double lon1 = issPositions[0].getLongitude(); //-53.1479;
        double lon2 = issPositions[1].getLongitude(); //-52.9264;
        long timestamp1 = issPositions[0].getTimestamp(); //1609642633;
        long timestamp2 = issPositions[1].getTimestamp(); //1609642639;

        return Maths.calculateSpeed(lat1,lon1,lat2,lon2,timestamp1,timestamp2);
    }
}
