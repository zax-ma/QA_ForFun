package com.example.BARStesting.dto.factorDTO;

public class PropertiesMathDTO {

    String program;

    public PropertiesMathDTO() {
    }

    public PropertiesMathDTO(String program) {
        this.program = program;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "PropertiesMathDTO{" +
                "program='" + program + '\'' +
                '}';
    }
}
