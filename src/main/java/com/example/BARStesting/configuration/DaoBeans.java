package com.example.BARStesting.configuration;

import com.example.BARStesting.dao.*;
import com.example.BARStesting.dao.api.*;
import org.springframework.context.annotation.Bean;

public class DaoBeans {

    @Bean
    public IAttributeDAO attributeDao(){ return new AttributeDAO(); }

    @Bean
    public IDocumentDAO documentDao(){
        return new DocumentDAO();
    }

    @Bean
    public IFactorDAO factorDao(){
        return new FactorDAO();
    }

    @Bean
    public IVariableDAO variableDAO(){ return new VariableDAO(); }

    @Bean
    public IRuleDAO ruleDAO(){ return new RuleDAO(); }

}
