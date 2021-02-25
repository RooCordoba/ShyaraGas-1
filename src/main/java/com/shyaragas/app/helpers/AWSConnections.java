package com.shyaragas.app.helpers;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class AWSConnections {

	private static final Regions region  = Regions.SA_EAST_1;// no hago new por que es estatica-
    public static final AmazonDynamoDB client = AmazonDynamoDBClientBuilder
            .standard()//El client es el objeto que maneja la coneccion con AWS. Como es cliente Dynamo, Base de datos
            .withCredentials(new AWSStaticCredentialsProvider(AWSCredentials.myCredentials))
            .withRegion(region)//este region es el que cree arriba.
            .build();
}