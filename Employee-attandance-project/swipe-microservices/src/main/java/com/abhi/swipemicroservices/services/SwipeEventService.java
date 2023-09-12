package com.abhi.swipemicroservices.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.abhi.swipemicroservices.dto.SwipeEventRequestDTO;
import com.abhi.swipemicroservices.model.Attendance;
import com.abhi.swipemicroservices.model.Status;
import com.abhi.swipemicroservices.model.SwipeEvent;

@Service
public class SwipeEventService {
	
	private static HashMap<Long, List<SwipeEvent>> eventCollection=new HashMap<>();

	public void processSwipeEvent(SwipeEventRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		
		Long empId=requestDTO.getEmployee().getEmployeeId();
		SwipeEvent swipeEvent=new SwipeEvent();
		
		swipeEvent.setEmployee(requestDTO.getEmployee());
		swipeEvent.setEventType(requestDTO.getEventType());
		swipeEvent.setTimestamp(requestDTO.getTimeStamp());
		
		if (eventCollection.containsKey(empId)) {
			List<SwipeEvent> swipeList= eventCollection.get(empId);
			swipeList.add(swipeEvent);
			eventCollection.put(empId, swipeList);
			
		}else {
			List<SwipeEvent> swipeList=new ArrayList<>();
			swipeList.add(swipeEvent);
			eventCollection.put(empId, swipeList);
		}
		
	}
	
	
	public List<Attendance> calculateAttendances(){
		
		List<Attendance> attendances=new ArrayList<>();
		
		for (Entry<Long, List<SwipeEvent>> entry : eventCollection.entrySet()) {
			Attendance attendance=new Attendance();
			
			
			List<SwipeEvent> swipeEvents=entry.getValue();
			SwipeEvent swipe_inEvent=swipeEvents.get(0);
			SwipeEvent swipe_outEvent=swipeEvents.get(swipeEvents.size() -1);
			
			if (swipe_inEvent.getEventType().equalsIgnoreCase("IN") && swipe_outEvent.getEventType().equalsIgnoreCase("OUT")) {
				
				if (swipe_inEvent.getTimestamp()!=null && swipe_outEvent.getTimestamp()!=null) {
					
					long totalHours=hoursCalculation(swipe_inEvent.getTimestamp(), swipe_outEvent.getTimestamp());
					attendance.setTotalHours(totalHours);
					attendance.setDate(swipe_inEvent.getTimestamp());
					
					if (totalHours>8) {
						attendance.setStatus(Status.PRESENT);
					}else if (totalHours>4 && totalHours<8) {
						attendance.setStatus(Status.HALF_DAY);
					}else {
						attendance.setStatus(Status.ABSENT);
					}
				}
				
			}
			
			if (swipe_inEvent.getEmployee().equals(swipe_outEvent.getEmployee())) {
				attendance.setEmployee(swipe_inEvent.getEmployee());
			}
			
			attendances.add(attendance);
			
		}
		
		return attendances;
		
	}
	
	
	private long hoursCalculation(Long startDate, Long endDate) {
		
		long milliseconds = endDate - startDate;
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        
        return hours;
	}

}
