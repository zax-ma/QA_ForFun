package com.example.BARStesting.service.api;

import com.example.BARStesting.dto.factorDTO.FactorDTO;
import com.example.BARStesting.dto.variableDTO.VariableDTO;

import java.util.List;

public interface IVariableService {

    void save(VariableDTO variable);

    List<VariableDTO> getAll();

}
