package com.example.BARStesting.service.api;

import com.example.BARStesting.dto.factorDTO.FactorDTO;

import java.util.List;

public interface IFactorService {

    void save(FactorDTO factor);

    List<FactorDTO> getAll();

}
