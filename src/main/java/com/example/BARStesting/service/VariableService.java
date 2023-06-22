package com.example.BARStesting.service;

import com.example.BARStesting.dao.api.IVariableDAO;
import com.example.BARStesting.dto.variableDTO.VariableDTO;
import com.example.BARStesting.service.api.IVariableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariableService implements IVariableService {

    private final IVariableDAO dao;

    public VariableService(IVariableDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(VariableDTO variable) {
        dao.save(variable);
    }

    @Override
    public List<VariableDTO> getAll() {
        return dao.getVariableList();
    }
}
