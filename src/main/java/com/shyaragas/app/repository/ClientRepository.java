package com.shyaragas.app.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shyaragas.app.helpers.AWSConnections;
import com.shyaragas.app.models.Client;
import com.shyaragas.app.models.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Repository
public class ClientRepository extends AWSRepository
{
    DynamoDBMapper mapper = new DynamoDBMapper(AWSConnections.client);


    public Client saveClient(Client client)
    {
        try
        {
            mapper.save(client);
            return client;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public Client getClientById(String id) throws JsonProcessingException
    {
        List<Vehicle> vehicles = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Client client = mapper.load(Client.class, id);

        Table table = DYNAMO_DB_CLAW.getTable("ShyaraGasVehicles");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#cid", "clientId");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":clientId", id);

        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("#cid, id, brand, model, plate, problems, features")
                .withFilterExpression("#cid = :clientId")
                .withNameMap(nameMap)
                .withValueMap(valueMap);

        ItemCollection<ScanOutcome> scanOutcome = table.scan(scanSpec);
        Iterator<Item> iter = scanOutcome.iterator();
        while (iter.hasNext()) {
            Item item = iter.next();
            Vehicle vehicle = objectMapper.readValue(item.toJSON(),Vehicle.class);
            vehicles.add(vehicle);
        }
        client.setVehiclelist(vehicles);
        return client;
    }

    public List<Client> getAllClients()
    {
        return mapper.scan(Client.class, new DynamoDBScanExpression());
    }

    public String deleteClient(String id)
    {
        try{
            Client client = getClientById(id);
            List<Vehicle> vehicles = client.getVehiclelist();
            vehicles.stream()
                    .forEach(vehicle -> {
                        mapper.delete(vehicle);
                    });
            mapper.delete(client);
            return "Se borró el cliente y sus vehículos con éxito";
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "No se pudo borrar el cliente, verifique la información e intentelo nuevamente";
        }
    }
}
