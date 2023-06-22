package com.example.BARStesting.service;

import com.example.BARStesting.dao.api.IAttributeDAO;
import com.example.BARStesting.dto.AttributeDTO;
import com.example.BARStesting.service.api.IAttributeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService implements IAttributeService {

    private IAttributeDAO dao;

    public AttributeService(IAttributeDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(AttributeDTO attribute) {
        dao.save(attribute);
    }

    @Override
    public List<AttributeDTO> getAll() {
        return dao.getAttributeList();
    }
}
