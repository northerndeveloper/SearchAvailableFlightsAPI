package tr.toki.gamesapi.application.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import tr.toki.gamesapi.application.mapper.CheapFlight;
import tr.toki.gamesapi.application.response.CheapFlightResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Cheap Flight Repository class which is used to get only cheap flights by using Spring Web Flux
 */
@Repository
public class CheapFlightRepository {

    /**
     * Fetches Cheap Flights from TokiGames WebService
     *
     * @return
     */
    public Flux<CheapFlight> getCheapFlights() {

        RestTemplate restTemplate = new RestTemplate();
        CheapFlightResponse cheapFlightRepository =
            restTemplate.getForObject("https://tokigames-challenge.herokuapp.com/api/flights/cheap", CheapFlightResponse.class);
        CheapFlight[] cheapFlights = cheapFlightRepository.getData();
        List<CheapFlight> cheapFlightList = Arrays.asList(cheapFlights);
        return Flux.fromIterable(cheapFlightList);
    }
}   
