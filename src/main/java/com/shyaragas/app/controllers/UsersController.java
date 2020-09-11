package com.shyaragas.app.controllers;


import java.util.List;

import com.shyaragas.app.repository.Users_Repository;
import com.shyaragas.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shyaragas.app.models.User;


@Controller
public class UsersController
{
	
	@Autowired
	UserService userService; //al new lo hizo spring por detras, ya existe la instancia porque le dijiste que era un servicio
	
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUsers() throws Exception {
		return userService.getAllUsers();
	}
	
	@GetMapping (value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // ,produces = MediaType.APPLICATION_JSON_VALUE ->
	@ResponseBody
	public User getUserById(@PathVariable String id) throws Exception {
		return userService.getUserById(id);
	}
	@PostMapping (value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User newUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteUser(@PathVariable String id) throws Exception {
		return userService.deleteUser(id);
	}


}
