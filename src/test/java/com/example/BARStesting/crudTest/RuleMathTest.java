package com.example.BARStesting.crudTest;

import com.example.BARStesting.dto.ruleDTO.JoinsDTO;
import com.example.BARStesting.dto.ruleDTO.RuleDTO;
import com.example.BARStesting.service.RuleService;
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
public class RuleMathTest {

    private static final String END_POINT = "/rule";

    private static ThreadLocal<String> tokenThread = new ThreadLocal<>();

    public RuleDTO rule = new RuleDTO();

    public List<JoinsDTO> joinsList = new ArrayList<>();

    @Value("${connection.authentication}")
    String security;

    @Value("${connection.secret}")
    String secret;

    @Value("${connection.user}")
    String user;

    @Value("${connection.password}")
    String password;

    @Value("#{'${rule-math.name}'.split(',')}")
    List<String> name;

    @Value("${rule-math.risk-profile}")
    int risk;

    @Value("#{'${rule-math.description}'.split(',')}")
    List<String> description;

    @Value("${rule-math.status}")
    String status;

    @Value("${rule-math.post-check}")
    boolean postCheck;

    @Value("#{'${rule-math.field.factor}'.split(',')}")
    List<String> factor;

    @Autowired
    DictionaryService service;

    @Autowired
    RuleService ruleService;


    @Autowired
    AuthenticationService authenticationService;

    @BeforeAll
    public void getToken() {

        tokenThread = authenticationService.getToken("SC-CCS_API");

    }

    @BeforeAll
    public void createRuleObject(){

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date = sdf.format(new Date());

        rule.setVersion(1);
        rule.setStatus(status);
        rule.setCreateDate(date);
        rule.setCreator(user);
        rule.setModifyDate(date);
        rule.setModifier(user);
        rule.setSession(false);
        rule.setScore(0);
        rule.setSecondPhase(postCheck);
        rule.setRiskProfileId(risk);

    }

    @Order(1)
    @Test
    public void createMathRule() throws JsonProcessingException {

        for (int i = 0; i < name.size(); i++) {

            JoinsDTO joins = new JoinsDTO();

            joins.setRuleName(name.get(i));
            joins.setFactorName(factor.get(i));
            joins.setOrderNum(1);

            joinsList.add(joins);

            rule.setRuleName(name.get(i));
            rule.setDescription(description.get(i));
            rule.setJoins(joinsList);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            String json = ow.writeValueAsString(rule);

            Response response = service.create(END_POINT + "/non-wlc", json, tokenThread.get());
            assertThat(response.statusCode(), is(equalTo(200)));

        }
    }

    @Order(2)
    @Test
    public void getInfoAboutAllRules() throws JsonProcessingException {

        Response response = service.getInfo(END_POINT);
        assertThat(response.statusCode(), is(equalTo(200)));

        ResponseBody body = response.body();
        String responseBody = body.asString();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode json = mapper.readTree(responseBody);

        for (JsonNode node : json) {
            String nodeContext = mapper.writeValueAsString(node);
            RuleDTO ruleDTO = mapper.readValue(nodeContext, RuleDTO.class);
            ruleService.save(ruleDTO);
        }

        List<RuleDTO> rules = ruleService.getAll();

        for (RuleDTO ruleDTO : rules) {
            assert name != null;
            for (String each_rule : name) {
                if (Objects.equals(ruleDTO.getRuleName(), each_rule)) {

                    assert true;

                }
            }
        }
    }
}
