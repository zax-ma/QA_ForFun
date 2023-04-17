package com.example.testingapi.service;

import com.example.testingapi.dto.AttributeDTO;

import java.util.List;

public interface IAttributeService {

    void save(AttributeDTO attribute);

    List<AttributeDTO> getAll();

}
