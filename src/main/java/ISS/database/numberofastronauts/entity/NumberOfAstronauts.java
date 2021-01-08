package ISS.database.numberofastronauts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "number_of_astronauts")
@JsonIgnoreProperties(value = {"people","message"})
public class NumberOfAstronauts {

    @Id
    private long timestamp;
    @JsonProperty("number")
    private int count;

    public NumberOfAstronauts() {
    }

    public NumberOfAstronauts(long timestamp, int count) {
        this.timestamp = timestamp;
        this.count = count;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfAstronauts that = (NumberOfAstronauts) o;
        return timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp);
    }
}
