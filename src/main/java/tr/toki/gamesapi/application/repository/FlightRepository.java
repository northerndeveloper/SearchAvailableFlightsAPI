package tr.toki.gamesapi.application.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import tr.toki.gamesapi.application.entity.Flight;
import tr.toki.gamesapi.application.mapper.BusinessFlight;
import tr.toki.gamesapi.application.mapper.CheapFlight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Flight Repository class which is used to get flights and make operations on them
 */
@Repository
public class FlightRepository {

    private BusinessFlightRepository businessFlightRepository;

    private CheapFlightRepository cheapFlightRepository;

    private List<Flight> flightList;

    public FlightRepository(BusinessFlightRepository businessFlightRepository, CheapFlightRepository cheapFlightRepository) {
        this.businessFlightRepository = businessFlightRepository;
        this.cheapFlightRepository = cheapFlightRepository;
    }

    /**
     * Fetches all flights by parameters from webservices. Sorts, seacrchs and paginate the solutions
     * @param sortedBy
     * @param searchByParam
     * @param searchByValue
     * @param paginationPage
     * @param paginationCount
     * @return
     */
    public Flux<Flight> getAllFlights(String sortedBy, String searchByParam, String searchByValue, Long paginationPage, Long paginationCount) {

        flightList = new ArrayList<>();
        Flux cheapFlights = cheapFlightRepository.getCheapFlights();
        Flux businessFlights = businessFlightRepository.getBusinessFlights();

        aggregateCheapFlights(cheapFlights);

        aggreagateBusinessFlights(businessFlights);

        applyPagination(paginationPage, paginationCount);

        applySearch(searchByParam, searchByValue);

        applySort(sortedBy);

        return Flux.fromIterable(flightList);
    }

    private List<Flight> applySort(String sortedBy) {
        if (!StringUtils.isEmpty(sortedBy)) {
            if (sortedBy.equals("departureCity")) {
                flightList = flightList.stream()
                    .sorted(Comparator.comparing(Flight::getDepartureCity))
                    .collect(Collectors.toList());
            } else if (sortedBy.equals("arrivalCity")) {
                flightList = flightList.stream()
                    .sorted(Comparator.comparing(Flight::getArrivalCity))
                    .collect(Collectors.toList());
            } else if (sortedBy.equals("departureTime")) {
                flightList = flightList.stream()
                    .sorted(Comparator.comparing(Flight::getDepartureTime))
                    .collect(Collectors.toList());
            } else if (sortedBy.equals("arrivalTime")) {
                flightList = flightList.stream()
                    .sorted(Comparator.comparing(Flight::getArrivalTime))
                    .collect(Collectors.toList());
            }
        }
        return flightList;
    }

    private List<Flight> applySearch(String searchByParam, String searchByValue) {
        if (!StringUtils.isEmpty(searchByParam) && !StringUtils.isEmpty(searchByValue)) {
            if (searchByParam.equals("departureCity")) {
                flightList = flightList.stream().filter(str -> str.getDepartureCity().contains(searchByValue)).collect(Collectors.toList());
            } else if (searchByParam.equals("arrivalCity")) {
                flightList = flightList.stream().filter(str -> str.getArrivalCity().contains(searchByValue)).collect(Collectors.toList());
            } else if (searchByParam.equals("departureTime")) {
                flightList = flightList.stream().filter(str -> str.getDepartureTime().contains(searchByValue)).collect(Collectors.toList());
            } else if (searchByParam.equals("arrivalTime")) {
                flightList = flightList.stream().filter(str -> str.getArrivalTime().contains(searchByValue)).collect(Collectors.toList());
            }
        }
        return flightList;
    }

    private List<Flight> applyPagination(Long paginationPage, Long paginationCount) {
        if (paginationCount != null && paginationCount != 0 && paginationPage != null && paginationPage != 0) {
            flightList = flightList.stream().skip((paginationPage - 1) * paginationCount).limit(paginationCount).collect(Collectors.toList());
        }
        return flightList;
    }

    private void aggreagateBusinessFlights(Flux businessFlights) {
        Flight flight;
        Iterable<BusinessFlight> businessFlightIterable = businessFlights.toIterable();
        for (BusinessFlight businessFlight : businessFlightIterable) {
            flight = new Flight();
            flight.setDepartureCity(businessFlight.getDeparture());
            flight.setArrivalCity(businessFlight.getArrival());
            flight.setDepartureTime(businessFlight.getDepartureTime());
            flight.setArrivalTime(businessFlight.getArrivalTime());
            flight.setBusiness(true);
            flightList.add(flight);
        }
    }

    private void aggregateCheapFlights(Flux cheapFlights) {
        Flight flight;
        Iterable<CheapFlight> cheapFlightsIterable = cheapFlights.toIterable();
        for (CheapFlight cheapFlight : cheapFlightsIterable) {
            flight = new Flight();
            String[] cities = cheapFlight.getRoute().split("-");
            flight.setDepartureCity(cities[0]);
            flight.setArrivalCity(cities[1]);
            flight.setDepartureTime(cheapFlight.getDeparture());
            flight.setArrivalTime(cheapFlight.getArrival());
            flight.setBusiness(false);
            flightList.add(flight);
        }
    }
}   
