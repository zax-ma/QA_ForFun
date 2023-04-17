package com.example.testingapi.service;

import com.example.testingapi.dao.IAttributeDao;
import com.example.testingapi.dto.AttributeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService implements IAttributeService{
public IAttributeDao dao;

    public AttributeService(IAttributeDao dao) {
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
