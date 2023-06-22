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
import java.io.File;

@Slf4j
@Service
public class ControlService implements ITaskRequests {

    private RequestSpecification specification, specification_wf;

    @Value("${connection.addresses.test}")
    private String BASE_URI;
    @Value("${connection.addresses.control}")
    private String SUB_URI;
    @Value("${connection.addresses.workflow}")
    private String WF_URI;

    @PostConstruct
    protected void init(){

        RestAssured.useRelaxedHTTPSValidation();

        specification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(SUB_URI)
                .build();

        specification_wf = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(WF_URI)
                .build();
    }

    @Override
    public Response post(String endpoint, String requestBody, String token) {

        return RestAssured.given(specification)
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(requestBody)
                .when()
                .post(endpoint);

    }

    @Override
    public Response workflow(String endpoint, String requestBody, String token) {

        return RestAssured.given(specification_wf)
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(requestBody)
                .when()
                .put(endpoint);
    }

    @Override
    public Response workflow_attache(String endpoint, File file, String token) {

        return RestAssured.given(specification_wf)
                .auth()
                .oauth2(token)
                .log()
                .all()
                .multiPart(file)
                .when()
                .post(endpoint);
    }

    @Override
    public Response workflow_comment(String endpoint, String text, String token) {

        return RestAssured.given(specification_wf)
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(text)
                .when()
                .post(endpoint);
    }

}
