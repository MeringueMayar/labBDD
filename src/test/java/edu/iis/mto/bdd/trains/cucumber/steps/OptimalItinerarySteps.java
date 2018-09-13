package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.LocalTime;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.junit.Assert;

public class OptimalItinerarySteps {

    List<LocalTime> arrivalTime;

    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
        throw new PendingException();

    }

    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {

        arrivalTime = new ArrayList<>();
        arrivalTime.add(new LocalTime("8:02"));
        arrivalTime.add(new LocalTime("8:11"));
        arrivalTime.add(new LocalTime("8:14"));
        Assert.assertThat(expectedTrainTimes.size(), Matchers.equalTo(arrivalTime.size()));
        Assert.assertThat(expectedTrainTimes, Matchers.equalTo(arrivalTime));
    }
}
