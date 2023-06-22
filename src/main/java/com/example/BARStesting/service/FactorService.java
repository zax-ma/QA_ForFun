package com.example.BARStesting.service;

import com.example.BARStesting.dao.api.IFactorDAO;
import com.example.BARStesting.dto.factorDTO.FactorDTO;
import com.example.BARStesting.service.api.IFactorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactorService implements IFactorService {

    private final IFactorDAO dao;

    public FactorService(IFactorDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(FactorDTO factor) {
        dao.save(factor);
    }

    @Override
    public List<FactorDTO> getAll() {
        return dao.getFactorList();
    }
}
