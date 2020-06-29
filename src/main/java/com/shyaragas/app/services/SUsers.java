package com.shyaragas.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.shyaragas.app.helpers.AWSConnections;
import com.shyaragas.app.models.Users;

@Service					//le dice a spring que instancie una copia y despues la uso cuando la quiera
public class SUsers {
	
	String tableName = "ShyaraGasUsers"; //String con nombre de la tabla declarada 
										//por nosotros (tableName), de la base de datos

	public List<Users> getUsers(){
		
		ScanRequest scan = new ScanRequest().withTableName(tableName); //creacion del objeto scan
		ScanResult result = AWSConnections.client.scan(scan); //scan() es el metodo, scan es el objeto
		List<Users> listUsers = new ArrayList<Users>(); //la clase List es una interfaz
		
		for(Map<String, AttributeValue> x: result.getItems()) { // result.getItems() devuelve una lsita de mapas, y los
																//mapas son de tipo Map<String, AttributeValue>
			Users usuario = new Users();
			usuario.setName(x.get("name").getS());
			usuario.setDni(Integer.parseInt(x.get("dni").getN())); 	//getN me va a devolver un numero "String", para convertirlo en Int, tengo que poner Integer.parseInt("lo que quiera convertir)
																  	//parseInt transforma de string a int
			usuario.setLastName(x.get("lastName").getS());
			usuario.setSex(Integer.parseInt(x.get("sex").getN()));
			usuario.setId(Integer.parseInt(x.get("id").getN()));
			listUsers.add(usuario);									//para que siga al otro usuario existente
		}
				
		return listUsers;
	}
	
	public Users getUserById (int id) {
		
		Users user = new Users();
		DynamoDB claw = new DynamoDB(AWSConnections.client); // AWSConnections es la clase que nosotros creamos para conectarnos 
		Table myTable = claw.getTable(tableName);
		GetItemSpec myItem = new GetItemSpec().withPrimaryKey("id", id); //creamos un objeto que busque el id con el id que le pasamos
		try {
			
		Item userInTable = myTable.getItem(myItem);	
		user.setName(userInTable.getString("name"));	
		user.setLastName(userInTable.getString("lastName"));
		user.setDni(userInTable.getInt("dni"));
		user.setSex(userInTable.getInt("sex"));
		user.setId(userInTable.getInt("id"));
		
		return user;
		}
		
		catch (Exception e){
			return new Users();
		}
	}
	
	public Users insertUser (Users user) {
		
		DynamoDB claw = new DynamoDB(AWSConnections.client);
		Table myTable = claw.getTable(tableName);
		try {
			Item myItem = new Item()
					.withPrimaryKey("id", user.getId())
					.withString("name", user.getName())
					.withString("lastName", user.getLastName())
					.withInt("dni", user.getDni())
					.withInt("sex", user.getSex());
					myTable.putItem(myItem);
					return user;
		}
		catch (Exception e) {
			return new Users();
		}
	}
	
	public DeleteItemOutcome deleteUser(int id) {
		DynamoDB claw = new DynamoDB(AWSConnections.client);
		Table myTable = claw.getTable(tableName);

		try {
			
			DeleteItemOutcome outcome = myTable.deleteItem("id", id);
			
			return outcome;
		} catch (Exception e) {
			throw (e);
		}
	}
}
