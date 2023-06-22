package com.example.BARStesting.service;

import com.example.BARStesting.dao.api.IDocumentDAO;
import com.example.BARStesting.dto.DocumentDTO;
import com.example.BARStesting.service.api.IDocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService implements IDocumentService {

    private final IDocumentDAO dao;

    public DocumentService(IDocumentDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(DocumentDTO document) {
        dao.save(document);
    }

    @Override
    public List<DocumentDTO> getAll() {
        return dao.getDocumentList();
    }
}


