package com.softvision.springbootDynamodb.repository;

import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.softvision.springbootDynamodb.domain.Customer;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerRegistrationRepositoryImpl implements CustomerRegistrationRepository {


    @Override
    public void save(Customer customer) {
        try {
            System.setProperty("aws.accessKeyId","aws_access_key_id ");
            System.setProperty("aws.secretKey","aws_secret_access_key ");
//            SystemPropertiesCredentialsProvider provider = new SystemPropertiesCredentialsProvider();

            AmazonDynamoDB client =AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                    new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                    .build();
            DynamoDB dynamoDB = new DynamoDB(client);
            List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
            attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("N"));

            List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
            keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH)); // Partition
            // key

            CreateTableRequest request = new CreateTableRequest().withTableName("customer1").withKeySchema(keySchema)
                    .withAttributeDefinitions(attributeDefinitions).withProvisionedThroughput(
                    new ProvisionedThroughput().withReadCapacityUnits(5L).withWriteCapacityUnits(6L));
            System.out.println("Issuing CreateTable request for customer ");
            Table table = dynamoDB.createTable(request);

            System.out.println("Waiting for customer to be created...this may take a while...");
            table.waitForActive();
        }
        catch (Exception e) {
            System.err.println("CreateTable request failed for customer");
            System.err.println(e.getMessage());
        }

    }
}
