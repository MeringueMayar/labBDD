package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.List;
import org.joda.time.LocalTime;
import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Jeżeli;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;

public class ETAStep {

    @Zakładając("^chcę się dostać z \"(.*)\" do \"(.*)\"$")
    public void wantToGetFrom(String departurePlace, String arrivalPlace) {
        throw new PendingException();
    }

    @I("^następny pociąg odjeżdza o (.*) na linii \"(.*)\"$")
    public void andNextTheTrainArrivesAt(@Transform(JodaLocalTimeConverter.class) LocalTime startTime, String line) {
        throw new PendingException();
    }

    @Jeżeli("^zapytam o godzinę przyjazdu$")
    public void whenIAskAboutTheTimeOfArrival() {
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu:(.*)$")
    public void IShouldReceiveTheNextETA(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
        throw new PendingException();
    }
}
