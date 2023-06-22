package com.example.BARStesting.dto.factorDTO;

import java.util.List;

public class CategoriesDTO {

    private List<String> category;


    public CategoriesDTO(List<String> category) {
        this.category = category;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoriesDTO{" +
                "category=" + category +
                '}';
    }
}
