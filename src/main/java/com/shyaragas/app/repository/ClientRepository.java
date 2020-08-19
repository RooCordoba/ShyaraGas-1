package com.shyaragas.app.repository;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.shyaragas.app.models.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository extends AWSRepository
{
    public Client updateItem(Client client){
        PrimaryKey pk = new PrimaryKey("id", client.getId());
        Item itemFromDB;
        try
        {
            itemFromDB = MY_TABLE.getItem(pk);
        }
        catch(Exception e)
        {
            throw e;
        }
        try
        {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(pk)
                    .withUpdateExpression("set #n = :v1, #l = :v2, #e = :v3, #v = :v4, #p = :v5, #d = :v6")
                    .withNameMap(new NameMap()
                            .with("#n", "name")
                            .with("#l", "lastName")
                            .with("#e", "email")
                            .with("#v", "vehicle")
                            .with("#p", "phoneNumber")
                            .with("#d", "dni"))
                    .withValueMap(new ValueMap()
                            .withStringSet(":v1", client.getName())
                            .withStringSet(":v2", client.getLastName())
                            .withStringSet(":v3", client.getEmail())
                            .withList(":v4", client.getVehicleList())
                            .withStringSet(":v5", client.getPhoneNumber())
                            .withInt(":v6", client.getDni()));
            UpdateItemOutcome updateItemOutcome = MY_TABLE.updateItem(updateItemSpec);
            return client;
        }
        catch(Exception e)
        {
            throw e;
        }
        //TODO: CHECK AWS DOCUMENTATION TO IMPROVE EXCEPTION CATCHING

    }


}
