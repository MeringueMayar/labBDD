package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.lessThan;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WhenCalculatingArrivalTimes {

    @Mock
    TimetableService timetableService;

    private List<LocalTime> foundDepartures;
    private List<LocalTime> expectedDepartureTimes;

    @Before
    public void setUp() {
        String departure = "DepartureStation";
        String destination = "DestinationStation";

        ItineraryService intineraryService = new ItineraryService(timetableService);

        Line line1 = Line.named("line 1").departingFrom("Station1").withStations(departure, destination);
        Line line2 = Line.named("line 2").departingFrom("Station2").withStations(departure, destination);

        List<Line> linesPossible = new ArrayList<>();
        linesPossible.add(line1);
        
        when(timetableService.findLinesThrough(departure, destination)).thenReturn(linesPossible);

        List<LocalTime> arrivalsOfLine1 = new ArrayList<>(Arrays.asList(
                new LocalTime("7:58"),
                new LocalTime("8:00"),
                new LocalTime("8:02"),
                new LocalTime("8:11"),
                new LocalTime("8:14")));

        when(timetableService.findArrivalTimes(line1, destination)).thenReturn(arrivalsOfLine1);

        expectedDepartureTimes = new ArrayList<>(Arrays.asList(
                new LocalTime("8:02"),
                new LocalTime("8:11"),
                new LocalTime("8:14")));

        foundDepartures = intineraryService.findNextDepartures(departure, destination, new LocalTime("8:00"));

    }

    @Test
    public void findNextDepartureReturnsNotMoreThanThreeElements() {
        assertThat(foundDepartures.size(), lessThan(4));
    }

    @Test
    public void findNextDepartureReturnsExpectedDepartureTimes() {
        assertThat(foundDepartures, is(expectedDepartureTimes));
    }

}
