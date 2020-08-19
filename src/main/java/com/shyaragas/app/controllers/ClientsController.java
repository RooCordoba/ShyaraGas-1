package com.shyaragas.app.controllers;



import com.shyaragas.app.models.Client;
import com.shyaragas.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClientsController
{

	@Autowired
	ClientService clientService;
	
	@GetMapping(value = "/getAllClients", produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public List<Client> getClients() throws Exception
	{
		return clientService.getAllClients();
	}
	/*
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
	}*/
}



