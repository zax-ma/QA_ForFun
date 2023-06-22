package com.example.BARStesting.schemaTest;

import com.example.BARStesting.dto.variableDTO.FieldsDTO;
import com.example.BARStesting.dto.variableDTO.VariableDTO;
import com.example.BARStesting.service.requests.DictionaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.victools.jsonschema.generator.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Данный метод не отдает схему в ответе
 */

public class VariableSchemaTest {

    private static final String END_POINT = "/aggregate/send";

    VariableDTO variable = new VariableDTO();

    @Autowired
    DictionaryService service;


    public void schemaValidation() throws JsonProcessingException {

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date = sdf.format(new Date());

        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(VariableDTO.class);

        System.out.println(jsonSchema.toPrettyString());

        List<FieldsDTO> fields = new ArrayList<>();

        variable.setId("VARIABLE_SCHEMA_TEST");
        variable.setAnchor("CLIENT_ID");
        variable.setFunction("sum");
        variable.setValue("string");
        variable.setVersion(1);
        variable.setCreator("test");
        variable.setCreateDate(date);
        variable.setModifier("test");
        variable.setModifyDate(date);
        variable.setDescription("test");
        variable.setFields(fields);
        variable.setPeriod("30dt");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(variable);


        RestAssured.baseURI = "http://192.168.190.37/integration/api/la/dictionary";

        given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(END_POINT)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema.toPrettyString()));

    }


    public void deleteAttribute() {

        Response response = service.delete(END_POINT, "VARIABLE_SCHEMA_TEST");
        assertThat(response.statusCode(), is(equalTo(200)));

    }
}
