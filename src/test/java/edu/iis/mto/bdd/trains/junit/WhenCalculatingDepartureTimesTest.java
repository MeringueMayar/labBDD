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
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;
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

    @Before
    public void setUp() {
        timetableService = mock(InMemoryTimetableService.class);
        itineraryService = new BasicItineraryService(timetableService);
        Mockito.when(timetableService.findLinesThrough(anyString(), anyString())).thenReturn(Arrays.asList(testLine));
    }

    @Test
    public void findNextDeparturesShouldReturnListOfLocalTimes() {
        assertThat(itineraryService.findNextDepartures("Start", "Destination"), isA(List.class));
        assertThat(itineraryService.findNextDepartures("Start", "Destination"), everyItem(isA(LocalTime.class)));
    }

    @Test
    public void findNextDeparturesShouldReturnCorrectTimeValues() {
        List<LocalTime> times = Arrays.asList(new LocalTime(6, 30), new LocalTime(7, 0),
                new LocalTime(7, 10), new LocalTime(7, 20), new LocalTime(7, 30));
        Mockito.when(timetableService.findArrivalTimes(eq(testLine), anyString())).thenReturn(times);
        assertThat(itineraryService.findNextDepartures("Start", "Destination"), contains(times.toArray()));

    }
}
