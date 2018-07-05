package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

import java.util.List;

public interface ItineraryService {
    List<LocalTime> findNextDepartures();

    void setDepartureTime(LocalTime departureTime);
}
