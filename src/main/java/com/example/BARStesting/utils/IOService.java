package com.example.BARStesting.utils;

import com.example.BARStesting.dto.TaskDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class IOService {

    @Value("${tasks.path}")
    String path;

    public void saveToFile(String text, String to) throws IOException {

        FileWriter file = new FileWriter(path+to, true);

        Writer output;
        output = new BufferedWriter(file);
        output.append(text);
        output.append(System.lineSeparator());
        output.close();


    }

    public List<String> readFromFile(String from) {

        List<String> list = new ArrayList<>();

        File file = new File(path+from);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.lines().forEach(list::add);

        }   catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void delete(String file){

        File fileToDelete = new File(path + file);

        fileToDelete.delete();

    }
}
