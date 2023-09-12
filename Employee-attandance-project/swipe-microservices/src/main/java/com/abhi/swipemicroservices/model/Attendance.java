package com.abhi.swipemicroservices.model;

import java.util.Date;

public class Attendance {

	private Long attendanceId;

	private Employee employee;

	private Long date;

	private long totalHours;

	private Status status;
	
	// Getters and setters

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(long totalHours) {
		this.totalHours = totalHours;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
