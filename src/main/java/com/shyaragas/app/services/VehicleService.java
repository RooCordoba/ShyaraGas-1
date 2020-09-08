package com.shyaragas.app.services;

import com.shyaragas.app.models.Vehicle;
import com.shyaragas.app.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService
{


    @Autowired
    VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle)
    {
        return vehicleRepository.saveVehicle(vehicle);
    }
}
