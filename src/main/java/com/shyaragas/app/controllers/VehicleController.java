package com.shyaragas.app.controllers;

import com.shyaragas.app.models.Vehicle;
import com.shyaragas.app.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VehicleController
{
    @Autowired
    VehicleService vehicleService;

    @PostMapping(value = "/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Vehicle saveVehicle(@RequestBody Vehicle vehicle)
    {
        return vehicleService.saveVehicle(vehicle);
    }
}
