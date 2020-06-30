package com.shyaragas.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.shyaragas.app.models.Users;
import com.shyaragas.app.services.SUsers;

@Controller
public class CUsers {
	
	@Autowired
	SUsers sUsers; //la insancia ya existe por que SUsers es service. Solo le pongo nombre para referenciarlo.
	
	
	@GetMapping(value = "/getAllUsers" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Users> getUsers()
	{
		return sUsers.getUsers();
	}
	
	
	@GetMapping (value = "/getAllUsers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Users getUserById(@PathVariable int id)
	{
		return sUsers.getUserById(id);
	}
	
	@PostMapping (value = "/newUser", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Users newUser(@RequestBody Users usuario)
	{
		return sUsers.insertUser(usuario);
	}
	
	@DeleteMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DeleteItemOutcome deleteUser(@PathVariable int id)
	{
		return sUsers.deleteUser(id);
	}
	
}
