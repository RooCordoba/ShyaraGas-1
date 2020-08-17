package com.shyaragas.app.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.shyaragas.app.models.Client;
import com.shyaragas.app.services.ClientService;

@Controller
public class ClientsController
{

	@Autowired
	ClientService sClients;
	
	@GetMapping(value = "/getAllClients", produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public List<Client> getClients(){
		return sClients.getClients();
	}
	
	@GetMapping(value = "/getClient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Client getClientById(@PathVariable int id) {
		return sClients.getClientById(id);
	}

	@PostMapping (value = "/newClient", produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Client insertClient(@RequestBody Client client) {
		return sClients.insertClient(client);
	}
	
	@DeleteMapping (value = "/deleteClient/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public DeleteItemOutcome deleteClient (@PathVariable int id) {
		return sClients.deleteClient(id);
	}

	@PatchMapping (value = "/updateClient" , produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UpdateItemOutcome updateClient (@RequestBody Client client) {
		return sClients.updateClient(client);
	}
}



