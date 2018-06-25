package edu.iis.mto.bdd.trains.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;

import edu.iis.mto.bdd.trains.model.Line;

public class IntineraryService {
	
	private TimetableService timeTableService;

	public IntineraryService(TimetableService timeTableService) {
		super();
		this.timeTableService = timeTableService;
	}
	
	public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        List<LocalTime> arrivalTimes = new ArrayList<>();
        List<Line> lines = timeTableService.findLinesThrough(departure, destination);
        for (Line line : lines) {
            List<LocalTime> foundArrivalTimes = timeTableService.findArrivalTimes(line, departure);
            for (LocalTime foundArrivalTime : foundArrivalTimes) {
                if (foundArrivalTime.isAfter(startTime) && foundArrivalTime.isBefore(startTime.plusMinutes(30))) {
                    arrivalTimes.add(foundArrivalTime);
                }
            }
        }
        return arrivalTimes;
    }

}
