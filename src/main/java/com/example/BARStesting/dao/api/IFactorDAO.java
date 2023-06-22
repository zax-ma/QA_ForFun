package com.example.BARStesting.dao.api;

import com.example.BARStesting.dto.factorDTO.FactorDTO;

import java.util.List;

public interface IFactorDAO {

    List<FactorDTO> getFactorList();
    void save(FactorDTO factor);
    FactorDTO factor(int i);

}
