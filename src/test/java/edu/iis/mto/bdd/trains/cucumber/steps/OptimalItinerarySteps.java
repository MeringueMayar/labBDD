package edu.iis.mto.bdd.trains.cucumber.steps;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.LocalTime;
import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.Before;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;

public class OptimalItinerarySteps {

    IntineraryService intineraryService;
    TimetableService timetableService;
    Line line;
    List<LocalTime> arrivalTimes;
    
    @Before
    public void setUp() {
        timetableService = new InMemoryTimetableService();
        intineraryService = new IntineraryService(timetableService);
    }
    
    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
        this.line = Line.named(line).departingFrom(lineStart).withStations(departure, destination);
    }  
    
    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        arrivalTimes = intineraryService.findNextDepartures(departure, destination, startTime);
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
        assertThat(arrivalTimes, hasSize(expectedTrainTimes.size()));
        assertThat(arrivalTimes, equalTo(expectedTrainTimes));
    }
    
}
