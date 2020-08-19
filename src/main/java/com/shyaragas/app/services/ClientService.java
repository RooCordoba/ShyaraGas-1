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
			Map mapVehicle = map.get("vehiclelist").getM().get("vehicle1").getM();
			Vehicle vehicle = new Vehicle();
			vehicle.setBrand(mapVehicle.get("brand").toString());
			vehicle.setId(mapVehicle.get("id")); //TODO: CHECK THIS SHIET OUT
			vehicle.setModel(mapVehicle.get("model").toString());
			vehicle.setFeatures(mapVehicle.get("feature").toString());
			vehicle.setPlate(mapVehicle.get("plate").toString());
			vehicle.setProblems(mapVehicle.get("problems").toString());

			Client client = new ClientBuilder(
					map.get("name").getS(),
					map.get("lastname").getS(),
					map.get("phonenumber").getS()
			)
					.withDni(Integer.parseInt(map.get("dni").getN()))
					.withEmail(map.get("email").getS())
					.withVehicles(vehicle)
					.build();
			clientsList.add(client);
		}
		return clientsList;
	}
}


