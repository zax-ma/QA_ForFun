package com.example.BARStesting.dao.api;

import com.example.BARStesting.dto.AttributeDTO;

import java.util.List;

public interface IAttributeDAO {

    List<AttributeDTO> getAttributeList();
    void save(AttributeDTO attribute);
    AttributeDTO getAttribute(int i);


}
