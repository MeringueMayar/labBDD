package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;

import java.util.LinkedList;
import java.util.List;

public class ItineraryService {
    private TimetableService timetable;

    public ItineraryService(TimetableService timetable) {
        this.timetable = timetable;
    }
    public List<LocalTime> findNextDepartures(String departure, String destination) {
        List<LocalTime> timeOfDepartures = new LinkedList<>();
        Line line = timetable.findLinesThrough(departure, destination).get(0);
        for (LocalTime time : timetable.findArrivalTimes(line, departure)) {
            timeOfDepartures.add(time);
        }
        return timeOfDepartures;
    }
}
