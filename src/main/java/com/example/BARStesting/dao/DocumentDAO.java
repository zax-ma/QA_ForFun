package com.example.BARStesting.dao;

import com.example.BARStesting.dao.api.IDocumentDAO;
import com.example.BARStesting.dto.DocumentDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentDAO implements IDocumentDAO {

    private List<DocumentDTO> documents = new ArrayList<>();


    @Override
    public List<DocumentDTO> getDocumentList() {
        return new ArrayList<>(documents);
    }

    @Override
    public void save(DocumentDTO document) {
        documents.add(document);
    }

    @Override
    public DocumentDTO getDocument(int i) {
        return documents.get(i);
    }


}
