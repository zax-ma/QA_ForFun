package com.example.BARStesting.transactionTest;

import com.example.BARStesting.dto.TaskDTO;
import com.example.BARStesting.dto.TransactionDTO;
import com.example.BARStesting.service.requests.ControlService;
import com.example.BARStesting.service.requests.DictionaryService;
import com.example.BARStesting.utils.IOService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionForMathTest {

    private final TransactionDTO transaction = new TransactionDTO();

    private static final String END_POINT = "/statistics/send";

    @Value("${math-transaction.precheck}")
    String precheck;

    @Value("${math-transaction.fields.sum}")
    String sum;

    @Value("${math-transaction.fields.value}")
    String value;

    @Value("${math-transaction.fields.client}")
    String client;

    @Value("${math-transaction.fields.name}")
    String name;

    @Value("${document.name}")
    String docType;

    @Value("${math-transaction.fields.doc-source}")
    String doc_source;

    @Value("${tasks.file-name-to-save-tasks}")
    String saveTo;

    private static final String seq_num = UUID.randomUUID().toString();

    @Autowired
    DictionaryService service;

    @Autowired
    IOService IOService;

    @BeforeAll
    public void taskObject() {

        transaction.setDocId(seq_num);
        transaction.setDocSource(doc_source);
        transaction.setDocType(docType);
        transaction.setPrecheck(precheck);

    }

    @Test
    public void createMathTask() throws IOException {

        ObjectMapper mapper =  new ObjectMapper();
        ObjectWriter ow = new ObjectMapper().writer();

        String raw_json = ow.writeValueAsString(transaction);

        ObjectNode json = mapper.readValue(raw_json, ObjectNode.class);
        json.put(sum, value);
        json.put(client, name);

        TaskDTO task = new TaskDTO();

        task.setDocId(transaction.getDocId());
        task.setDocSource(transaction.getDocSource());

        String task_json = ow.withRootValueSeparator("\n").writeValueAsString(task);

        Response response = service.postStatistic(END_POINT, json);
        assertThat(response.statusCode(), is(equalTo(200)));

        IOService.saveToFile(task_json, saveTo);
    }
}
