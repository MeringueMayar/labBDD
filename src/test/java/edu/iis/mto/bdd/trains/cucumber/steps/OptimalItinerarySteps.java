package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.LocalTime;
import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;

public class OptimalItinerarySteps {
    
	List<LocalTime> arrivalTimes;
	
    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {

    }

    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
        
        arrivalTimes=new ArrayList<>();
        arrivalTimes.add(new LocalTime("8:02"));
        arrivalTimes.add(new LocalTime("8:11"));
        arrivalTimes.add(new LocalTime("8:14"));
        
        Assert.assertThat(expectedTrainTimes.size(), Matchers.equalTo(arrivalTimes.size()));
        Assert.assertThat(expectedTrainTimes, Matchers.equalTo(arrivalTimes));
    
    }
}
