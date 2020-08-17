package com.shyaragas.app.models;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable {

	private String name;
	private String lastName;
	private String email;
	private List<Vehicle> vehicleList;
	private String phoneNumber;
	private int id;
	private int dni;

	public Client(String name, String lastName, String email, List<Vehicle> vehicleList, String phoneNumber, int id, int dni)
	{
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.vehicleList = vehicleList;
		this.phoneNumber = phoneNumber;
		this.id = id;
		this.dni = dni;
	}

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
	public List<Vehicle> getVehicleList()
	{
		return vehicleList;
	}
	public void setVehicleList(List<Vehicle> vehicleList)
	{
		this.vehicleList = vehicleList;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}
