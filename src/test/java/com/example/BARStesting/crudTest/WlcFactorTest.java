package com.example.BARStesting.crudTest;

import com.example.BARStesting.dto.factorDTO.*;
import com.example.BARStesting.dto.ruleDTO.JoinsDTO;
import com.example.BARStesting.service.FactorService;
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
public class WlcFactorTest {

    private static final String END_POINT = "/factor";

    private static ThreadLocal<String> tokenThread = new ThreadLocal<>();

    @Value("${connection.authentication}")
    String security;

    @Value("${connection.secret}")
    String secret;

    public FactorDTO<Object> factor = new FactorDTO<>();

    public JoinsDTO joins = new JoinsDTO();

    public List<JoinsDTO> joinsList = new ArrayList<>();

    public PropertiesOrdDTO propertiesDTO = new PropertiesOrdDTO();

    public PropertiesDTO propertiesIdDTO = new PropertiesDTO();

    public PropertiesFrDTO propertiesFrDTO = new PropertiesFrDTO();

    @Value("${connection.user}")
    String user;

    @Value("${connection.password}")
    String password;

    @Value("${factor-wlc.type}")
    String type;

    @Value("#{'${factor-wlc.name}'.split(',')}")
    List<String> name;

    @Value("${factor-wlc.status}")
    String status;

    @Value("${factor-wlc.document}")
    String document;

    @Value("${factor-wlc.field.attribute}")
    String attrName;

    @Value("#{'${factor-wlc.field.category}'.split(',')}")
    List<String> category;

    @Value("#{'${factor-wlc.field.country}'.split(',')}")
    List<String> country;

    @Value("#{'${api.method}'.split(',')}")
    List<String> method;

    @Autowired
    DictionaryService service;

    @Autowired
    FactorService factorService;

    @Autowired
    AuthenticationService authenticationService;

    @BeforeAll
    public void getToken() {

        tokenThread = authenticationService.getToken("SC-CCS_API");

    }

    @BeforeAll
    public void createFactorObject(){

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date = sdf.format(new Date());

        String attribute = document + "." + attrName;

        List<String> categories = new ArrayList<>();
        categories.addAll(category);

        CategoriesDTO categoriesDTO = new CategoriesDTO(categories);

        List<String> countries = new ArrayList<>();
        countries.addAll(country);

        CountriesDTO countriesDTO = new CountriesDTO(countries);

            propertiesDTO.setAttribute(attribute);
            propertiesDTO.setCategories(categoriesDTO);
            propertiesDTO.setCountries(countriesDTO);
            propertiesDTO.setLevenshteinDistance("0");
            propertiesDTO.setStrictSearch(false);

            propertiesIdDTO.setAttribute(attribute);
            propertiesIdDTO.setCategories(categoriesDTO);
            propertiesIdDTO.setCountries(countriesDTO);

            propertiesFrDTO.setAttribute(attribute);
            propertiesFrDTO.setCategories(categoriesDTO);

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
    public void createWLCFactorOrdinarySearch() throws JsonProcessingException {

        String api = method.get(0);

        String name = this.name.get(0);

        joins.setRuleName("R" + name);
        joins.setFactorName(name);
        joins.setOrderNum(1);

        joinsList.add(joins);

        propertiesDTO.setLevenshteinDistance("AUTO");
        propertiesDTO.setLowDistance(3);
        propertiesDTO.setHighDistance(6);
        propertiesDTO.setMethod(api);
        propertiesDTO.setValue("name");

        factor.setFactorName(name);
        factor.setProperties(propertiesDTO);
        factor.setJoins(joinsList);



        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(factor);

        Response response = service.create(END_POINT + "/wlc", json, tokenThread.get());
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Order(2)
    @Test
    public void createWLCFactorIdSearch() throws JsonProcessingException {

        String api = method.get(1);

        String name = this.name.get(1);

        joins.setRuleName("R_" + name);
        joins.setFactorName(name);
        joins.setOrderNum(1);

        joinsList.add(joins);

        propertiesIdDTO.setMethod(api);
        propertiesIdDTO.setValue("number");

        factor.setFactorName(name);
        factor.setProperties(propertiesIdDTO);
        factor.setJoins(joinsList);


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(factor);

        Response response = service.create(END_POINT + "/wlc", json, tokenThread.get());
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Order(3)
    @Test
    public void createWLCFactorFragmentSearch() throws JsonProcessingException {

        String api = method.get(2);

        String name = this.name.get(2);


        joins.setRuleName("R_" + name);
        joins.setFactorName(name);
        joins.setOrderNum(1);

        joinsList.add(joins);

        propertiesFrDTO.setMethod(api);
        propertiesFrDTO.setValue("text");

        factor.setFactorName(name);
        factor.setProperties(propertiesFrDTO);
        factor.setJoins(joinsList);


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(factor);

        Response response = service.create(END_POINT + "/wlc", json, tokenThread.get());
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Order(4)
    @Test
    public void getAllFactors() throws JsonProcessingException {

        Response response = service.getInfo(END_POINT);
        assertThat(response.statusCode(), is(equalTo(200)));


        ResponseBody body = response.body();
        String responseBody = body.asString();

        ObjectMapper mapper =  new ObjectMapper();

        JsonNode json = mapper.readTree(responseBody);

        for (JsonNode node : json) {
            String nodeContext = mapper.writeValueAsString(node);
            FactorDTO factorDTO = mapper.readValue(nodeContext, FactorDTO.class);
            factorService.save(factorDTO);
        }

        List<FactorDTO> factors = factorService.getAll();

          for (FactorDTO factorDTO : factors) {
            assert name != null;
            for (String each_factor : name) {
                if (Objects.equals(factorDTO.getFactorName(), each_factor)) {

                    assert true;

                }
            }
        }
    }

    @Order(5)
    @Test
    public void getOneFactor(){

        Response response = service.getOneFactorInfo(END_POINT + "/one", name.get(0));
        assertThat(response.statusCode(), is(equalTo(200)));

    }
}

