package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.services.BasicItineraryService;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class WhenCalculatingDepartureTimesTest {
    private ItineraryService itineraryService;

    @Before
    public void setUp() {
        itineraryService = new BasicItineraryService(mock(InMemoryTimetableService.class));
    }

    @Test
    public void findNextDeparturesShouldReturnListOfLocalTimes() {
        assertThat(itineraryService.findNextDepartures(), isA(List.class));
        assertThat(itineraryService.findNextDepartures(), everyItem(isA(LocalTime.class)));
    }
}
