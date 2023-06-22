package com.example.BARStesting.service.api;

import com.example.BARStesting.dto.ruleDTO.RuleDTO;

import java.util.List;

public interface IRuleService {

    void save(RuleDTO rule);

    List<RuleDTO> getAll();
}
