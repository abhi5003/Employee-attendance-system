package com.abhi.swipemicroservices.dto;

import java.util.Date;

import com.abhi.swipemicroservices.model.Employee;

public class SwipeEventRequestDTO {

	private Employee employee;
	private String eventType;
	private Long timeStamp;

	// Constructors (default and parameterized)

	public SwipeEventRequestDTO() {
	}

	public SwipeEventRequestDTO(Employee employee, String eventType) {
		this.employee = employee;
		this.eventType = eventType;
		this.timeStamp = new Date().getTime();
	}

	// Getters and setters

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
