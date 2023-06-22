package com.example.BARStesting.dao.api;

import com.example.BARStesting.dto.DocumentDTO;

import java.util.List;

public interface IDocumentDAO {

    List<DocumentDTO> getDocumentList();
    void save(DocumentDTO document);
    DocumentDTO getDocument(int i);

}
