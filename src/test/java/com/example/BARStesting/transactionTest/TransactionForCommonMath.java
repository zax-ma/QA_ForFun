package com.example.BARStesting.transactionTest;

import com.example.BARStesting.dto.TaskDTO;
import com.example.BARStesting.dto.TransactionDTO;
import com.example.BARStesting.service.requests.CheckService;
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
public class TransactionForCommonMath {

    private final TransactionDTO transaction = new TransactionDTO();

    private static final String END_POINT = "/putCommon";

    @Value("${math-one-transaction.precheck}")
    String precheck;

    @Value("${math-one-transaction.fields.name}")
    String name;

    @Value("${math-one-transaction.fields.value}")
    String value;

    @Value("${math-one-transaction.fields.doc-source}")
    String docSource;

    @Value("${math-one-transaction.fields.sum}")
    String addField;

    @Value("${math-one-transaction.fields.value_sum}")
    String addValue;

    @Value("${document.name}")
    String docType;

    @Value("${tasks.file-name-to-save-tasks}")
    String saveTo;

    String seq_num = null;

    @Autowired
    CheckService service;

    @Autowired
    com.example.BARStesting.utils.IOService IOService;

    @BeforeAll
    public void taskObject() {

        seq_num = UUID.randomUUID().toString();

        transaction.setDocId(seq_num);
        transaction.setDocSource(docSource);
        transaction.setDocType(docType);
        transaction.setPrecheck(precheck);

    }

    @Test
    public void createMathOneTask() throws IOException {

        ObjectMapper mapper =  new ObjectMapper();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String raw_json = ow.writeValueAsString(transaction);

        ObjectNode json = mapper.readValue(raw_json, ObjectNode.class);
        json.put(name, value);
        json.put(addField, addValue);

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
