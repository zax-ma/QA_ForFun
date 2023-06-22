package com.example.BARStesting.crudTest;


import com.example.BARStesting.dto.variableDTO.FieldsDTO;
import com.example.BARStesting.dto.variableDTO.VariableDTO;
import com.example.BARStesting.service.VariableService;
import com.example.BARStesting.service.requests.AuthenticationService;
import com.example.BARStesting.service.requests.DictionaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VariableTest {

    private static final String END_POINT = "/aggregate";

    private static ThreadLocal<String> tokenThread = new ThreadLocal<>();


    @Value("${connection.authentication}")
    String security;

    @Value("${connection.secret}")
    String secret;

    @Value("${connection.user}")
    String user;

    @Value("${connection.password}")
    String password;

    @Value("#{'${variable.name}'.split(',')}")
    List<String> name;

    @Value("#{'${variable.description}'.split(',')}")
    List<String> description;

    @Value("${variable.document}")
    String document;

    @Value("${variable.field.attribute}")
    String attribute;

    @Value("${variable.field.anchor}")
    String anchor;

    @Value("${document.doc-type}")
    String docTypeAnchor;

    @Value("${variable.field.period}")
    String period;

    VariableDTO variable = new VariableDTO();

    @Autowired
    DictionaryService service;

    @Autowired
    VariableService variableService;

    @Autowired
    AuthenticationService authenticationService;

    @BeforeAll
    public void getToken() {

        tokenThread = authenticationService.getToken("SC-CCS_API");

    }

    @BeforeAll
    public void createVarObject(){

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date = sdf.format(new Date());

        List<FieldsDTO> fields = new ArrayList<>();
        FieldsDTO field = new FieldsDTO(document, docTypeAnchor, attribute);
        fields.add(field);

        variable.setAnchor(anchor);
        variable.setPeriod(period);
        variable.setValue("");
        variable.setVersion(0);
        variable.setCreator(user);
        variable.setCreateDate(date+"Z");
        variable.setModifier(user);
        variable.setModifyDate(date+"Z");
        variable.setFields(fields);

    }

    @Order(1)
    @Test
    public void createVariableSum() throws JsonProcessingException {

        variable.setId(name.get(0));
        variable.setDescription(description.get(0));
        variable.setFunction("sum");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(variable);

        System.out.println(json);

        Response response = service.create(END_POINT + "/send", json, tokenThread.get());
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Order(2)
    @Test
    public void createVariableCount() throws JsonProcessingException {

        variable.setId(name.get(1));
        variable.setDescription(description.get(1));
        variable.setFunction("count");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(variable);

        System.out.println(json);

        Response response = service.create(END_POINT + "/send", json, tokenThread.get());
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Order(3)
    @Test
    public void createVariableAvg() throws JsonProcessingException {

        variable.setId(name.get(2));
        variable.setDescription(description.get(2));
        variable.setFunction("avg");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(variable);

        System.out.println(json);

        Response response = service.create(END_POINT + "/send", json, tokenThread.get());
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Order(4)
    @Test
    public void createVariableMax() throws JsonProcessingException {

        variable.setId(name.get(3));
        variable.setDescription(description.get(3));
        variable.setFunction("max");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(variable);

        System.out.println(json);

        Response response = service.create(END_POINT + "/send", json, tokenThread.get());
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Order(5)
    @Test
    public void createVariableMin() throws JsonProcessingException {

        variable.setId(name.get(4));
        variable.setDescription(description.get(4));
        variable.setFunction("min");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(variable);

        System.out.println(json);

        Response response = service.create(END_POINT + "/send", json, tokenThread.get());
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Order(6)
    @Test
    public void getInfoAboutAllVariables() throws JsonProcessingException {

        Response response = service.getInfo(END_POINT+"s");
        assertThat(response.statusCode(), is(equalTo(200)));

        ResponseBody body = response.body();
        String responseBody = body.asString();

        ObjectMapper mapper =  new ObjectMapper();

        JsonNode json = mapper.readTree(responseBody);

        for (JsonNode node : json) {
            String nodeContext = mapper.writeValueAsString(node);
            VariableDTO variableDTO = mapper.readValue(nodeContext, VariableDTO.class);
            variableService.save(variableDTO);
        }

        List<VariableDTO> variables = variableService.getAll();

        for (VariableDTO variableDTO : variables) {
            assert name != null;
            for (String each_variable : name) {
                if (Objects.equals(variableDTO.getId(), each_variable)) {

                    assert true;

                }
            }
        }

    }

}
