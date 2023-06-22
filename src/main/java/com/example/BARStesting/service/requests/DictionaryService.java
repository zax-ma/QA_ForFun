package com.example.BARStesting.service.requests;

import com.example.BARStesting.service.requests.api.ICRUDRequests;
import com.fasterxml.jackson.databind.JsonNode;
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
public class DictionaryService implements ICRUDRequests {

    private RequestSpecification specification;

    @Value("${connection.addresses.test}")
    private String BASE_URI;
    @Value("${connection.addresses.dictionary}")
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
    public Response create(String endpoint, String requestBody, String token) {

        return RestAssured.given(specification)
                .given()
                .auth()
                .oauth2(token)
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    @Override
    public Response getInfo(String endpoint) {

        return RestAssured.given(specification)
                .get(endpoint);

    }

    @Override
    public Response getOneInfo(String endpoint, String path, String param) {

         return RestAssured.given(specification)
                .log()
                .all()
                .pathParam(path, param)
                .when()
                .get(endpoint + "/{" + path + "}");
    }

    @Override
    public Response update(String endpoint, JsonNode requestBody, String token) {

        return RestAssured.given(specification)
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(endpoint);

    }

    @Override
    public Response delete(String endpoint, String requestBody) {

        return RestAssured.given(specification)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(requestBody)
                .when()
                .delete(endpoint);

    }

    public Response createMathFactor(String endpoint,
                                     String fName,
                                     boolean repeatable,
                                     String period,
                                     String description,
                                     String instancePeriod,
                                     String AFM){

        String p1 = "factorName";
        String p2 = "repeatable";
        String p3 = "period";
        String p4 = "description";
        String p5 = "instancePeriod";

        return RestAssured.given(specification)
                .contentType(ContentType.TEXT)
                .param(p1, fName)
                .param(p2, repeatable)
                .param(p3, period)
                .param(p4, description)
                .param(p5, instancePeriod)
                .body(AFM)
                .when()
                .put(endpoint);

    }

    public Response deleteVar(String endpoint, String path, String param) {

        return RestAssured.given(specification)
                .pathParam(path, param)
                .when()
                .delete(endpoint + "/{" + path + "}");

    }

    public Response getOneFactorInfo(String endpoint, String body) {

        return RestAssured.given(specification)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }

    public Response postStatistic(String endpoint, ObjectNode body){

        return RestAssured.given(specification)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }


}
