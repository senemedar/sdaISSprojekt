package ISS.functionality.isspasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ISSPassesRequest {

    private double latitude;
    private double longitude;
    private double altitude;
    @JsonProperty("passes")
    private int passesToReturn;
    @JsonProperty("datetime")
    private long requestTimestamp;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public int getPassesToReturn() {
        return passesToReturn;
    }

    public void setPassesToReturn(int passesToReturn) {
        this.passesToReturn = passesToReturn;
    }

    public long getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(long requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public ISSPassesRequest(double latitude, double longitude, double altitude, int passesToReturn, long requestTimestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.passesToReturn = passesToReturn;
        this.requestTimestamp = requestTimestamp;
    }

    public ISSPassesRequest() {
    }
}
