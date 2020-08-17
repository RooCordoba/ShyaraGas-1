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
import com.shyaragas.app.models.User;
import com.shyaragas.app.services.SUsers;

@Controller
public class UsersController
{
	
	@Autowired
	SUsers sUsers; //al new lo hizo spring por detras, ya existe la instancia porque le dijiste que era un servicio
	
	@GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUsers(){
		return sUsers.getUsers();
	}
	
	@GetMapping (value = "getUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // ,produces = MediaType.APPLICATION_JSON_VALUE ->
	@ResponseBody
	public User getUserById(@PathVariable int id){
		return sUsers.getUserById(id);
	}
	@PostMapping (value = "/newUser", produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User newUser(@RequestBody User user) {
		return sUsers.insertUser(user);
	}
	
	@DeleteMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DeleteItemOutcome deleteUser(@PathVariable int id) {
		return sUsers.deleteUser(id);
	}
	
	@PatchMapping (value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UpdateItemOutcome updateItem(@RequestBody User user)
    {
         return sUsers.updateUser(user);
    }
}
