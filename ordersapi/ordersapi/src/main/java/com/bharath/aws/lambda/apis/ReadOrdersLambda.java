package com.bharath.aws.lambda.apis;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.bharath.aws.lambda.apis.dto.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ReadOrdersLambda {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public APIGatewayProxyResponseEvent getOrders(APIGatewayProxyRequestEvent request)
            throws JsonProcessingException {

        ScanResult scanResult = dynamoDB.scan(new ScanRequest().withTableName(System.getenv("ORDERS_TABLE")));
        List<Order> orders = scanResult
                .getItems().stream()
                .map(item -> {
                    int orderId = Integer.parseInt(item.get("id").getN());
                    String itemName = item.get("itemName").getS();
                    int quantity = Integer.parseInt(item.get("quantity").getN());
                    return new Order(orderId, itemName, quantity);
                })
                .collect(Collectors.toList());

        String jsonOutput = objectMapper.writeValueAsString(orders);
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonOutput);
    }

}
