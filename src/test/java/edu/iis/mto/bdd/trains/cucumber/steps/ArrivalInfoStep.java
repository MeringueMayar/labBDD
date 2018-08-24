package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;

public class ArrivalInfoStep {

    @Zakładając("^chcę się dostać z \"([^\"]*)\" do \"([^\"]*)\"$")
    public void givenStartAndDestination(String startPoint, String destination) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @I("^następny pociąg odjeżdża o (\\d+):(\\d+) na linii \"([^\"]*)\"$")
    public void następnyPociągOdjeżdżaONaLinii(int nextTrainHour, int nextTrainMin, String line) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void zapytamOGodzinęPrzyjazdu() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (\\d+):(\\d+)$")
    public void powinienemUzyskaćNastępującySzacowanyCzasPrzyjazdu(int destinationHour, int destinationMin) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}