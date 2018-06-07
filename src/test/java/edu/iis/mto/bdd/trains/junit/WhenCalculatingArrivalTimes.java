package edu.iis.mto.bdd.trains.junit;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Before
    public void setUp() {
        timetable = Mockito.mock(TimetableService.class);
        itinerary = new ItineraryService(timetable);
    }

    @Test
    public void trainsOnLineWesternFromEmuPlainsDeparturingFromParamattaToTownHallAtGivenTimeShouldArriveAtExpectedTime() {
        int numberOfDepartures = 3;
        String departureLocation = "Parramatta";
        String destination = "Town Hall";
        LocalTime departureTime = new LocalTime("8:00");
        List<LocalTime> expectedDepartureTimes = new ArrayList<LocalTime>(
                Arrays.asList(new LocalTime("8:02"), new LocalTime("8:11"), new LocalTime("8:14")));
        line = Line.named("Western").departingFrom("Emu Plains").withStations("Emu Plains", "Parramatta", "Town Hall",
                "North Richmond");
        List<LocalTime> allDeparturesTimes = Arrays.asList(new LocalTime(7, 53), new LocalTime(7, 55),
                new LocalTime(7, 57), new LocalTime(8, 6), new LocalTime(8, 9), new LocalTime(8, 16));

        for (int i = 0; i < allDeparturesTimes.size(); i++) {
            allDeparturesTimes.set(i, allDeparturesTimes.get(i).plusMinutes(5));
        }
        Mockito.when(timetable.findLinesThrough(departureLocation, destination))
                .thenReturn(new ArrayList<Line>(Arrays.asList(line)));
        Mockito.when(timetable.findArrivalTimes(line, departureLocation)).thenReturn(allDeparturesTimes);

        List<LocalTime> foundDepartureTimes = new ArrayList<>();
        foundDepartureTimes = itinerary.findNextDepartures(departureLocation, destination, departureTime);

        Assert.assertThat(foundDepartureTimes, Matchers.hasSize(numberOfDepartures));
        Assert.assertThat(foundDepartureTimes, Matchers.equalTo(expectedDepartureTimes));
    }
}
