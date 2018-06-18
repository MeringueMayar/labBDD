package edu.iis.mto.bdd.trains.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;

import edu.iis.mto.bdd.trains.model.Line;

public class IntineraryService {
	
	TimetableService timeTableService;

	public IntineraryService(TimetableService timeTableService) {
		super();
		this.timeTableService = timeTableService;
	}
	
	public List<LocalTime> findNextDepartures(Line line, String departurePlace, LocalTime departureTime){
		List<LocalTime> departures=new ArrayList<>();
		
		for (LocalTime time : timeTableService.findArrivalTimes(line, departurePlace)) {
			if(time.isAfter(departureTime)) {
				departures.add(time);
			}
		}
		return departures;
	}

}
