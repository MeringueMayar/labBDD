package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.BasicItineraryService;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

public class WhenCalculatingDepartureTimesTest {
    private ItineraryService itineraryService;
    private TimetableService timetableService;

    private Line testLine = new Line.LineBuilder("Test").departingFrom("Start").withStations("Start", "Through", "Destination");
    List<LocalTime> times = Arrays.asList(new LocalTime(6, 30), new LocalTime(7, 0),
            new LocalTime(7, 10), new LocalTime(7, 20), new LocalTime(7, 30));

    @Before
    public void setUp() {
        timetableService = mock(InMemoryTimetableService.class);
        itineraryService = new BasicItineraryService(timetableService);
        Mockito.when(timetableService.findLinesThrough(anyString(), anyString())).thenReturn(Arrays.asList(testLine));
    }

    @Test
    public void findNextDeparturesShouldReturnListOfLocalTimes() {
        assertThat(itineraryService.findNextDepartures("Start", "Destination", times.get(0)),
                isA(List.class));
        assertThat(itineraryService.findNextDepartures("Start", "Destination", times.get(0)),
                everyItem(isA(LocalTime.class)));
    }

    @Test
    public void findNextDeparturesShouldReturnCorrectTimeValues() {
        Mockito.when(timetableService.findArrivalTimes(eq(testLine), anyString())).thenReturn(times);
        assertThat(itineraryService.findNextDepartures("Start", "Destination", times.get(0)),
                contains(times.toArray()));
    }


    @Test
    public void findNextDeparturesShouldReturnOnlyTimeValuesAfterDepartureTime() {
        Mockito.when(timetableService.findArrivalTimes(eq(testLine), anyString())).thenReturn(times);

        assertThat(itineraryService.findNextDepartures("Start", "Destination", new LocalTime(6,50)),
                not(hasItem(times.get(0))));
    }

    @Test
    public void findNextDeparturesShouldNotReturnValuesAfterSpecificTimeFrame() {
        Mockito.when(timetableService.findArrivalTimes(eq(testLine), anyString())).thenReturn(times);
        LocalTime departure = new LocalTime(6, 50);

        assertThat(itineraryService.findNextDepartures("Start", "Destination", departure),
                not(hasItem(greaterThan(departure.plusMinutes(15)))));
    }
}
