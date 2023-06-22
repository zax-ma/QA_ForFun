package com.example.BARStesting.crudTest;

import com.example.BARStesting.dto.AttributeDTO;
import com.example.BARStesting.dto.DocumentDTO;
import com.example.BARStesting.service.AttributeService;
import com.example.BARStesting.service.DocumentService;
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
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DocumentTest {

    private static final String END_POINT = "/doctype";

    private static ThreadLocal<String> tokenThread = new ThreadLocal<>();

    DocumentDTO document = new DocumentDTO();

    @Value("${connection.authentication}")
    String security;

    @Value("${connection.secret}")
    String secret;

    @Value("${connection.user}")
    String user;

    @Value("${connection.password}")
    String password;

    @Value("${document.name}")
    String name;

    @Value("${document.description}")
    String description;

    @Value("${document.doc-type}")
    String docTypeAnchor;

    @Value("#{'${document.attribute.name}'.split(',')}")
    List<String> attributeName;

    @Autowired
    DictionaryService service;

    @Autowired
    AttributeService attributeService;

    @Autowired
    DocumentService documentService;

    @Autowired
    AuthenticationService authenticationService;

    @BeforeAll
    public void getToken() {

        tokenThread = authenticationService.getToken("SC-CCS_API");

    }

    @BeforeAll
    public void dataInit() throws JsonProcessingException {

        Response response = service.getInfo("/attribute");
        ResponseBody body = response.body();
        String responseBody = body.asString();

        ObjectMapper mapper =  new ObjectMapper();

        JsonNode json = mapper.readTree(responseBody);

        for (JsonNode node : json) {
            String nodeContext = mapper.writeValueAsString(node);
            AttributeDTO attributeDTO = mapper.readValue(nodeContext, AttributeDTO.class);
            attributeService.save(attributeDTO);
        }

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date = sdf.format(new Date());

        List<String> factorNames = new ArrayList<>();

        document.setDocType(name);
        document.setVersion(1);
        document.setCreateDate(date);
        document.setCreator(user);
        document.setModifyDate(date);
        document.setModifier(user);
        document.setDocTypeAnchor(docTypeAnchor);
        document.setWlcFactorNames(factorNames);
        document.setDescription(description);
    }

    @Order(1)
    @Test
    public void createDocument() throws JsonProcessingException {

        List<AttributeDTO> attributes = attributeService.getAll();

        List<AttributeDTO> attributesForCreation = new ArrayList<>();

        for (AttributeDTO attributeDTO : attributes) {
            assert attributeName != null;
            for (String attribute : attributeName) {
                if (Objects.equals(attributeDTO.getAttributeName(), attribute)) {
                    attributesForCreation.add(attributeDTO);
                }
            }
        }

            document.setAttributes(attributesForCreation);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(document);

        Response response = service.create(END_POINT, json, tokenThread.get());
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Order(2)
    @Test
    public void getInfoAboutAllDocuments() throws JsonProcessingException {

        Response response = service.getInfo(END_POINT);
        assertThat(response.statusCode(), is(equalTo(200)));

        ResponseBody body = response.body();
        String responseBody = body.asString();

        ObjectMapper mapper =  new ObjectMapper();

        JsonNode json = mapper.readTree(responseBody);

        for (JsonNode node : json) {
            String nodeContext = mapper.writeValueAsString(node);
            DocumentDTO documentDTO = mapper.readValue(nodeContext, DocumentDTO.class);
            documentService.save(documentDTO);
        }

        List<DocumentDTO> documents = documentService.getAll();

        for (DocumentDTO documentDTO : documents) {
            assert name != null;

                if (Objects.equals(documentDTO.getDocType(), name)) {

                    assert true;

            }
        }
    }

    @Order(3)
    @Test
    public void getInfoAboutOneDocument() {

        String path = "doctype";

        Response response = service.getOneInfo(END_POINT, path, name);
        assertThat(response.statusCode(), is(equalTo(200)));

    }
}
