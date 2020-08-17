package com.shyaragas.app.models;

public class Vehicle
{
    private int id;
    private String brand;
    private String model;
    private String plate;
    private String problems;
    private String features;

    public Vehicle(int id, String brand, String plate, String problems, String features)
    {
        this.id = id;
        this.brand = brand;
        this.plate = plate;
        this.problems = problems;
        this.features = features;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getPlate()
    {
        return plate;
    }

    public void setPlate(String plate)
    {
        this.plate = plate;
    }

    public String getProblems()
    {
        return problems;
    }

    public void setProblems(String problems)
    {
        this.problems = problems;
    }

    public String getFeatures()
    {
        return features;
    }

    public void setFeatures(String features)
    {
        this.features = features;
    }
}
