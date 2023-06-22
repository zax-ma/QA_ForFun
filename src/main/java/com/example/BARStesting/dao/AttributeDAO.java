package com.example.BARStesting.dao;

import com.example.BARStesting.dao.api.IAttributeDAO;
import com.example.BARStesting.dto.AttributeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttributeDAO implements IAttributeDAO {

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
