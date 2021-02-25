package com.shyaragas.app.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.shyaragas.app.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersRepository extends AWSRepository {

    // Guarda a un usuario creado
    public User saveUser (User user)
    {
        try
        {
            mapper.save(user);
            return user;
        }
        catch(Exception e)              //TODO: fijarse de intentar esperar 2 seg aprox e intentarlo mas tarde
        {
            throw e;
        }
    }

    // Muestra un usuario a partir del id que le doy
    public User getUserById (String id)         // TODO: hacer UserNotFoundException (por ejemplo) try catch, etc.
    {
        return mapper.load(User.class, id);
    }

    // Me muestra toda la lista de usuarios
    public List<User> getAllUsers()             // TODO: hacer lo de Exception
    {
        return mapper.scan(User.class, new DynamoDBScanExpression());
    }

    // Borra a un usuario a traves del id que le paso
    public String deleteUser(String id) //throws JsonProcessingException //TODO: hacer lo de Exception
    {
        //try{
            User user = new User(id);
            mapper.delete(user);
            return "Se borró el usuario con éxito";
        //}
        /*catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "No se pudo borrar el cliente, verifique la información e intentelo nuevamente";
        }*/
    }

}
