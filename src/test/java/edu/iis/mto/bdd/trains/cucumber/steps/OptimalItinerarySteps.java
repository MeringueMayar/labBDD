package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.LocalTime;
import org.junit.Assert;

import cucumber.api.Transform;
import cucumber.api.java.Before;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;

public class OptimalItinerarySteps {
    
	TimetableService timeTableService;
    IntineraryService itineraryService;
    Line line;
	List<LocalTime> arrivalTimes;
	
	@Before 
	public void setUp() {
		timeTableService=new InMemoryTimetableService();
		itineraryService=new IntineraryService(timeTableService);
	}
    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String lineName, String lineStart, String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
    	line=Line.named(lineName).departingFrom(lineStart).withStations(lineStart, departure, destination);
    }

    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
    	arrivalTimes=itineraryService.findNextDepartures(departure, departure, startTime);
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
       
        Assert.assertThat(expectedTrainTimes.size(), Matchers.equalTo(arrivalTimes.size()));
        Assert.assertThat(expectedTrainTimes, Matchers.equalTo(arrivalTimes));
    
    }
}
