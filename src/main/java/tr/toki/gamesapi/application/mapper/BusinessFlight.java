package tr.toki.gamesapi.application.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Business Flight object which is used to map from RestService to BusinessFlight oobject
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessFlight {

    @JsonProperty(value = "departure")
    private String departure;

    @JsonProperty(value = "arrival")
    private String arrival;

    @JsonProperty(value = "departureTime")
    private String departureTime;

    @JsonProperty(value = "arrivalTime")
    private String arrivalTime;

}   
