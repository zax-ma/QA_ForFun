package com.example.BARStesting.dao;

import com.example.BARStesting.dao.api.IRuleDAO;
import com.example.BARStesting.dto.ruleDTO.RuleDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RuleDAO implements IRuleDAO {

    private List<RuleDTO> rules = new ArrayList<>();

    @Override
    public List<RuleDTO> getRuleList() {
        return new ArrayList<>(rules);
    }

    @Override
    public void save(RuleDTO rule) {
        rules.add(rule);
    }

    @Override
    public RuleDTO rule(int i) {
        return rules.get(i);
    }
}
