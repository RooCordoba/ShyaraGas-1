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

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
				Map<String, AttributeValue> mapa = vehicleProperty.getM();
				clientBuilder.withVehicles(buildVehicleWithMapAttribute(mapa));
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
		LinkedHashMap<String, LinkedHashMap<String, Object>> vehicles = (LinkedHashMap<String, LinkedHashMap<String, Object>>) item.get("vehiclelist");
		for(LinkedHashMap<String, Object> map : vehicles.values())
		{
			clientBuilder.withVehicles(buildVehicleWithMapObject(map));
		}
		return clientBuilder.build();

	}

	private Vehicle buildVehicleWithMapObject(Map<String, Object> map)
    {
    	Vehicle vehicle = new Vehicle();
		vehicle.setModel(map.get("model").toString());
		vehicle.setBrand(map.get("brand").toString());
		vehicle.setPlate(map.get("plate").toString());
		vehicle.setProblems(map.get("problems").toString());
		vehicle.setFeatures(map.get("features").toString());
        return vehicle;
    }


	private Vehicle buildVehicleWithMapAttribute(Map<String, AttributeValue> map)
	{
		Vehicle vehicle = new Vehicle();
		vehicle.setModel(map.get("model").getS());
		vehicle.setBrand(map.get("brand").getS());
		vehicle.setPlate(map.get("plate").getS());
		vehicle.setProblems(map.get("problems").getS());
		vehicle.setFeatures(map.get("features").getS());
		return vehicle;
	}





}


