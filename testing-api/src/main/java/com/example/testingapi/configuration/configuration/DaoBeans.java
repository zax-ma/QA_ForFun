package com.example.testingapi.configuration.configuration;

import com.example.testingapi.dao.AttributeDao;
import com.example.testingapi.dao.IAttributeDao;
import org.springframework.context.annotation.Bean;

public class DaoBeans {
    @Bean
    public IAttributeDao dao(){
        return new AttributeDao();
    }
}
