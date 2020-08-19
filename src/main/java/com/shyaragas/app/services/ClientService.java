package com.shyaragas.app.services;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.shyaragas.app.models.Client;
import com.shyaragas.app.models.ClientBuilder;
import com.shyaragas.app.models.Vehicle;
import com.shyaragas.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ClientService
{

	@Autowired
	ClientRepository clientRepository;

	public List<Client> getAllClients() throws Exception
	{
		List<Map<String, AttributeValue>> result = clientRepository.getAllItems();
		List<Client> clientsList = new ArrayList<>();
		for (Map<String, AttributeValue> map : result)
		{
			Map<String, AttributeValue> map2 = map.get("vehiclelist").getM();
			ClientBuilder clientBuilder = new ClientBuilder(
					map.get("name").getS(),
					map.get("lastname").getS(),
					map.get("phonenumber").getS()
			);
			for(AttributeValue vehicleProperty : map2.values())
			{
				Vehicle vehicle = new Vehicle();
				vehicle.setId(Integer.parseInt(vehicleProperty.getM().get("id").getN()));
				vehicle.setFeatures(vehicleProperty.getM().get("features").getS());
				vehicle.setProblems(vehicleProperty.getM().get("problems").getS());
				vehicle.setPlate(vehicleProperty.getM().get("plate").getS());
				vehicle.setBrand(vehicleProperty.getM().get("brand").getS());
				vehicle.setModel(vehicleProperty.getM().get("model").getS());
				clientBuilder.withVehicles(vehicle);
			}
			Client client = clientBuilder.withDni(Integer.parseInt(map.get("dni").getN()))
					.withEmail(map.get("email").getS())
					.build();
			clientsList.add(client);
		}
		return clientsList;
	}

	private Vehicle getVehicles(Map<String, AttributeValue> map, int id)
	{
		Map<String, AttributeValue> mapVehicle = map.get("vehiclelist").getM().get("vehicle"+id).getM();

		Vehicle vehicle = new Vehicle();
		vehicle.setId(Integer.parseInt(mapVehicle.get("id").getN()));
		vehicle.setBrand(mapVehicle.get("brand").getS());
		vehicle.setPlate(mapVehicle.get("plate").getS());
		vehicle.setProblems(mapVehicle.get("problems").getS());
		vehicle.setFeatures(mapVehicle.get("features").getS());
		return vehicle;
	}
}


