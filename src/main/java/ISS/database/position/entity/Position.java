package ISS.database.position.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "iss_position")
@JsonIgnoreProperties(value = {"message"})
public class Position {

    @Id
    private long timestamp;
    private double latitude;
    private double longitude;

    @JsonProperty("iss_position")
    private void unpackNested(Map<String,Double> position) {
        this.latitude = position.get("latitude");
        this.longitude = position.get("longitude");
    }

    public Position() {
    }

    public Position(long timestamp, double latitude, double longitude) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return timestamp == position.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp);
    }
}
