package com.example.BARStesting.dao;

import com.example.BARStesting.dao.api.IFactorDAO;
import com.example.BARStesting.dto.factorDTO.FactorDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FactorDAO implements IFactorDAO {

    private List<FactorDTO> factors = new ArrayList<>();


    @Override
    public List<FactorDTO> getFactorList() {
        return new ArrayList<>(factors);
    }

    @Override
    public void save(FactorDTO factor) {
        factors.add(factor);
    }

    @Override
    public FactorDTO factor(int i) {
        return factors.get(i);
    }
}
