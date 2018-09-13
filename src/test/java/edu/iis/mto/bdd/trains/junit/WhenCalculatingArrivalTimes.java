package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.hamcrest.Matchers;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class WhenCalculatingArrivalTimes {

    TimetableService timetableService;
    IntineraryService itineraryService;
    Line line;

    @Before
    public void setUp() {

        timetableService = Mockito.mock(TimetableService.class);
        itineraryService = new IntineraryService(timetableService);
    }

    @Test
    public void shouldGetCorrectTrainArrivingTimesWhenWantToTravel() {

        List<LocalTime> arrivalTime = new ArrayList<>();
        arrivalTime.add(new LocalTime(8, 2));
        arrivalTime.add(new LocalTime(8, 11));
        arrivalTime.add(new LocalTime(8, 14));
        arrivalTime.add(new LocalTime(8, 21));
        List<LocalTime> departureTime= new ArrayList<>();
        departureTime.add(new LocalTime(7, 58));
        departureTime.add(new LocalTime(8, 0));
        departureTime.add(new LocalTime(8, 2));
        departureTime.add(new LocalTime(8, 11));
        departureTime.add(new LocalTime(8, 14));
        departureTime.add(new LocalTime(8, 21));
        Line line = Line.named("Western").departingFrom("Emu Plains").withStations("Parramatta", "Town Hall");
        Mockito.when(timetableService.findLinesThrough("Parramatta", "Town Hall"))
                .thenReturn(new ArrayList<>(Arrays.asList(line)));
        Mockito.when(timetableService.findArrivalTimes(line, "Parramatta")).thenReturn(arrivalTime);
        List<LocalTime> result = itineraryService.findNextDepartures("Parramatta", "Town Hall", new LocalTime(8, 0));
        assertThat(result, hasSize(3));
    }
}
