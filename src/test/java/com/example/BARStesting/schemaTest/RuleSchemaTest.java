package com.example.BARStesting.schemaTest;

import com.example.BARStesting.dto.ruleDTO.JoinsDTO;
import com.example.BARStesting.dto.ruleDTO.RuleDTO;
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
public class RuleSchemaTest {

    private static final String END_POINT = "/rule";

    private static final ThreadLocal<String> TOKEN_THREAD = new ThreadLocal<>();

    public RuleDTO rule = new RuleDTO();

    public List<JoinsDTO> joinsList = new ArrayList<>();

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
        JsonNode jsonSchema = generator.generateSchema(RuleDTO.class);

        System.out.println(jsonSchema);

        rule.setVersion(1);
        rule.setStatus("active");
        rule.setCreateDate(date);
        rule.setCreator("test");
        rule.setModifyDate(date);
        rule.setModifier("test");
        rule.setSession(false);
        rule.setScore(0);
        rule.setSecondPhase(false);
        rule.setRiskProfileId(1);

        JoinsDTO joins = new JoinsDTO();

        joins.setRuleName("RULE_SCHEMA_TEST");
        joins.setFactorName("FACTOR_SCHEMA_TEST");
        joins.setOrderNum(1);

        joinsList.add(joins);

        rule.setRuleName("RULE_SCHEMA_TEST");
        rule.setDescription("test");
        rule.setJoins(joinsList);
        rule.setErrorMessage("error");
        rule.setClassName("c1");
        rule.setDuration("1m");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(rule);

        ValidatableResponse response = schema.validate(END_POINT + "/wlc", json, jsonSchema);
        assertThat(response.body(JsonSchemaValidator.matchesJsonSchema(jsonSchema.toPrettyString())),
                equalToObject(response.body(JsonSchemaValidator.matchesJsonSchema(jsonSchema.toPrettyString()))));

    }

    @Order(2)
    @Test
    public void deleteRule() {

        Response response = service.delete(END_POINT, "RULE_SCHEMA_TEST");
        assertThat(response.statusCode(), is(equalTo(200)));

    }

}
