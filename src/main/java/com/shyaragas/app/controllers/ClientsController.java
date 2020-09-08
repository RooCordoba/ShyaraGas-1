package com.shyaragas.app.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.shyaragas.app.helpers.exceptions.ClientNotFoundException;
import com.shyaragas.app.models.Client;
import com.shyaragas.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientsController
{

	@Autowired
	ClientService clientService;

	@PostMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Client saveClient(@RequestBody Client client) throws Exception
	{
		return clientService.saveClient(client);
	}

	@GetMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Client getClient(@PathVariable String id) throws ClientNotFoundException, JsonProcessingException
	{
		return clientService.getClientById(id);
	}

	@GetMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Client> getClients() throws Exception
	{
		return clientService.getAllClients();
	}

	@DeleteMapping(value = "/client/{id}")
	@ResponseBody
	public String deleteClient(@PathVariable String id) throws Exception
	{
		return clientService.deleteClient(id);
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



