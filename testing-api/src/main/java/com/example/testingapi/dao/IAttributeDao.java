package com.example.testingapi.dao;

import com.example.testingapi.dto.AttributeDTO;

import java.util.List;

public interface IAttributeDao {

    List<AttributeDTO> getAttributeList();
    void save(AttributeDTO attribute);
    AttributeDTO getAttribute(int i);
    
}
