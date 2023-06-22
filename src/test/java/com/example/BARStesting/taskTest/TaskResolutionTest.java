package com.example.BARStesting.taskTest;

import com.example.BARStesting.service.requests.AuthenticationService;
import com.example.BARStesting.service.requests.api.ITaskRequests;
import com.example.BARStesting.utils.IOService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskResolutionTest {

    private static ThreadLocal<String> TOKEN_THREAD = new ThreadLocal<>();

    private List<String> workFlowId = new ArrayList<>();

    private List<String> factorInstanceId = new ArrayList<>();

    @Value("${connection.user}")
    String user;

    @Value("${connection.password}")
    String password;

    @Value("${connection.authentication}")
    String security;

    @Value("${connection.secret}")
    String secret;

    @Value("${tasks.file-name-to-save-joins}")
    String joins;

    @Value("${tasks.file-name-to-save-tasks}")
    String tasks;

    @Value("${tasks.path}")
    String pathToFile;

    @Autowired
    IOService ioService;

    @Autowired
    ITaskRequests service;

    @Autowired
    AuthenticationService authenticationService;

    @BeforeAll
    public void getCookingTestBed() throws IOException {

        TOKEN_THREAD = authenticationService.getToken("SC-CCS_UI");

        List<String> jsonList = ioService.readFromFile(tasks);

        for (String json : jsonList) {

            Response getId = service.post("/document", json, TOKEN_THREAD.get());

            ResponseBody body = getId.body();
            String responseBody = body.asString();

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> id = mapper.readValue(responseBody, new TypeReference<>() {
            });

            workFlowId.add(id.get("workflowInstanceId").toString());

            Response getInstance = service.post("/rules", json, TOKEN_THREAD.get());

            ResponseBody factor_body = getInstance.body();
            String responseFBody = factor_body.asString();

            JsonNode nodeList = mapper.readValue(responseFBody, JsonNode.class);

            for (int i = 0; i < nodeList.size(); i++) {

                JsonNode nodeObject = nodeList.get(i);
                JsonNode joinsList = nodeObject.get("joins");
                JsonNode joinsObject = joinsList.get(0);
                JsonNode joinsId = joinsObject.get("id");
                JsonNode factorInstField = joinsId.get("factorInstanceId");

                factorInstanceId.add(factorInstField.asText());
            }

            for (int i = 0; i < workFlowId.size(); i++) {

                Map<String, String> bed = new HashMap<>();
                bed.put(workFlowId.get(i), factorInstanceId.get(i));

                String bed_json = mapper.writeValueAsString(bed);

                ioService.saveToFile(bed_json, joins);

            }
        }
    }


    @Order(1)
    @Test
    public void assignMe(){

        for (String id : workFlowId) {

            String assign_me = "{\"command\": {\"name\": \"assign_me\"}}";
            String assign_another = "{\"command\": {\"name\": \"assign_to\", \"parameters\": {\"userId\": \"squirrel\"}}}";

            Response response = service.workflow("/trigger/" + id, assign_me, TOKEN_THREAD.get());
            assertThat(response.statusCode(), is(equalTo(200)));
        }
    }

    @Order(2)
    @Test
    public void startTask(){

        for (String id : workFlowId) {

            String start = "{\"command\": {\"name\": \"start\"}}";

            Response response =  service.workflow("/trigger/" + id, start, TOKEN_THREAD.get());
            assertThat(response.statusCode(), is(equalTo(200)));
        }
    }

    @Order(3)
    @Test
    public void addCommentToTask(){

        for (String id : workFlowId) {
            String comment = "{\"textComment\": \"Hello, world!\"}";

            Response response = service.workflow_comment("/comment/" + id, comment, TOKEN_THREAD.get());
            assertThat(response.statusCode(), is(equalTo(200)));
        }
    }

    @Order(4)
    @Test
    public void attacheFile(){

        File file = new File(pathToFile+tasks);

        Response response =  service.workflow_attache("/attach-files/" + workFlowId.get(0), file, TOKEN_THREAD.get());
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Order(5)
    @Test
    public void setFactorStatus() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String statusConfirmed = "{\"status\": \"DECLINED\"}";
        String statusDeclined = "{\"status\": \"CONFIRMED\"}";
        ObjectNode status;

        for (int i = 0; i < factorInstanceId.size(); i++) {

            if(i%2 == 1) {
                status = mapper.readValue(statusConfirmed, ObjectNode.class);
            } else {
                status = mapper.readValue(statusDeclined, ObjectNode.class);
            }

            status.put("id", factorInstanceId.get(i));
            String statusFactor = status.toString();

            Response response =  service.post("/status/factor-instance", statusFactor, TOKEN_THREAD.get());
            assertThat(response.statusCode(), is(equalTo(200)));

        }
    }

    @Order(6)
    @Test
    public void taskResolution(){

        String allow = "{\"command\": {\"name\": \"allow\", \"parameters\": {\"comment\": \"Ура! Платеж одобрили\"}}}";
        String deny = "{\"command\": {\"name\": \"deny\", \"parameters\": {\"comment\": \"Печалька =(\"}}}";

        for (int i = 0; i < workFlowId.size(); i++) {
            if(i%2 == 1) {

                Response response = service.workflow("/trigger/" + workFlowId.get(i), allow, TOKEN_THREAD.get());
                assertThat(response.statusCode(), is(equalTo(200)));

            } else {

                Response response = service.workflow("/trigger/" + workFlowId.get(i), deny, TOKEN_THREAD.get());
                assertThat(response.statusCode(), is(equalTo(200)));

            }
        }
    }

  //  @Test
    public void eraseTraces(){

        ioService.delete(tasks);
        ioService.delete(joins);

    }
}
