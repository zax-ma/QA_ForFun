package com.example.BARStesting.service.requests.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;

import java.io.File;

public interface ITaskRequests {

    Response post(String endpoint, String requestBody, String token);

    Response workflow(String endpoint, String requestBody, String token);

    Response workflow_attache(String endpoint, File file, String token);

    Response workflow_comment(String endpoint, String text, String token);
}
