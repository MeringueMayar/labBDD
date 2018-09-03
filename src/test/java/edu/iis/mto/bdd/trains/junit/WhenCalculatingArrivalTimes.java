package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WhenCalculatingArrivalTimes {

    @Mock
    TimetableService timetableService;

    private IntineraryService intineraryService;
    private List<LocalTime> foundedDepartures;
    private List<LocalTime> expectedDepartureTimes;

    @Before
    public void setUp(){
        String departure = "A";
        String destination = "B";

        intineraryService = new IntineraryService(timetableService);

        List<Line> linesPossible = new ArrayList<>();
        Line line1 = Line.named("line 1").departingFrom("Q").withStations(departure, destination);
        linesPossible.add(line1);
        when(timetableService.findLinesThrough(departure, destination)).thenReturn(linesPossible);
        List<LocalTime> arrivalsOfLine1 = new ArrayList<>();
        arrivalsOfLine1.add(new LocalTime(7, 45));
        arrivalsOfLine1.add(new LocalTime(7, 59));
        arrivalsOfLine1.add(new LocalTime(8, 00));
        arrivalsOfLine1.add(new LocalTime(8, 2));
        arrivalsOfLine1.add(new LocalTime(8, 11));
        arrivalsOfLine1.add(new LocalTime(8, 14));
        arrivalsOfLine1.add(new LocalTime(8, 25));
        arrivalsOfLine1.add(new LocalTime(8, 43));
        when(timetableService.findArrivalTimes(line1, destination)).thenReturn(arrivalsOfLine1);

        expectedDepartureTimes = new ArrayList<>();
        expectedDepartureTimes.add(new LocalTime(8, 2));
        expectedDepartureTimes.add(new LocalTime(8, 11));
        expectedDepartureTimes.add(new LocalTime(8, 14));

        foundedDepartures = intineraryService.findNextDepartures(departure, destination, new LocalTime(8, 0));
    }

    @Test
    public void findNextDepartureReturnsNotMoreThanThreeElements() {
        assertThat(foundedDepartures.size(), is(3));
    }

    @Test
    public void findNextDepartureReturnsAppropriateList() {
        assertThat(foundedDepartures, is(expectedDepartureTimes));
    }

}
