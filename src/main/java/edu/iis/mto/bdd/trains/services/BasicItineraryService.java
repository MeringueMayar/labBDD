package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

import java.util.List;

public class BasicItineraryService implements ItineraryService {
    public BasicItineraryService(TimetableService timetableService) {
    }

    @Override
    public List<LocalTime> findNextDepartures() {
        return null;
    }

    @Override
    public void setDepartureTime(LocalTime departureTime) {

    }
}
