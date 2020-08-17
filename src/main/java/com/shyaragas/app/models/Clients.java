package com.shyaragas.app.models;

import java.io.Serializable;

public class Clients implements Serializable {

	private String name, lastName, email, carFeatures, carProblems;
	private int id, dni, phoneNumber;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCarFeatures() {
		return carFeatures;
	}
	public void setCarFeatures(String carFeatures) {
		this.carFeatures = carFeatures;
	}
	public String getCarProblems() {
		return carProblems;
	}
	public void setCarProblems(String carProblems) {
		this.carProblems = carProblems;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
