package tr.toki.gamesapi.application.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import tr.toki.gamesapi.application.mapper.BusinessFlight;
import tr.toki.gamesapi.application.response.BusinessFlightResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Business Flight Repository class which is used to get only business flights by using Spring Web Flux
 */
@Repository
public class BusinessFlightRepository {

    /**
     * Fetches Business Flights from TokiGames WebService
     *
     * @return
     */
    public Flux<BusinessFlight> getBusinessFlights() {
        RestTemplate restTemplate = new RestTemplate();
        BusinessFlightResponse businessFlightsResponse =
            restTemplate.getForObject("https://tokigames-challenge.herokuapp.com/api/flights/business", BusinessFlightResponse.class);
        BusinessFlight[] businessFlights = businessFlightsResponse.getData();
        List<BusinessFlight> businessFlightList = Arrays.asList(businessFlights);
        return Flux.fromIterable(businessFlightList);
    }
}
