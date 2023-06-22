package com.example.BARStesting.dao.api;

import com.example.BARStesting.dto.factorDTO.FactorDTO;
import com.example.BARStesting.dto.variableDTO.VariableDTO;

import java.util.List;

public interface IVariableDAO {

    List<VariableDTO> getVariableList();
    void save(VariableDTO factor);
    VariableDTO variable(int i);

}
