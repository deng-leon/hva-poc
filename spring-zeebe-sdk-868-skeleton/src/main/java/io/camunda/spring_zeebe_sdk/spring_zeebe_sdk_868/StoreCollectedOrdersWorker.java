package io.camunda.spring_zeebe_sdk.spring_zeebe_sdk_868;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.spring_zeebe_sdk.spring_zeebe_sdk_868.domain.Order;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Component
public class StoreCollectedOrdersWorker {

    private final RestClient restClient;

    public StoreCollectedOrdersWorker(RestClient restClient) {
        this.restClient = restClient;
    }
    @Autowired
    private ZeebeClient zeebeClient;
    private final static Logger LOG = LoggerFactory.getLogger(StoreCollectedOrdersWorker.class);

    private final ObjectMapper objectMapper = new ObjectMapper();


    @JobWorker(type = "storeCollectedOrders")
    public void storeCollectedOrders(final ActivatedJob job) {

        Map<String, Object> variables = job.getVariablesAsMap();

        LOG.info("Number of variables: {}", variables.size());

        List<Order> orders = (List<Order>) variables.get("orderList");

        LOG.info("Number of orders: {}", orders.size());

        // Log Body as JSON string

        try {
            String requestBody = objectMapper.writeValueAsString(orders);
            LOG.info("Request Body: {}", requestBody);
        } catch (Exception e) {
            LOG.error("Could not serialize JSON body", e);
        }

        ResponseEntity<String> response = restClient
                .post()
                .uri("https://")
                //.uri("https://example.com/api/orders?api_key=API_KEY")
                //.header("Authorization", "Bearer ACCESS_TOKEN")
                //.header("Authorization", "Basic " + Base64.getEncoder().encodeToString("username:password".getBytes()))
                //.header("x-api-key", API_KEY")
                .body(orders)
                .retrieve()
                .toEntity(String.class);

        System.out.println("Response: " + response.getBody());


    }








}
