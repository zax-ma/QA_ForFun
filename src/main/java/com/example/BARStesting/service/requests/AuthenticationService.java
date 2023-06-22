package com.example.BARStesting.service.requests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AuthenticationService {

    private RequestSpecification specification;

    private static final ThreadLocal<String> TOKEN_THREAD = new ThreadLocal<>();

    @Value("${connection.authentication}")
    String security;

    @Value("${connection.secret}")
    String secret;

    @Value("${connection.user}")
    String user;

    @Value("${connection.password}")
    String password;

    @Value("${connection.authentication}")
    private String URI;

    @PostConstruct
    protected void init(){

        RestAssured.useRelaxedHTTPSValidation();

        specification = new RequestSpecBuilder()
                .setBasePath(URI)
                .build();
    }

    public ThreadLocal<String> getToken(String client_id){

        RequestSpecification httpRequest = RestAssured
                .given()
                .log()
                .all()
                .param("grant_type", "password")
                .param("username", user)
                .param("password", password)
                .param("scope", "SC-APPS")
                .param("client_id", client_id)
                .param("client_secret", secret);


        Response res = httpRequest.post(security);
        String jsonString = res.getBody().asString();

        TOKEN_THREAD.set(JsonPath.from(jsonString).get("access_token"));

        return TOKEN_THREAD;

    }

}
