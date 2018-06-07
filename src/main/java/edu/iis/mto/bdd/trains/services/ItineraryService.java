package edu.iis.mto.bdd.trains.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;

import edu.iis.mto.bdd.trains.model.Line;

public class ItineraryService {

    private TimetableService timetable;
    private int numberOfNextDepartures;

    public ItineraryService(TimetableService timetable) {
        super();
        this.timetable = timetable;
        numberOfNextDepartures = 3;
    }

    public ItineraryService(TimetableService timetable, int numberOfNextDepartures) {
        super();
        this.timetable = timetable;
        this.numberOfNextDepartures = numberOfNextDepartures;
    }

    public List<LocalTime> findNextDepartures(Line line, String departureLocation, LocalTime departureTime) {
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

    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        List<LocalTime> nextDepartures = new ArrayList<>();
        int departures = 0;
        Line line = timetable.findLinesThrough(departure, destination).get(0);
        for (LocalTime time : timetable.findArrivalTimes(line, departure)) {
            if (departures < numberOfNextDepartures) {
                if (time.isAfter(startTime)) {
                    nextDepartures.add(time);
                    departures++;
                }
            }
        }
        return nextDepartures;
    }
}
