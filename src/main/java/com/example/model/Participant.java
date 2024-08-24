package com.example.model;

import java.time.LocalDate;

public class Participant {
	
	//fields
	private Integer pId;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private LocalDate startDate;  
	private Integer batchId;
	private String batchName;
	
	// Constructors 

	public Participant() {
		super();
	}

	public Participant(Integer pId, String firstName, String lastName, String phone, String email, LocalDate startDate,
			Integer batchId, String batchName) {
		super();
		this.pId = pId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.startDate = startDate;
		this.batchId = batchId;
		this.batchName = batchName;
	}



	//setters and getters
	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

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


	@Override
	public String toString() {
		return "Participant [pId=" + pId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", email=" + email + ", startDate=" + startDate + ", batchId=" + batchId + ", batchName=" + batchName +"]";
	}


	

	
}
