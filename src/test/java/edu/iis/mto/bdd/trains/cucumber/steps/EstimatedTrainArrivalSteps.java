package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.List;

import org.joda.time.LocalTime;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;

public class EstimatedTrainArrivalSteps {

    @Zakładając("^chcę się dostać z \"(.*)\" do \"(.*)\"$")
    public void givenStartPointAndEndPoint(String startCity, String endCity) {
        throw new PendingException();
    }

    @I("następny pociąg odjeżdża o (.*) na linii \"(.*)\"$")
    public void andGivenStartTimeAndStartLine(@Transform(JodaLocalTimeConverter.class) LocalTime startTime, 
            String startLine) {
        throw new PendingException();
    }
    
    @Gdy("^zapytam o godzinę przyjazdu$")
    public void whenIWantToTravel(){
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (.*)$")
    public void shouldGetEndTime(@Transform(JodaLocalTimeConverter.class) LocalTime endTime) {
        throw new PendingException();
    }
    
}
