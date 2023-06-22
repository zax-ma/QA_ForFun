package com.example.BARStesting.service;

import com.example.BARStesting.dao.api.IRuleDAO;
import com.example.BARStesting.dto.ruleDTO.RuleDTO;
import com.example.BARStesting.service.api.IRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService implements IRuleService {

    private final IRuleDAO dao;

    public RuleService(IRuleDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(RuleDTO rule) {
        dao.save(rule);
    }

    @Override
    public List<RuleDTO> getAll() {
        return dao.getRuleList();
    }
}
