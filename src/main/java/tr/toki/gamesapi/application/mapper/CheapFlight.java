package tr.toki.gamesapi.application.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String departure; //TODO change format

    @JsonProperty(value="arrival")
    private String arrival; //TODO change format


}
