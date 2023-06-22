package com.example.BARStesting.transactionTest;


import com.example.BARStesting.dto.TaskDTO;
import com.example.BARStesting.dto.TransactionDTO;
import com.example.BARStesting.utils.IOService;
import com.example.BARStesting.service.requests.CheckService;
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
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionForWlcTest {

    private final TransactionDTO transaction = new TransactionDTO();

    private static final String END_POINT = "/putCommon";

    @Value("${wlc-transaction.precheck}")
    String precheck;

    @Value("${wlc-transaction.fields.name}")
    String name;

    @Value("${wlc-transaction.fields.value}")
    String value;

    @Value("${document.name}")
    String docType;

    @Value("${wlc-transaction.fields.doc-source}")
    String docSource;

    @Value("${tasks.file-name-to-save-tasks}")
    String saveTo;

    String seq_num = null;

    @Autowired
    CheckService service;

    @Autowired
    IOService IOService;

    @BeforeAll
    public void taskObject() {

        seq_num = UUID.randomUUID().toString();

        transaction.setDocId(seq_num);
        transaction.setDocSource(docSource);
        transaction.setDocType(docType);
        transaction.setPrecheck(precheck);

    }

    @Test
    public void createWlcTask() throws IOException {

        ObjectMapper mapper =  new ObjectMapper();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String raw_json = ow.writeValueAsString(transaction);

        ObjectNode json = mapper.readValue(raw_json, ObjectNode.class);
            json.put(name, value);

        TaskDTO task = new TaskDTO();

        task.setDocId(transaction.getDocId());
        task.setDocSource(transaction.getDocSource());

        ObjectWriter ow_task = new ObjectMapper().writer().withRootValueSeparator("\n");
        String task_json = ow_task.writeValueAsString(task);

        Response response = service.post(END_POINT, json);
        assertThat(response.statusCode(), is(equalTo(200)));

        IOService.saveToFile(task_json, saveTo);

    }

}
