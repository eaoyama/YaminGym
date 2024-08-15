package com.example.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Batch {

	//fields
	//access      datatype          nameOfField
	private Integer batchId;
	private String batchName;
	private DayOfWeek dayOfClass;
	private LocalTime startHour;
	private List<Participant> participants; //[Participant1, Participant2]
	
	//constructor
	public Batch() {
		super();
        this.participants = new ArrayList<>();
//        System.out.println("new BATCH created!");
	}

	public Batch(Integer batchId, String batchName, DayOfWeek dayOfClass, LocalTime startHour,
			List<Participant> participants) {
		super();
		this.batchId = batchId;
		this.batchName = batchName;
		this.dayOfClass = dayOfClass;
		this.startHour = startHour;
        this.participants = new ArrayList<>();
	}

	// getters and setters

	public Integer getBatchId() {
		return batchId;
	}
	
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public DayOfWeek getDayOfClass() {
		return dayOfClass;
	}

	public void setDayOfClass(DayOfWeek dayOfClass) {
		this.dayOfClass = dayOfClass;
	}

	public LocalTime getStartHour() {
		return startHour;
	}

	public void setStartHour(LocalTime startHour) {
		this.startHour = startHour;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	
	//developer-designed method
    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batchName=" + batchName + ", dayOfClass=" + dayOfClass + ", startHour="
				+ startHour + ", participants=" + participants + "]";
	}



}
