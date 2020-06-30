package com.shyaragas.app.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.shyaragas.app.helpers.AWSConnections;
import com.shyaragas.app.models.Users;


@Service
public class SUsers {
	
	String tableName = "ShyaraGasUsers";
	public List<Users> getUsers()
	{
		ScanRequest scanner = new ScanRequest().withTableName(tableName);
		ScanResult result = AWSConnections.client.scan(scanner);//HACEME EL ESCANEO Y GUARDAMELO EN RESULTADO
		
		List <Users> listUsers = new ArrayList<Users>();


		for(Map<String,AttributeValue> x : result.getItems())
		{
			Users usuario = new Users();
			usuario.setName(x.get("name").getS());
			usuario.setDni( Integer.parseInt(x.get("dni").getN()));
			usuario.setLastName(x.get("lastName").getS());
			usuario.setSex(Integer.parseInt(x.get("sex").getN()));
			usuario.setId(Integer.parseInt(x.get("id").getN()));
			listUsers.add(usuario);			
		}
		
		return listUsers;
	}
	
	public Users getUserById(int id)
	{
		Users user = new Users();
		DynamoDB claw = new DynamoDB(AWSConnections.client);//AWSConnections es la clase que nosotros creamos para conectarnos.
		Table myTable = claw.getTable(tableName);//agarrame la tabla
		GetItemSpec myItem = new GetItemSpec().withPrimaryKey("id" , id);// crea un objeto que va a buscar en "id" con el id que le pasamos.
		
		try {
			Item userInTable = myTable.getItem(myItem);
			user.setName(userInTable.getString("name"));
			user.setLastName(userInTable.getString("lastName"));
			user.setDni(userInTable.getInt("dni"));
			user.setSex(userInTable.getInt("sex"));
			user.setId(userInTable.getInt("id"));
			
			return user;
		}
		catch(Exception e)
		{
			return new Users();
		}
		
	}
	
	public Users insertUser(Users usuario)
	{
		DynamoDB claw = new DynamoDB(AWSConnections.client);
		Table myTable = claw.getTable(tableName);
		
		
		try {
			Item myItem = new Item()
					.withPrimaryKey("id", usuario.getId())
					.withInt("dni", usuario.getDni())
					.withString("lastName", usuario.getLastName())
					.withInt("sex",usuario.getSex())
					.withString("name", usuario.getName());
					
					PutItemOutcome myOutcome = myTable.putItem(myItem);
					System.out.println(myOutcome);
					return usuario;
		}
		catch(Exception e)
		{
			return new Users();
		}
	
	}
	
	public DeleteItemOutcome deleteUser(int id)
	{
		DynamoDB claw = new DynamoDB(AWSConnections.client);
		Table myTable = claw.getTable(tableName);
		
		try {
			
			
			DeleteItemOutcome outcome = myTable.deleteItem("id", id);
			return outcome;
		
		}
		catch(Exception e)
		{
			throw(e);
		}
	}
}
