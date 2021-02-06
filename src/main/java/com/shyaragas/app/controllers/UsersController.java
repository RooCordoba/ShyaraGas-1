package com.shyaragas.app.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.shyaragas.app.models.User;
import com.shyaragas.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersController
{
	@Autowired
	UserService userService; //al new lo hizo spring por detras, ya existe la instancia porque le dijiste que era un servicio
	
	@GetMapping(value = "user/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // ,produces = MediaType.APPLICATION_JSON_VALUE ->
	@ResponseBody
	public User getUserById(@PathVariable String id) throws JsonProcessingException {
		return userService.getUserById(id);
	}

	@PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User newUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}

	@DeleteMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}
}
