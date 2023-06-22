package com.example.BARStesting.dto.factorDTO;

import java.util.List;

public class CountriesDTO {

    private List<String> country;


    public CountriesDTO(List<String> country) {
        this.country = country;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CountriesDTO{" +
                "country=" + country +
                '}';
    }
}
