package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.joda.time.LocalTime;

import java.util.List;

public class ArriveTimeToDestinationStation {
    @Zakładając("^chcę się dostać z ([^\"]*) do ([^\"]*)$")
    public void givenTravel(String departure, String destination) {
        throw new PendingException();
    }
    @I("^następny pociąg odjeżdża o ([^\"]*) na linii ([^\"]*)$")
    public void andNextTrain(String departureTime, String lineName) {
        throw new PendingException();
    }
    @Gdy("^zapytam o godzinę przyjazdu$")
    public void whenIAskAboutArriveTime() {
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: ([^\"]*)$")
    public void shouldBeInformedAboutArriveTime(@Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        throw new PendingException();
    }
}
