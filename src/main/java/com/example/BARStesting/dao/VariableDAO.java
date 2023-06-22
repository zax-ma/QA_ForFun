package com.example.BARStesting.dao;

import com.example.BARStesting.dao.api.IVariableDAO;
import com.example.BARStesting.dto.variableDTO.VariableDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VariableDAO implements IVariableDAO {

    private List<VariableDTO> variables = new ArrayList<>();

    @Override
    public List<VariableDTO> getVariableList() {
        return new ArrayList<>(variables);
    }

    @Override
    public void save(VariableDTO variable) {
        variables.add(variable);
    }

    @Override
    public VariableDTO variable(int i) {
        return variables.get(i);
    }
}
