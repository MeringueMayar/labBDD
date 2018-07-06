package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

import java.util.List;

public interface ItineraryService {
    int MAX_MINUTES_AFTER_DEPARTURE = 30;

    List<LocalTime> findNextDepartures(String departure, String destination, LocalTime localTime);
}
