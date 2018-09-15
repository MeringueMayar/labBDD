package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class ItineraryService {

    private final TimetableService timetableService;

    public ItineraryService(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        List<Line> linesPossible = timetableService.findLinesThrough(departure, destination);

        List<LocalTime> arrivalTimes = new ArrayList<>();
        List<LocalTime> upcomingArrivalTimes = new ArrayList<>();

        for (Line line : linesPossible) {
            arrivalTimes.addAll(timetableService.findArrivalTimes(line, destination));
            for (LocalTime arrival : arrivalTimes) {
                if (upcomingArrivalTimes.size() == 4) {
                    break;
                }

                if (arrival.isAfter(startTime) && arrival.isBefore(startTime.plusMinutes(30))) {
                    upcomingArrivalTimes.add(arrival);
                }
            }

        }
        return upcomingArrivalTimes;
    }

}
