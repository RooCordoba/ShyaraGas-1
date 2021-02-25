package com.shyaragas.app.models;

import java.util.ArrayList;
import java.util.List;

public class ClientBuilder
{
    private String name = "";
    private String lastName = "";
    private String email = "";
    private List vehicleList = new ArrayList<>();
    private String id = "0";
    private int dni = 0;
    private String phoneNumber = "";

    public ClientBuilder(String name, String lastName, String phoneNumber)
    {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public ClientBuilder withEmail(String email)
    {
        this.email = email;
        return this;
    }

    public ClientBuilder withId(String id)
    {
        this.id = id;
        return this;
    }

    public ClientBuilder withVehicles(Vehicle vehicle)
    {
        vehicleList.add(vehicle);
        return this;
    }

    public ClientBuilder withDni(int dni)
    {
        this.dni = dni;
        return this;
    }

    public Client build()
    {
        Client client = new Client(name, lastName, email, vehicleList, phoneNumber, id, dni);
        return client;
    }
}
