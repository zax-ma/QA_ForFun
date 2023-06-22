package com.example.BARStesting.service.requests.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.ValidatableResponse;

public interface ISchemaRequests {

    ValidatableResponse validate(String endpoint, String requestBody, JsonNode schema);

}
