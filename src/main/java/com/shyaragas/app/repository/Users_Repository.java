package com.shyaragas.app.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.shyaragas.app.helpers.AWSConnections;
import com.shyaragas.app.models.Client;
import com.shyaragas.app.models.User;
import com.shyaragas.app.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Users_Repository extends AWSRepository {

    @Autowired
    ClientRepository clientRepository;

    DynamoDBMapperConfig myConfig  = new DynamoDBMapperConfig.Builder()
            .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
            .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)
            .build();
    DynamoDBMapper mapper = new DynamoDBMapper(AWSConnections.client, myConfig);

    public User saveUser (User user)
    {
        try
        {
            mapper.save(user);
            return user;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public User getUserById(String id) throws Exception
    {
        try
        {
            return mapper.load(User.class, id);
        }
        catch (Exception e)
        {
            return new User();
        }

    }

    public List<User> getAllUsers(){
        return mapper.scan(User.class ,new DynamoDBScanExpression());
    }

    public String deleteUser(String id)
    {
        try{
            User user = getUserById(id);
            mapper.delete(user);
            return "Se borró el usuario con éxito";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "No se pudo borrar el usuario, verifique la información e intentelo nuevamente";
        }

    }



}
