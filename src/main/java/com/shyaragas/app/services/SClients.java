package com.shyaragas.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.shyaragas.app.helpers.AWSConnections;
import com.shyaragas.app.models.Clients;

@Service
public class SClients {
	String tableName = "ShyaraGasClients";

	public List<Clients> getClients(){
		
		ScanRequest scan = new ScanRequest().withTableName(tableName);
		ScanResult result = AWSConnections.client.scan(scan);
		List<Clients> listClients = new ArrayList<Clients>();
		for(Map<String, AttributeValue> x : result.getItems() ) {
			Clients client = new Clients();
			
			client.setId(Integer.parseInt(x.get("id").getN()));
			client.setName(x.get("name").getS());
			client.setLastName(x.get("lastName").getS());
			client.setDni(Integer.parseInt(x.get("dni").getN()));
			client.setEmail(x.get("email").getS());
			client.setPhoneNumber(Integer.parseInt(x.get("phoneNumber").getN()));
			client.setCarFeatures(x.get("carFeatures").getS());
			client.setCarProblems(x.get("carProblems").getS());
			listClients.add(client);
		}	
		return listClients;
	}

	public Clients getClientById (int id) {
		Clients client = new Clients();
		DynamoDB claw = new DynamoDB(AWSConnections.client);
		Table myTable = claw.getTable(tableName);
		GetItemSpec myItem = new GetItemSpec().withPrimaryKey("id", id);
		try{
			
		Item clientInTable = myTable.getItem(myItem);
		client.setId(clientInTable.getInt("id"));
		client.setName(clientInTable.getString("name"));
		client.setLastName(clientInTable.getString("lastName"));
		client.setDni(clientInTable.getInt("dni"));
		client.setPhoneNumber(clientInTable.getInt("phoneNumber"));
		client.setEmail(clientInTable.getString("email"));
		client.setCarFeatures(clientInTable.getString("carFeatures"));
		client.setCarProblems(clientInTable.getString("carProblems"));
		return client;
		}
		
		catch (Exception e) {
			return new Clients();
		}
	}
	
	public Clients insertClient(Clients client) {
		DynamoDB claw = new DynamoDB(AWSConnections.client);
		Table myTable = claw.getTable(tableName);
		
		try {
			Item myItem = new Item()
					.withPrimaryKey("id", client.getId())
					.withString("name", client.getName())
					.withString("lastName", client.getLastName())
					.withInt("dni", client.getDni())
					.withInt("phoneNumber", client.getPhoneNumber())
					.withString("email", client.getEmail())
					.withString("carFeatures", client.getCarFeatures())
					.withString("carProblems", client.getCarProblems());
					myTable.putItem(myItem);
			return client;
		}
		catch(Exception e){
			return new Clients();
		}
		
		
	}

	public DeleteItemOutcome deleteClient(int id) {
		DynamoDB claw = new DynamoDB(AWSConnections.client);
		Table myTable = claw.getTable(tableName);
		try {
			
			DeleteItemOutcome delete = myTable.deleteItem("id", id);
			
			return delete;
		}
		catch(Exception e) {
			throw(e);
		}
	}
	
	public UpdateItemOutcome updateClient(Clients clientJson) {
		DynamoDB claw = new DynamoDB(AWSConnections.client);
		Table myTable = claw.getTable(tableName);
		
		try {
			PrimaryKey pk = new PrimaryKey("id", clientJson.getId());
			Item itemClient = myTable.getItem(pk);
			Clients clientDB = new Clients();
	
			UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(pk)
					.withUpdateExpression("set #n = :v1, #a = :v2, #d = :v3, #t = :v4, #e = :v5, #cf = :v6, #cp = :v7")
					.withNameMap(new NameMap().with("#n", "name").with("#a", "lastName")
							.with("#d", "dni").with("#t", "phoneNumber").with("#e", "email")
							.with("#cf", "carFeatures").with("#cp", "carProblems"))
					.withValueMap(new ValueMap()
							.withStringSet(":v1", clientJson.getName())
							.withStringSet(":v2", clientJson.getLastName())
							.withInt(":v3", clientJson.getDni())
							.withInt(":v4", clientJson.getPhoneNumber())
							.withStringSet(":v5", clientJson.getEmail())
							.withStringSet(":v6", clientJson.getCarFeatures())
							.withStringSet(":v7", clientJson.getCarProblems()));
			
			UpdateItemOutcome outcome = myTable.updateItem(updateItemSpec);
			return outcome;
		}
		catch(Exception e) {
			throw (e);
		}
	}
}


