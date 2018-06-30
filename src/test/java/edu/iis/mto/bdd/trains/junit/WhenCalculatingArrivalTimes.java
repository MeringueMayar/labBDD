package edu.iis.mto.bdd.trains.junit;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;

public class WhenCalculatingArrivalTimes {

    TimetableService timetable;
    ItineraryService itinerary;
    Line line;
    List<LocalTime> arrivalTimes;
    List<LocalTime> departureTimes;
    @Before
    public void setUp() {
        timetable = Mockito.mock(TimetableService.class);
        itinerary = new ItineraryService(timetable);
        arrivalTimes = new LinkedList<>(Arrays.asList(new LocalTime(8, 2), new LocalTime(8, 11), new LocalTime(8, 14)));
        departureTimes = new LinkedList<>(Arrays.asList(new LocalTime(7, 58), new LocalTime(8, 0), new LocalTime(8, 2), new LocalTime(8, 11), new LocalTime(8, 14), new LocalTime(8, 21)));
    }

    @Test
    public void ArriveExpectedTimesOnLineNamedWesternDeparturingFromParamattaToTownHall() {
        final int expectedNumberOfDepartures = 3;
        final int expectedNumberOfDeparturesLimitedByStartTime = 2;
        line = Line.named("Western").departingFrom("Emu Plains").withStations("Parramatta", "Town Hall");
        Mockito.when(timetable.findLinesThrough("Parramatta", "Town Hall"))
                .thenReturn(new LinkedList<>(Arrays.asList(line)));
        Mockito.when(timetable.findArrivalTimes(line, "Parramatta")).thenReturn(arrivalTimes);
        List<LocalTime> foundDepartureTimes;
        foundDepartureTimes = itinerary.findNextDepartures("Parramatta", "Town Hall");
        Assert.assertThat(foundDepartureTimes, Matchers.hasSize(expectedNumberOfDepartures));
        Assert.assertThat(foundDepartureTimes, Matchers.equalTo(arrivalTimes));
        foundDepartureTimes = itinerary.findNextDepartures("Parramatta", "Town Hall", new LocalTime(8, 5));
        Assert.assertThat(foundDepartureTimes, Matchers.hasSize(expectedNumberOfDeparturesLimitedByStartTime));
    }
}