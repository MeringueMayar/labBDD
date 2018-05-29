package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.List;

import org.joda.time.LocalTime;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;

public class TrainArrivalTimeSteps {
    @Zakładając("^chce się dostać z \"(.*)\" do \"(.*)\"$")
    public void givenCities(String cityA, String cityB) {};
    @I("^następny pociag odjeżdża na linii \"(.*)\" o:$")
    public void andArrivingTrains(String line, String departure,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
        throw new PendingException();
    }
    @Gdy("^zapytam o godzine przyjazdu$")
    public void whenIAskAboutTime() {};

    @Wtedy("^powinienem uzyskać następujacy szacowany czas przyjazdu:$")
    public void shouldBeInformedAboutEstimatedArrivalTime(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
        throw new PendingException();
    }
}
