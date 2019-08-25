package tr.toki.gamesapi.application.controller;


import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import tr.toki.gamesapi.application.entity.Flight;
import tr.toki.gamesapi.application.mapper.BusinessFlight;
import tr.toki.gamesapi.application.mapper.CheapFlight;
import tr.toki.gamesapi.application.repository.BusinessFlightRepository;
import tr.toki.gamesapi.application.repository.CheapFlightRepository;
import tr.toki.gamesapi.application.repository.FlightRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchAvailableFlightControllerTest {

    @Autowired
    private SearchAvailableFlightController searchAvailableFlightController;

    @MockBean
    private CheapFlightRepository cheapFlightRepository;

    @MockBean
    private BusinessFlightRepository businessFlightRepository;

    @MockBean
    private FlightRepository flightRepository;

    @Before
    public void setUp() {

        CheapFlight cheapFlight = new CheapFlight();
        cheapFlight.setArrival("1558902656.000000000");
        cheapFlight.setDeparture("1558902656.000000000");
        cheapFlight.setRoute("Cruz del Eje-Antalya");

        BusinessFlight businessFlight = new BusinessFlight();
        businessFlight.setArrival("Istanbul");
        businessFlight.setDeparture("Ankara");
        businessFlight.setArrivalTime("1564410656.000000000");
        businessFlight.setDepartureTime("1561627856.000000000");

        Flight flight  = new Flight();
        flight.setArrivalCity("Istanbul");
        flight.setDepartureCity("Antalya");
        flight.setArrivalTime("1564410656.000000000");
        flight.setDepartureTime("1561627856.000000000");
        flight.setBusiness(true);

        Flight otherFlight = new Flight();
        otherFlight.setArrivalCity("Ankara");
        otherFlight.setDepartureCity("Zongulfak");
        otherFlight.setArrivalTime("15341210656.000000000");
        otherFlight.setDepartureTime("1591627856.000000000");
        otherFlight.setBusiness(false);

        Flux<CheapFlight> cheapFlightFlux = Flux.just(cheapFlight);
        Flux<BusinessFlight> businessFlightFlux = Flux.just(businessFlight);
        Flux<Flight> flightFlux = Flux.just(flight,otherFlight);

        Mockito.when(cheapFlightRepository.getCheapFlights()).thenReturn(cheapFlightFlux);
        Mockito.when(businessFlightRepository.getBusinessFlights()).thenReturn(businessFlightFlux);
        Mockito.when(flightRepository.getAllFlights("sortedBy","arrivalCity","Ankara",1L,1L)).thenReturn(flightFlux);
    }

    @Test
    public void getCheapFlights() {

        Flux<CheapFlight> cheapFlights = cheapFlightRepository.getCheapFlights();
        Iterable<CheapFlight> cheapFlightsIterable = cheapFlights.toIterable();
        CheapFlight cheapFlight = cheapFlightsIterable.iterator().next();
        assertEquals(cheapFlight.getArrival(), "1558902656.000000000");
        assertEquals(cheapFlight.getDeparture(),"1558902656.000000000" );
        assertEquals(cheapFlight.getRoute(), "Cruz del Eje-Antalya");
        assertNotNull(cheapFlight);
    }

    @Test
    public void getBusinessFlights() {

        Flux<BusinessFlight> businessFlights = businessFlightRepository.getBusinessFlights();
        Iterable<BusinessFlight> businessFlightIterable = businessFlights.toIterable();
        BusinessFlight businessFlight = businessFlightIterable.iterator().next();
        assertEquals(businessFlight.getArrival(), "Istanbul");
        assertEquals(businessFlight.getDeparture(),"Ankara" );
        assertEquals(businessFlight.getDepartureTime(), "1561627856.000000000");
        assertEquals(businessFlight.getArrivalTime(), "1564410656.000000000");
        assertNotNull(businessFlight);
    }



    @Test
    public void getAllFlights(){

        Flux<Flight> allFlights = flightRepository.getAllFlights("sortedBy","arrivalCity","Ankara",1L,1L);
        Iterable<Flight> flightIterable = allFlights.toIterable();
        Flight flight = flightIterable.iterator().next();
        assertEquals(flight.getArrivalCity(), "Istanbul");
        assertEquals(flight.getDepartureCity(), "Antalya");
        assertEquals(flight.getArrivalTime(), "1564410656.000000000");
        assertEquals(flight.getDepartureTime(), "1561627856.000000000");
    }



}
