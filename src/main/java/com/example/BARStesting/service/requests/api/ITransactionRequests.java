package com.example.BARStesting.service.requests.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;

public interface ITransactionRequests {

    Response post(String endpoint, ObjectNode requestBody);

    Response portion(String endpoint, String requestBody, int portion);
}
