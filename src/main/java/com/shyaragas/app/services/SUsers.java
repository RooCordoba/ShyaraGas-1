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
	
	public UpdateItemOutcome updateUser (Users userJson) {
		DynamoDB claw = new DynamoDB(AWSConnections.client);
		Table myTable =claw.getTable(tableName);
		
		try{
			PrimaryKey pk = new PrimaryKey("id", userJson.getId());
			Item itemUser = myTable.getItem(pk);
			Users userDB = new Users();
			userDB.setId(itemUser.getInt("id"));
			userDB.setName(itemUser.getString("name"));
			userDB.setLastName(itemUser.getString("lastName"));
			userDB.setDni(itemUser.getInt("dni"));
			userDB.setSex(itemUser.getInt("sex"));
			
			
			Map<String , String> nombresDeAtributos = new HashMap<String, String>();
			
			//nombresDeAtributos.put("id", "id");
			nombresDeAtributos.put("#n", "name");
			nombresDeAtributos.put("#ln", "lastName");
			nombresDeAtributos.put("#s", "sex");
			nombresDeAtributos.put("#dni", "dni"); //aca tengo un mapa con los nombre representativos de los nombres de los atributos en la tabla
			
			Map<String, Object> valoresDeAtributos = new HashMap <String, Object>();
			if(userJson.getName() == "") 
				valoresDeAtributos.put(":v1", userDB.getName());
			
			else
				valoresDeAtributos.put(":v1", userJson.getName());
			
			if(userJson.getLastName() == "")
				valoresDeAtributos.put(":v2", userDB.getLastName());
			else
				valoresDeAtributos.put(":v2", userJson.getLastName());
			
			if(userJson.getSex() <3 && userJson.getSex() >=0)
                valoresDeAtributos.put(":v4", userDB.getSex());
            else
                valoresDeAtributos.put(":v4", userJson.getSex());
            if(userJson.getDni() == 0)
                valoresDeAtributos.put(":v3", userDB.getDni());
            else
                valoresDeAtributos.put(":v3", userJson.getDni());
			
            
			UpdateItemOutcome result = myTable.updateItem(
					"id", //nombre de la clave primaria (igual exacto que se ve en AWS, osea id)
					userJson.getId(), //valor de la clave primaria (que quiero modificar)
					"set #n = :v1, #ln = :v2, #s = :v4, #dni = :v3", //Expresion en String que AWS entiende 
					nombresDeAtributos, valoresDeAtributos); //De donde saco el nombre y el valor de lo que updateo
			return result;
		}
		catch (Exception e) {
			throw (e);
		}
		
		
	}
}
