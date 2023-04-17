package com.example.testingapi.dao;

import com.example.testingapi.dto.AttributeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttributeDao implements IAttributeDao {

    private List<AttributeDTO> attributes = new ArrayList<>();

    @Override
    public List<AttributeDTO> getAttributeList() {
        return new ArrayList<>(attributes);
    }

    @Override
    public void save(AttributeDTO attribute) {
        attributes.add(attribute);
    }

    @Override
    public AttributeDTO getAttribute(int i) {
        return attributes.get(i);
    }
}
