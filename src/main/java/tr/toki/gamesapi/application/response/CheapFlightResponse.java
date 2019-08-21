package tr.toki.gamesapi.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tr.toki.gamesapi.application.mapper.CheapFlight;

/**
 * Cheap Flight Response class
 */
@Data
public class CheapFlightResponse {

    @JsonProperty
    private CheapFlight[] data;

}
