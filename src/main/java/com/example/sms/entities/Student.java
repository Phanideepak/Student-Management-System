package com.example.sms.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Student {

	@Id
	private Long id;
	
	private String firstName;
	private String lastName;
	private String email;
	private Date birthdate;
	
	public Student()
	{
		
	}

	public Student(Long id, String firstName, String lastName, String email, Date birthdate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthdate = birthdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
}
