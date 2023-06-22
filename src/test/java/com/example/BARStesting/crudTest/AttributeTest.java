package com.example.BARStesting.crudTest;

import com.example.BARStesting.dto.AttributeDTO;
import com.example.BARStesting.service.AttributeService;
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
class AttributeTest {

	private static final String END_POINT = "/attribute";

	private static ThreadLocal<String> tokenThread = new ThreadLocal<>();

	private final AttributeDTO attribute = new AttributeDTO();

	@Value("#{'${document.attribute.name}'.split(',')}")
	List<String> name;

	@Value("#{'${document.attribute.description}'.split(',')}")
	List<String> description;

	@Value("${connection.user}")
	String user;

	@Autowired
	DictionaryService service;

	@Autowired
	AttributeService attributeService;

	@Autowired
	AuthenticationService authenticationService;

	@BeforeAll
	public void getToken() {

		tokenThread = authenticationService.getToken("SC-CCS_API");

	}

	@BeforeAll
	public void createAttributeObject(){

		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String date = sdf.format(new Date());

		attribute.setVersion(1);
		attribute.setCreator(user);
		attribute.setCreateDate(date);
		attribute.setModifier(user);
		attribute.setModifyDate(date);

	}

	@Order(1)
	@Test
	public void createAttribute() throws JsonProcessingException {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		for (int i = 0; i < name.size(); i++) {

			attribute.setAttributeName(name.get(i));
			attribute.setDescription(description.get(i));


			String json_attr = ow.writeValueAsString(attribute);

			Response response = service.create(END_POINT, json_attr, tokenThread.get());
			assertThat(response.statusCode(), is(equalTo(200)));

			}
		}


	@Order(2)
	@Test
	public void getInfoAboutAttributes() throws JsonProcessingException {

		Response response = service.getInfo(END_POINT);

		ResponseBody body = response.body();
		String responseBody = body.asString();

		ObjectMapper mapper =  new ObjectMapper();

		JsonNode json = mapper.readTree(responseBody);

		for (JsonNode node : json) {
			String nodeContext = mapper.writeValueAsString(node);
			AttributeDTO attributeDTO = mapper.readValue(nodeContext, AttributeDTO.class);
			attributeService.save(attributeDTO);
		}

		List<AttributeDTO> attributes = attributeService.getAll();

		for (AttributeDTO attributeDTO : attributes) {
			assert name != null;
			for (String attribute : name) {
				if (Objects.equals(attributeDTO.getAttributeName(), attribute)) {

					assert true;

				}
			}
		}
	}

	@Order(3)
	@Test
	public void updateAttribute() throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		String body = "{\n" +
				"  \"attributeName\": \"A_SUM\",\n" +
				"  \"description\": \"Сумма одной операции клиента\",\n" +
				"  \"version\": 0\n" +
				"}";

		JsonNode requestBody = mapper.readTree(body);

		Response response = service.update(END_POINT, requestBody, tokenThread.get());
		assertThat(response.statusCode(), is(equalTo(200)));

	}
}
