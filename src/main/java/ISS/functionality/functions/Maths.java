package ISS.functionality.functions;

public class Maths {
    public static double calculateSpeed(double lat1, double lon1,double lat2, double lon2, long timestamp1, long timestamp2){
        //Formuła Haversine - Haversine formula Formuła Haversine - https://pl.qaz.wiki/wiki/Haversine_formula

        double R = 6_371_000 + 408_000;         // promień ziemi w metrach + orbita ISS w metrach
        double φ1 = lat1 * Math.PI/180;         // φ, λ w radianach
        double φ2 = lat2 * Math.PI/180;         // φ, λ w radianach
        double Δφ = (lat2-lat1) * Math.PI/180;  // φ, λ w radianach
        double Δλ = (lon2-lon1) * Math.PI/180;  // φ, λ w radianach

        double a =  Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                        Math.sin(Δλ/2) * Math.sin(Δλ/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double d = R * c; // d - odległość między dwoma punktami wzdłuż wielkiego koła kuli (odległość sferyczna)

        return (d/Math.abs(timestamp1-timestamp2))/1000;
    }




}
