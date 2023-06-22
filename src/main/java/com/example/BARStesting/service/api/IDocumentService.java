package com.example.BARStesting.service.api;

import com.example.BARStesting.dto.DocumentDTO;

import java.util.List;

public interface IDocumentService {

    void save(DocumentDTO document);

    List<DocumentDTO> getAll();
}
