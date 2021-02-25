package com.shyaragas.app.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shyaragas.app.helpers.AWSConnections;
import com.shyaragas.app.models.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository
{
    DynamoDBMapper mapper = new DynamoDBMapper(AWSConnections.client);

    public Vehicle saveVehicle(Vehicle vehicle)
    {
        try
        {
            mapper.save(vehicle);
            return vehicle;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new Vehicle();
        }


    }
}
