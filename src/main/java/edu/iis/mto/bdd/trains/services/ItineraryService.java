package edu.iis.mto.bdd.trains.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;

import edu.iis.mto.bdd.trains.model.Line;

public class ItineraryService {

    private TimetableService timetable;

    public ItineraryService(TimetableService timetable) {
        super();
        this.timetable = timetable;
    }

    public List<LocalTime> findNextDepartures(Line line, String departureLocation, LocalTime departureTime) {
        List<LocalTime> nextDepartures = new ArrayList<>();
        for (LocalTime time : timetable.findArrivalTimes(line, departureLocation)) {
            if (time.isAfter(departureTime)) {
                nextDepartures.add(time);
            }
        }
        return nextDepartures;
    }

    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime,
            int minutesOfNextDepartures) {
        List<LocalTime> nextDepartures = new ArrayList<>();
        Line line = timetable.findLinesThrough(departure, destination).get(0);
        for (LocalTime time : timetable.findArrivalTimes(line, departure)) {
            if (time.isAfter(startTime) && time.isBefore(startTime.plusMinutes(minutesOfNextDepartures))) {
                nextDepartures.add(time);
            }

        }
        return nextDepartures;
    }
}
