package com.shyaragas.app.services;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.shyaragas.app.helpers.exceptions.ClientNotFoundException;
import com.shyaragas.app.models.Client;
import com.shyaragas.app.models.ClientBuilder;
import com.shyaragas.app.models.Vehicle;
import com.shyaragas.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
				clientBuilder.withVehicles(buildVehicle(vehicleProperty));
			}
			Client client = clientBuilder.withDni(Integer.parseInt(map.get("dni").getN()))
					.withEmail(map.get("email").getS())
					.build();
			clientsList.add(client);
		}
		return clientsList;
	}

	public Client getClientById(int id) throws ClientNotFoundException
	{
		Item item = clientRepository.getItemById(id);
		ClientBuilder clientBuilder;
		String name = item.get("name").toString();
		String lastname = item.get("lastname").toString();
		String phoneNumber = item.get("phonenumber").toString();
		clientBuilder = new ClientBuilder(name, lastname, phoneNumber)
				.withEmail(item.get("email").toString())
				.withDni(Integer.parseInt(item.get("dni").toString()))
				.withId(Integer.parseInt(item.get("id").toString()));
		HashMap<String, AttributeValue> vehicles = (HashMap<String, AttributeValue>) item.get("vehiclelist");
		LinkedHashMap<String, LinkedHashMap> vehicles.values();











		return null;
	}















	private Vehicle buildVehicle(AttributeValue attributeVehicle)
    {
    	Vehicle vehicle = new Vehicle();
		vehicle.setId(Integer.parseInt(attributeVehicle.getM().get("id").getN()));
		vehicle.setFeatures(attributeVehicle.getM().get("features").getS());
		vehicle.setProblems(attributeVehicle.getM().get("problems").getS());
		vehicle.setPlate(attributeVehicle.getM().get("plate").getS());
		vehicle.setBrand(attributeVehicle.getM().get("brand").getS());
		vehicle.setModel(attributeVehicle.getM().get("model").getS());
        return vehicle;
    }







}


