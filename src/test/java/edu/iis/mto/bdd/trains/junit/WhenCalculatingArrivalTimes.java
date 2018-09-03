package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.services.IntineraryService;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WhenCalculatingArrivalTimes {

    private IntineraryService intineraryService;
    private List<LocalTime> foundedDepartures;
    private List<LocalTime> expectedDepartureTimes;

    @Before
    public void setUp(){
        intineraryService = new IntineraryService();


        expectedDepartureTimes = new ArrayList<>();
        expectedDepartureTimes.add(new LocalTime(8, 2));
        expectedDepartureTimes.add(new LocalTime(8, 11));
        expectedDepartureTimes.add(new LocalTime(8, 14));

        foundedDepartures = intineraryService.findNextDepartures("s", "s", LocalTime.MIDNIGHT);
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
