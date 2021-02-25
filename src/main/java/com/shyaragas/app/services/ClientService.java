package com.shyaragas.app.services;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.shyaragas.app.models.Client;
import com.shyaragas.app.models.Vehicle;
import com.shyaragas.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientService
{
	@Autowired
	ClientRepository clientRepository;

	public Client saveClient(Client client)
	{
		clientRepository.saveClient(client);
		return client;
	}

	public Client getClientById(String id) throws JsonProcessingException
	{
		return clientRepository.getClientById(id);
	}

	public List<Client> getAllClients() throws Exception
	{
		return clientRepository.getAllClients();
	}

	public String deleteClient(String id) throws Exception
	{
		return clientRepository.deleteClient(id);
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


