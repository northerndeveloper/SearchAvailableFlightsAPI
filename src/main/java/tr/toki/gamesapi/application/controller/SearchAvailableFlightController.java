package tr.toki.gamesapi.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import tr.toki.gamesapi.application.entity.Flight;
import tr.toki.gamesapi.application.mapper.BusinessFlight;
import tr.toki.gamesapi.application.mapper.CheapFlight;
import tr.toki.gamesapi.application.repository.BusinessFlightRepository;
import tr.toki.gamesapi.application.repository.CheapFlightRepository;
import tr.toki.gamesapi.application.repository.FlightRepository;

/**
 * Rest Controller method which gives all flights including cheap and business flight iterated, ordered and searched with the query
 */
@RestController
public class SearchAvailableFlightController {

    private BusinessFlightRepository businessFlightRepository;

    private CheapFlightRepository cheapFlightRepository;

    private FlightRepository flightRepository;

    public SearchAvailableFlightController(BusinessFlightRepository businessFlightRepository, CheapFlightRepository cheapFlightRepository, FlightRepository flightRepository) {
        this.businessFlightRepository = businessFlightRepository;
        this.cheapFlightRepository = cheapFlightRepository;
        this.flightRepository = flightRepository;
    }

    @GetMapping("/cheapflights")
    public Flux<CheapFlight> getCheapFlights() {
        return cheapFlightRepository.getCheapFlights();

    }

    @GetMapping("/businessflights")
    public Flux<BusinessFlight> getBusinessFlights() {
        return businessFlightRepository.getBusinessFlights();
    }

    @GetMapping("/allflights")
    @ResponseBody
    public Flux<Flight> getAllFlights(@RequestParam(name = "sortedBy", required = false, defaultValue = "depatureCity") String sortedBy,
        @RequestParam(name = "searchByParam", required = false) String searchByParam, @RequestParam(name = "searchByValue", required = false) String searchByValue,
        @RequestParam(name = "paginationPage", required = false) Long paginationPage, @RequestParam(name = "paginationCount", required = false) Long paginationCount) {
        return flightRepository.getAllFlights(sortedBy, searchByParam, searchByValue, paginationPage, paginationCount);
    }

}
