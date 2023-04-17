package com.example.testingapi;

import com.example.testingapi.dto.AttributeDTO;
import com.example.testingapi.dto.DocumentDTO;
import com.example.testingapi.service.IAttributeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiTest {
    @Autowired
    IAttributeService service;
    @Value("${document.name}")
    String name;
    @Value("#{'${document.attributes.name}'.split(',')}")
    List<String> attributes;
    @Value("#{'${document.attributes.description}'.split(',')}")
    List<String> descriptions;

    @Test
    public void testCreation() throws JsonProcessingException {

        for (int i = 0; i < attributes.size(); i++) {
                service.save(new AttributeDTO(attributes.get(i), descriptions.get(i)));
        }

        List<AttributeDTO> attributeDTOS = service.getAll();

        DocumentDTO doc = new DocumentDTO(name, "ljrevtyn", attributeDTOS);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json_doc = ow.writeValueAsString(doc);
        String json_attrs = ow.writeValueAsString(attributeDTOS);

    //    System.out.println(doc);
        System.out.println(json_doc);
    //    System.out.println(json_attrs);
  //      System.out.println(attributeDTOS);
    }

}
