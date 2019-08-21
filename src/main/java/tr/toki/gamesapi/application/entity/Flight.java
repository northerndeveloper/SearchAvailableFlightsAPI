package tr.toki.gamesapi.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents Aggreated Flight POJO which contains Cheap and Business Flights Together
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    private String departureCity;

    private String arrivalCity;

    private String departureTime;

    private String arrivalTime;

    private boolean isBusiness;

    @Override
    public String toString() {
        return "Flight [Departure City=" + departureCity + ", Arrival City=" + arrivalCity + ", Departure Time=" + departureTime + ", Arrival Time=" + arrivalTime +
            ", Business Flight=" + isBusiness + "]\\n";
    }

}   
