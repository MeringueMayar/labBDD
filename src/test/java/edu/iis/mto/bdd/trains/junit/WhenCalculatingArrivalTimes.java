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
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;

public class WhenCalculatingArrivalTimes {

	TimetableService timeTableService;
	IntineraryService intineraryService;
	Line line;

	@Before
	public void setUp() {
		timeTableService = Mockito.mock(TimetableService.class);
		intineraryService = new IntineraryService(timeTableService);
	}

	@Test
	public void whenTravelingFromParmattaToTownHallWithSpecifiedDepartureTimeShouldReturnExpectedArrivalTimes() {
		List<LocalTime> arrivalTimes = new ArrayList<LocalTime>(
				Arrays.asList(new LocalTime(8, 2), new LocalTime(8, 11), new LocalTime(8, 14), new LocalTime(8, 21)));

		List<LocalTime> departureTimes = new ArrayList<LocalTime>(
				Arrays.asList(new LocalTime(7, 58), new LocalTime(8, 0), new LocalTime(8, 2), new LocalTime(8, 11),
						new LocalTime(8, 14), new LocalTime(8, 21)));

		Line line = Line.named("Western").departingFrom("Emu Plains").withStations("Parramatta", "Town Hall");
		Mockito.when(timeTableService.findLinesThrough("Parramatta", "Town Hall"))
				.thenReturn(new ArrayList<Line>(Arrays.asList(line)));
		Mockito.when(timeTableService.findArrivalTimes(line, "Parramatta")).thenReturn(arrivalTimes);
		List<LocalTime> result = intineraryService.findNextDepartures("Parramatta", "Town Hall", new LocalTime(8, 0));
		Assert.assertThat(result, Matchers.equalTo(arrivalTimes));

	}

}
