package com.example.BARStesting.schemaTest;

import com.example.BARStesting.dto.factorDTO.FactorDTO;
import com.example.BARStesting.dto.factorDTO.PropertiesDTO;
import com.example.BARStesting.dto.ruleDTO.JoinsDTO;
import com.example.BARStesting.service.requests.DictionaryService;
import com.example.BARStesting.service.requests.SchemaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.victools.jsonschema.generator.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FactorSchemaTest {

    private static final String END_POINT = "/factor/wlc";

    FactorDTO factor = new FactorDTO();

    @Autowired
    DictionaryService service;

    @Autowired
    SchemaService schema;

    @Order(1)
    @Test
    public void schemaValidation() throws JsonProcessingException {

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date = sdf.format(new Date());

        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(FactorDTO.class);

        System.out.println(jsonSchema.toPrettyString());

        JoinsDTO join = new JoinsDTO();

        List<JoinsDTO> joins = new ArrayList<>();
        joins.add(join);

        PropertiesDTO properties = new PropertiesDTO();

        properties.setAttribute("101.DOC_NOTES");
        properties.setMethod("/search/by/text");
        properties.setValue("text");

        factor.setFactorName("FACTOR_SCHEMA_TEST");
        factor.setCreateDate(date);
        factor.setCreator("test");
        factor.setType("WLC");
        factor.setModifier("test");
        factor.setModifyDate(date);
        factor.setVersion(1);
        factor.setStatus("active");
        factor.setErrorMessage("error");
        factor.setPeriod("P1M");
        factor.setInstancePeriod("1dt");
        factor.setProperties(properties);
        factor.setJoins(joins);


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(factor);

        ValidatableResponse response = schema.validate(END_POINT, json, jsonSchema);
        assertThat(response.body(JsonSchemaValidator.matchesJsonSchema(jsonSchema.toPrettyString())),
                equalToObject(response.body(JsonSchemaValidator.matchesJsonSchema(jsonSchema.toPrettyString()))));

    }

    @Order(2)
    @Test
    public void deleteFactor() {

        Response response = service.delete(END_POINT, "FACTOR_SCHEMA_TEST");
        assertThat(response.statusCode(), is(equalTo(200)));

    }
}
