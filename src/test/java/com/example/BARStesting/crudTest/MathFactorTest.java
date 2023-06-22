package com.example.BARStesting.crudTest;

import com.example.BARStesting.dto.factorDTO.FactorDTO;
import com.example.BARStesting.dto.ruleDTO.JoinsDTO;
import com.example.BARStesting.dto.factorDTO.PropertiesMathDTO;
import com.example.BARStesting.service.requests.AuthenticationService;
import com.example.BARStesting.service.requests.DictionaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.response.Response;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MathFactorTest {

    private static final String END_POINT = "/factor";

    private static ThreadLocal<String> tokenThread = new ThreadLocal<>();

    @Value("${connection.authentication}")
    String security;

    @Value("${connection.secret}")
    String secret;

    @Value("${connection.user}")
    String user;

    @Value("${connection.password}")
    String password;

    @Value("${factor-math.type}")
    String type;

    @Value("${factor-math.status}")
    String status;

    @Value("#{'${factor-math.name}'.split(',')}")
    List<String> name;

    @Value("#{'${factor-math.description}'.split(',')}")
    List<String> description;

    @Value("#{'${factor-math.expression}'.split(',')}")
    List<String> expression;

    private final FactorDTO factor = new FactorDTO();

    private final JoinsDTO joins = new JoinsDTO();

    private final List<JoinsDTO> joinsList = new ArrayList<>();

    private final PropertiesMathDTO propertiesMathDTO = new PropertiesMathDTO();

    @Autowired
    DictionaryService service;

    @Autowired
    AuthenticationService authenticationService;

    @BeforeAll
    public void getToken() {

        tokenThread = authenticationService.getToken("SC-CCS_API");

    }

    @BeforeAll
    public void mathFactorObject(){

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date = sdf.format(new Date());


        factor.setVersion(1);
        factor.setCreateDate(date);
        factor.setCreator(user);
        factor.setModifyDate(date);
        factor.setModifier(user);
        factor.setType(type);
        factor.setStatus(status);
        factor.setPeriod("");
        factor.setRepeatable(true);
        factor.setInstancePeriod("");
        factor.setErrorMessage("Unknown attribute");

    }

    @Order(1)
    @Test
    public void createMATHFactorNew() throws JsonProcessingException {

        for (int i = 0; i < name.size(); i++) {

            propertiesMathDTO.setProgram(expression.get(i));

            joins.setFactorName(name.get(i));
            joins.setRuleName("R" + name.get(i));
            joins.setOrderNum(1);

            joinsList.add(joins);

            factor.setFactorName(name.get(i));
            factor.setDescription(description.get(i));
            factor.setProperties(propertiesMathDTO);
            factor.setJoins(joinsList);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            String json = ow.writeValueAsString(factor);

            Response response = service.create(END_POINT + "/wlc", json, tokenThread.get());
            assertThat(response.statusCode(), is(equalTo(200)));

        }
    }


    public void createMATHFactorOld(){

        String body = "IF 1=1";

        Response response = service.createMathFactor(END_POINT + "/create",
                "OLD_MATH_FACTOR",
                true,
                "P1M",
                "Factor",
                "1mt",
                body);
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Order(2)
    @Test
    public void getOneFactor(){

        Response response = service.getOneFactorInfo(END_POINT + "/one", name.get(0));
        assertThat(response.statusCode(), is(equalTo(200)));

    }
}
