package com.example.BARStesting.schemaTest;

import com.example.BARStesting.dto.AttributeDTO;
import com.example.BARStesting.dto.DocumentDTO;
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
public class DocumentSchemaTest {

    private static final String END_POINT = "/doctype";

    AttributeDTO attribute = new AttributeDTO();

    DocumentDTO document = new DocumentDTO();

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
        JsonNode jsonSchema = generator.generateSchema(DocumentDTO.class);

        System.out.println(jsonSchema.toPrettyString());

        attribute.setAttributeName("ATTRIBUTE_SCHEMA_TEST");
        attribute.setCreateDate(date);
        attribute.setCreator("test");
        attribute.setDescription("");
        attribute.setModifier("test");
        attribute.setModifyDate(date);
        attribute.setVersion(1);

        List<AttributeDTO> attributes = new ArrayList<>();
        attributes.add(attribute);

        List<String> factorNames = new ArrayList<>();

        document.setDocType("DOC_SCHEMA_TEST");
        document.setAttributes(attributes);
        document.setVersion(1);
        document.setCreateDate(date);
        document.setCreator("test");
        document.setModifyDate(date);
        document.setModifier("test");
        document.setDocTypeAnchor("DOC_TYPE");
        document.setWlcFactorNames(factorNames);
        document.setDescription("hello");


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(document);

        ValidatableResponse response = schema.validate(END_POINT, json, jsonSchema);
        assertThat(response.body(JsonSchemaValidator.matchesJsonSchema(jsonSchema.toPrettyString())),
                equalToObject(response.body(JsonSchemaValidator.matchesJsonSchema(jsonSchema.toPrettyString()))));

    }

    @Order(2)
    @Test
    public void deleteDocument() {

        Response response = service.delete(END_POINT, "DOC_SCHEMA_TEST");
        assertThat(response.statusCode(), is(equalTo(200)));

    }

}
