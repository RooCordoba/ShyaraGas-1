package com.shyaragas.app.services;

import com.shyaragas.app.models.User;
import com.shyaragas.app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public User saveUser(User user)
    {
       return usersRepository.saveUser(user);
    }

    public User getUserById (String id)
    {
        return usersRepository.getUserById(id);
    }

    public List<User> getAllUsers()
    {
        return usersRepository.getAllUsers();
    }

    public String deleteUser(String id)
    {
        return usersRepository.deleteUser(id);
    }
}
