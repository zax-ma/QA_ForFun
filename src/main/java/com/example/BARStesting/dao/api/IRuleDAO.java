package com.example.BARStesting.dao.api;

import com.example.BARStesting.dto.factorDTO.FactorDTO;
import com.example.BARStesting.dto.ruleDTO.RuleDTO;

import java.util.List;

public interface IRuleDAO {

    List<RuleDTO> getRuleList();
    void save(RuleDTO rule);
    RuleDTO rule(int i);

}
