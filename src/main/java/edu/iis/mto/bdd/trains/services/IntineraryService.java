package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class IntineraryService {

    private final TimetableService timetableService;

    public IntineraryService(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        List<Line> linesPossible = timetableService.findLinesThrough(departure, destination);

        List<LocalTime> arrivalTimes = new ArrayList<>();

        for(Line line : linesPossible){
            arrivalTimes.addAll(timetableService.findArrivalTimes(line, destination));
        }

        List<LocalTime> foundedDepartures = findThreeClosestTimesAfter(arrivalTimes, startTime);

        return foundedDepartures;
    }

    private List<LocalTime> findThreeClosestTimesAfter(List<LocalTime> arrivalTimes, LocalTime startTime) {
        List<LocalTime> timesAfter = new ArrayList<>();

        for(LocalTime arrival : arrivalTimes){
            if(timesAfter.size() == 3) {
                break;
            }

            if(arrival.isAfter(startTime)){
                timesAfter.add(arrival);
            }
        }

        return timesAfter;
    }

}
