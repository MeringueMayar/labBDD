package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.joda.time.LocalTime;

public class ArrivalTimeSteps {
    @Zakładając("^chcę się dostać z (.*) do (.*)$")
    public void givenIWantToTravel(String origin, String destination) {
        throw new PendingException();
    }


    @I("^następny pociąg odjeżdża o (.*) na linii (.*)$")
    public void andNextTrainLeavesAt(@Transform(JodaLocalTimeConverter.class) LocalTime departure, String line) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void whenIAskAboutArrivalTime() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (.*)$")
    public void shouldGetEstimatedArrivalTime(@Transform(JodaLocalTimeConverter.class) LocalTime arrivalTime) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
