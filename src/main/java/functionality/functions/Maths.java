package functionality.functions;
import ISS.database.position.entity.Position;
import functionality.managers.DatabaseManager;

import java.math.*;

public class Maths {
public static double calculateSpeed(){
    Position[] issPositions = DatabaseManager.getLast2PositionsFromDatabase();
    double lat1 = issPositions[0].getLatitude(); //18.0200;
    double lat2 = issPositions[1].getLatitude(); //18.2896;
    double lon1 = issPositions[0].getLongitude(); //-53.1479;
    double lon2 = issPositions[1].getLongitude(); //-52.9264;
    double timestamp1 = issPositions[0].getTimestamp(); //1609642633;
    double timestamp2 = issPositions[1].getTimestamp(); //1609642639;


    //Formuła Haversine - Haversine formula Formuła Haversine - https://pl.qaz.wiki/wiki/Haversine_formula

    double R = 6_371_000 + 408_000;         // promien ziemi w metrach + orbita ISS w metrach
    double φ1 = lat1 * Math.PI/180;         // φ, λ w radianach
    double φ2 = lat2 * Math.PI/180;         // φ, λ w radianach
    double Δφ = (lat2-lat1) * Math.PI/180;  // φ, λ w radianach
    double Δλ = (lon2-lon1) * Math.PI/180;  // φ, λ w radianach

    double a =  Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                       Math.cos(φ1) * Math.cos(φ2) *
                       Math.sin(Δλ/2) * Math.sin(Δλ/2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    double d = R * c; // d - odległość między dwoma punktami wzdłuż wielkiego koła kuli (odległość sferyczna)

    return (d/5)/1000;

    }

}
