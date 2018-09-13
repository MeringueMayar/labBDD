package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.ArrayList;
import java.util.List;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.hamcrest.Matchers;
import org.joda.time.LocalTime;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.junit.Assert;
import org.junit.Before;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class OptimalItinerarySteps {

    private List<LocalTime> arrivalTime;
    private IntineraryService itineraryService;
    private TimetableService timetableService;
    private Line line;

    @Before
    public void setUp() {
        timetableService = new InMemoryTimetableService();
        itineraryService = new IntineraryService(timetableService);
    }

    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
        this.line = Line.named(line).departingFrom(lineStart).withStations(departure, destination);
    }

    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        arrivalTime = itineraryService.findNextDepartures(departure, destination, startTime);
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {

        assertThat(arrivalTime, hasSize(expectedTrainTimes.size()));
        assertThat(arrivalTime, equalTo(expectedTrainTimes));
    }
}
