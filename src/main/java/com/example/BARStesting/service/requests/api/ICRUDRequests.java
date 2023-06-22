package com.example.BARStesting.service.requests.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;

public interface ICRUDRequests {

    Response create(String endpoint, String requestBody, String token);
    Response getInfo(String endpoint);
    Response update(String endpoint, JsonNode requestBody, String token);
    Response delete(String endpoint, String requestBody);
    Response getOneInfo(String endpoint, String path, String param);
}
