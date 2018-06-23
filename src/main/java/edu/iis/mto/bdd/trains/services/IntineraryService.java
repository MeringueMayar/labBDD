package edu.iis.mto.bdd.trains.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;

import edu.iis.mto.bdd.trains.model.Line;

public class IntineraryService {

    private TimetableService timetableService;
    
    public IntineraryService(TimetableService timetableService) {
        this.timetableService = timetableService;
    }
    
    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        List<LocalTime> arrivalTimes = new ArrayList<>();
        List<Line> lines = timetableService.findLinesThrough(departure, destination);
        for (Line line : lines) {
            arrivalTimes.addAll(timetableService.findArrivalTimes(line, departure));
        }
        return arrivalTimes;
    }
    
}
