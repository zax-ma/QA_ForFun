package com.example.BARStesting.service.requests;

import com.example.BARStesting.service.requests.api.ITaskRequests;
import com.example.BARStesting.service.requests.api.ITransactionRequests;
import com.fasterxml.jackson.databind.node.ObjectNode;
import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class CheckService implements ITransactionRequests {

    private RequestSpecification specification;

    @Value("${connection.addresses.test}")
    private String BASE_URI;
    @Value("${connection.addresses.check}")
    private String SUB_URI;

    @PostConstruct
    protected void init(){

        RestAssured.useRelaxedHTTPSValidation();

        specification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(SUB_URI)
                .build();
    }
    @Override
    public Response post(String endpoint, ObjectNode requestBody) {
        return RestAssured.given(specification)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    @Override
    public Response portion(String endpoint, String requestBody, int portion) {
         return RestAssured.given(specification)
                .contentType(ContentType.JSON)
                .pathParam("portion", portion)
                .body(requestBody)
                .when()
                .post(endpoint + "/{portion}");
    }
}
