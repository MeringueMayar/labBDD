package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;

import java.util.List;
import java.util.stream.Collectors;

public class BasicItineraryService implements ItineraryService {
    public static final int MAX_MINUTES_AFTER_DEPARTURE = 30;
    private TimetableService timetableService;

    public BasicItineraryService(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @Override
    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime departureTime) {
        Line line = timetableService.findLinesThrough(departure, destination).get(0);
        return timetableService.findArrivalTimes(line, departure).stream()
                .filter(time -> time.isAfter(departureTime) && time.isBefore(departureTime.plusMinutes(MAX_MINUTES_AFTER_DEPARTURE)))
                .collect(Collectors.toList());
    }
}
