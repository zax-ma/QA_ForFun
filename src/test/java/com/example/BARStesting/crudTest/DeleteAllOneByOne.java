package com.example.BARStesting.crudTest;

import com.example.BARStesting.service.requests.DictionaryService;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeleteAllOneByOne {

    private static final String END_POINT_RULE = "/rule";

    private static final String END_POINT_FACTOR = "/factor";

    private static final String END_POINT_VAR = "/aggregate";

    private static final String END_POINT_DOC = "/doctype";

    private static final String END_POINT_ATTR = "/attribute";

    @Value("#{'${rule-wlc.name}'.split(',')}")
    List<String> rule_wlc_name;

    @Value("#{'${rule-math.name}'.split(',')}")
    List<String> rule_math_name;

    @Value("#{'${factor-wlc.name}'.split(',')}")
    List<String> factor_wlc_name;

    @Value("#{'${factor-math.name}'.split(',')}")
    List<String> factor_math_name;

    @Value("#{'${variable.name}'.split(',')}")
    List<String> variable_name;

    @Value("#{'${document.attribute.name}'.split(',')}")
    List<String> attribute_name;

    @Value("${document.name}")
    String doc_name;

    @Autowired
    DictionaryService service;

    @Order(1)
    @Test
    public void deleteWLCRule(){

        for (String wlc_rule : rule_wlc_name) {

            Response response = service.delete(END_POINT_RULE, wlc_rule);
            assertThat(response.statusCode(), is(equalTo(200)));
        }
    }

    @Order(2)
    @Test
    public void deleteMATHRule(){

        for (String math_rule : rule_math_name) {

            Response response = service.delete(END_POINT_RULE, math_rule);
            assertThat(response.statusCode(), is(equalTo(200)));
        }
    }

    @Order(3)
    @Test
    public void deleteWLCFactor(){

        for (String wlc_factor : factor_wlc_name) {

            Response response = service.delete(END_POINT_FACTOR + "/wlc", wlc_factor);
            assertThat(response.statusCode(), is(equalTo(200)));

        }
    }

    @Order(4)
    @Test
    public void deleteMATHFactor() {

        for (String math_factor : factor_math_name) {

            Response response = service.delete(END_POINT_FACTOR + "/delete", math_factor);
            assertThat(response.statusCode(), is(equalTo(200)));
        }
    }

    @Order(5)
    @Test
    public void deleteVariable() {

        String path = "aggregateVariableId";

        for (String variable : variable_name) {
            Response response = service.deleteVar(END_POINT_VAR + "/delete", path, variable);
            assertThat(response.statusCode(), is(equalTo(200)));
        }
    }

    @Order(6)
    @Test
    public void deleteDocument() {

        Response response = service.delete(END_POINT_DOC, doc_name);
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Order(7)
    @Test
    public void deleteAttribute() {

        for (String attribute : attribute_name) {
            Response response = service.delete(END_POINT_ATTR, attribute);
            assertThat(response.statusCode(), is(equalTo(200)));
        }

    }

}
