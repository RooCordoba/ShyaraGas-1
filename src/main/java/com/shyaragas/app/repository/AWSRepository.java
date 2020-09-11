package com.shyaragas.app.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.shyaragas.app.helpers.AWSConnections;
import com.shyaragas.app.helpers.exceptions.ClientNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;



public abstract class AWSRepository
{
    protected final String TABLE_NAME = "ShyaraGasClients";
    protected final DynamoDB DYNAMO_DB_CLAW = new DynamoDB(AWSConnections.client);
    protected final Table MY_TABLE = DYNAMO_DB_CLAW.getTable(TABLE_NAME);

    protected final String USER_TABLE_NAME = "ShyaraGasUsers";
    protected final Table USER_TABLE = DYNAMO_DB_CLAW.getTable(TABLE_NAME);




/*
    public Item getItemById(int id) throws ClientNotFoundException
    {
        try
        {
            GetItemSpec myItem = new GetItemSpec().withPrimaryKey("id", id);
            Item item = MY_TABLE.getItem(myItem);
            return item;
        }
        catch(Exception e)
        {
            throw new ClientNotFoundException("Cannot found the client in the Database");
        }
    }

    public List<Map<String, AttributeValue>> getAllItems() throws Exception
    {
        try
        {
            ScanRequest scan = new ScanRequest().withTableName(TABLE_NAME);
            ScanResult result = AWSConnections.client.scan(scan);
            List<Map<String, AttributeValue>> list = result.getItems();
            return list;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public Item insertItem(Item item) throws Exception
    {
        try
        {
            MY_TABLE.putItem(item);
            return item;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public Item deleteItem(int id)
    {
        try
        {
            DeleteItemOutcome deleteItemOutcome = MY_TABLE.deleteItem("id", id);
            Item item = deleteItemOutcome.getItem();
            return item;
        }
        catch(Exception e)
        {
            throw e;
        }
    }*/
}
