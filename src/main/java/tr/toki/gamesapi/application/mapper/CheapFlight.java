package tr.toki.gamesapi.application.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Cheap Flight object which is used to map from RestService to CheapFlight object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheapFlight {

    @JsonProperty(value="route")
    private String route;

    @JsonProperty(value="departure")
    private String departure;

    @JsonProperty(value="arrival")
    private String arrival;



}
