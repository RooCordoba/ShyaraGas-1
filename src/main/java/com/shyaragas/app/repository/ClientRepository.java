package com.shyaragas.app.repository;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.shyaragas.app.models.Client;

public class ClientRepository extends AWSRepository
{
    public Client updateItem(Client client){
        PrimaryKey pk = new PrimaryKey("id", client.getId());
        Item itemFromDB = MY_TABLE.getItem(pk);

    }


}
