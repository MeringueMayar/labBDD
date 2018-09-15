package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.List;

import org.joda.time.LocalTime;

import cucumber.api.Transform;
import cucumber.api.java.pl.Jeżeli;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OptimalItinerarySteps {

    private ItineraryService itineraryService;
    Line line;
    String departureStation;
    LocalTime departureTime;
    private String departure;
    private String destination;
    private LocalTime startTime;
    private List<LocalTime> foundDepartures;

    
    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
        itineraryService = new ItineraryService(new InMemoryTimetableService());

    }

    @Jeżeli("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        this.departure = departure;
        this.destination = destination;
        this.startTime = startTime;
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
        foundDepartures = itineraryService.findNextDepartures(departure, destination, startTime);
        assertThat(foundDepartures, is(expectedTrainTimes));
    }
}
