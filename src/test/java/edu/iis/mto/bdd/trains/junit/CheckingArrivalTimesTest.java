package edu.iis.mto.bdd.trains.junit;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;

public class CheckingArrivalTimesTest {

    TimetableService timetableService;
    ItineraryService itineraryService;

    @Before
    public void setUp() {
        timetableService = mock(TimetableService.class);
        itineraryService = new ItineraryService(timetableService);
    }

    @Test
    public void shouldGetCorrectTrainArrivingTimesWhenIWantToTravel() {
        List<LocalTime> arrivalTimes = new ArrayList<>();
        arrivalTimes.add(new LocalTime(8, 2));
        arrivalTimes.add(new LocalTime(8, 11));
        arrivalTimes.add(new LocalTime(8, 14));
        arrivalTimes.add(new LocalTime(8, 21));
        List<LocalTime> departureTimes = new ArrayList<>();
        departureTimes.add(new LocalTime(7, 58));
        departureTimes.add(new LocalTime(8, 0));
        departureTimes.add(new LocalTime(8, 2));
        departureTimes.add(new LocalTime(8, 11));
        departureTimes.add(new LocalTime(8, 14));
        departureTimes.add(new LocalTime(8, 21));
        Line line = Line.named("Western").departingFrom("Emu Plains").withStations("Parramatta", "Town Hall");
        Mockito.when(timetableService.findLinesThrough("Parramatta", "Town Hall"))
                .thenReturn(new ArrayList<>(Arrays.asList(line)));
        Mockito.when(timetableService.findArrivalTimes(line, "Parramatta")).thenReturn(arrivalTimes);
        List<LocalTime> result = itineraryService.findNextDepartures("Parramatta", "Town Hall", new LocalTime(8, 0));
        assertThat(result, hasSize(4));
    }

}
