package com.shyaragas.app.services;

import com.shyaragas.app.models.User;
import com.shyaragas.app.repository.Users_Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserService {
    @Autowired
    Users_Repository users_repository;

    public User saveUser(User user)
    {
        users_repository.saveUser(user);
        return user;
    }

    public User getUserById(String id) throws Exception
    {
        return users_repository.getUserById(id);
    }

    public List<User> getAllUsers() throws Exception
    {
        return users_repository.getAllUsers();
    }

    public String deleteUser(String id) throws Exception
    {
        return users_repository.deleteUser(id);
    }

}
