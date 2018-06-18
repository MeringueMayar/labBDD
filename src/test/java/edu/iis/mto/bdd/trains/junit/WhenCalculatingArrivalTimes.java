package edu.iis.mto.bdd.trains.junit;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import cucumber.api.java.Before;
import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;

public class WhenCalculatingArrivalTimes {
	
	TimetableService timeTableService;
	IntineraryService intineraryService;
	Line line;
	
	
	@Before
	public void setUp() {
		timeTableService=Mockito.mock(TimetableService.class);
		intineraryService=new IntineraryService(timeTableService);
	}
	
	@Test
	public void whenTravelingFromParmattaToTownHallWithSpecifiedDepartureTimeShouldReturnExpectedArrivalTimes() {
		int departures=4;
		String departurePlace="Parmatta";
		LocalTime givenTime=new LocalTime("8:00");
		
		List<LocalTime> expectedDepartureTimes=new ArrayList<>(Arrays.asList(new LocalTime("8:02"), new LocalTime("8:11"), new LocalTime("8:14"), new LocalTime("8:21")));
		
		line = Line.named("Western").departingFrom("Emu Plains").withStations("Emu Plains", "Parmatta", "Town Hall");
		
		List<LocalTime> departureTimes=new ArrayList<>(Arrays.asList(new LocalTime("7:58"), new LocalTime("8:00"), new LocalTime("8:02"), new LocalTime("8:11"), new LocalTime("8:14"), new LocalTime("8:21")));
		
		when(timeTableService.findArrivalTimes(line, departurePlace)).thenReturn(expectedDepartureTimes);
		
		List<LocalTime> foundedDepartureTimes=new ArrayList<>();
		foundedDepartureTimes = intineraryService.findNextDepartures(line, departurePlace, new LocalTime("8:00"));
		
		Assert.assertThat(foundedDepartureTimes.size(), Matchers.equalTo(departures));
		Assert.assertThat(foundedDepartureTimes, Matchers.equalTo(expectedDepartureTimes));
		
		
	}

	
}
