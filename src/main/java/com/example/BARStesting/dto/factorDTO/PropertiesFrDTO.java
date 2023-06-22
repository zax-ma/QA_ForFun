package com.example.BARStesting.dto.factorDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "attribute",
        "categories",
        "ignoreHandbookWordsInSearchByFragment",
        "isActive",
        "isDisableStatistic",
        "maxResults",
        "method",
        "value"
})
public class PropertiesFrDTO {

    @JsonProperty("attribute")
    private String attribute;

    @JsonProperty("categories")
    private CategoriesDTO categories;

    @JsonProperty("ignoreHandbookWordsInSearchByFragment")
    private boolean ignoreHandbookWordsInSearchByFragment = false;

    @JsonProperty("isActive")
    private boolean isActive = true;

    @JsonProperty("isDisableStatistic")
    private boolean isDisableStatistic = true;

    @JsonProperty("maxResults")
    private int maxResults = 100;

    @JsonProperty("method")
    private String method;

    @JsonProperty("value")
    private String value;

    public PropertiesFrDTO() {
    }

    public PropertiesFrDTO(String attribute,
                           CategoriesDTO categories,
                           boolean ignoreHandbookWordsInSearchByFragment,
                           boolean isActive,
                           boolean isDisableStatistic,
                           int maxResults,
                           String method,
                           String value) {
        this.attribute = attribute;
        this.categories = categories;
        this.ignoreHandbookWordsInSearchByFragment = ignoreHandbookWordsInSearchByFragment;
        this.isActive = isActive;
        this.isDisableStatistic = isDisableStatistic;
        this.maxResults = maxResults;
        this.method = method;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public CategoriesDTO getCategories() {
        return categories;
    }

    public void setCategories(CategoriesDTO categories) {
        this.categories = categories;
    }

    public boolean ignoreHandbookWordsInSearchByFragment() {
        return ignoreHandbookWordsInSearchByFragment;
    }

    public void ignoreHandbookWordsInSearchByFragment(boolean ignoreHandbookWordsInSearchByFragment) {
        this.ignoreHandbookWordsInSearchByFragment = ignoreHandbookWordsInSearchByFragment;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
