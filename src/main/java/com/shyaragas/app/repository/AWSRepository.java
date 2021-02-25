package com.shyaragas.app.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.shyaragas.app.helpers.AWSConnections;
import org.springframework.stereotype.Repository;


@Repository
public abstract class AWSRepository
{
    protected final DynamoDB DYNAMO_DB_CLAW = new DynamoDB(AWSConnections.client);


    protected final DynamoDBMapperConfig myConfig = new DynamoDBMapperConfig.Builder()
            .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
            .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)
            .build();

    protected final DynamoDBMapper mapper = new DynamoDBMapper(AWSConnections.client, myConfig);
}
