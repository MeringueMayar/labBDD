package edu.iis.mto.bdd.trains.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;

import edu.iis.mto.bdd.trains.model.Line;

public class ItineraryService {

    TimetableService timetable;;

    public ItineraryService(TimetableService timetable) {
        super();
        this.timetable = timetable;
    }

    public List<LocalTime> findNextDepartures(Line line, String departureLocation, LocalTime departureTime,
            int numberOfNextDepartures) {
        List<LocalTime> nextDepartures = new ArrayList<>();
        int departures = 0;
        for (LocalTime time : timetable.findArrivalTimes(line, departureLocation)) {
            if (departures < numberOfNextDepartures) {
                if (time.isAfter(departureTime)) {
                    nextDepartures.add(time);
                    departures++;
                }
            }
        }
        return nextDepartures;
    }
}
