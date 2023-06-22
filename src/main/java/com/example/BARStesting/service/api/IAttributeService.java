package com.example.BARStesting.service.api;

import com.example.BARStesting.dto.AttributeDTO;

import java.util.List;

public interface IAttributeService {

    void save(AttributeDTO attribute);

    List<AttributeDTO> getAll();

}
