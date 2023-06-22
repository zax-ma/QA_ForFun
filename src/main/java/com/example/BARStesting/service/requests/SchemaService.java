package com.example.BARStesting.service.requests;

import com.example.BARStesting.service.requests.api.ISchemaRequests;
import com.fasterxml.jackson.databind.JsonNode;
import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Slf4j
@Service
public class SchemaService implements ISchemaRequests {

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

    public ValidatableResponse validate(String endpoint, String requestBody, JsonNode jsonSchema) {

        return RestAssured.given(specification)
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema.toPrettyString()));

    }


}
