package com.example.BARStesting.dto.factorDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "attribute",
        "categories",
        "countries",
        "highDistance",
        "ignoreHandbookWordsInUsualSearch",
        "isActive",
        "isDisableStatistic",
        "levenshteinDistance",
        "lowDistance",
        "maxResults",
        "method",
        "strictSearch",
        "value"
})
public class PropertiesOrdDTO {
    @JsonProperty("attribute")
    private String attribute;

    @JsonProperty("categories")
    private CategoriesDTO categories;

    @JsonProperty("countries")
    private CountriesDTO countries;

    @JsonProperty("highDistance")
    private int highDistance = 6;

    @JsonProperty("ignoreHandbookWordsInUsualSearch")
    private boolean ignoreHandbookWordsInUsualSearch = false;

    @JsonProperty("isActive")
    private boolean isActive = true;

    @JsonProperty("isDisableStatistic")
    private boolean isDisableStatistic = true;

    @JsonProperty("levenshteinDistance")
    private String levenshteinDistance;

    @JsonProperty("lowDistance")
    private int lowDistance = 3;

    @JsonProperty("maxResults")
    private int maxResults = 100;

    @JsonProperty("method")
    private String method;

    @JsonProperty("strictSearch")
    private boolean strictSearch;

    @JsonProperty("value")
    private String value;

    public PropertiesOrdDTO() {
    }

    public PropertiesOrdDTO(String attribute,
                            CategoriesDTO categories,
                            CountriesDTO countries,
                            boolean isActive,
                            boolean isDisableStatistic,
                            String levenshteinDistance,
                            String method,
                            boolean strictSearch,
                            String value) {
        this.attribute = attribute;
        this.categories = categories;
        this.countries = countries;
        this.isActive = isActive;
        this.isDisableStatistic = isDisableStatistic;
        this.levenshteinDistance = levenshteinDistance;
        this.method = method;
        this.strictSearch = strictSearch;
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

    public CountriesDTO getCountries() {
        return countries;
    }

    public void setCountries(CountriesDTO countries) {
        this.countries = countries;
    }

    public int getHighDistance() {
        return highDistance;
    }

    public void setHighDistance(int highDistance) {
        this.highDistance = highDistance;
    }

    public boolean isIgnoreHandbookWordsInUsualSearch() {
        return ignoreHandbookWordsInUsualSearch;
    }

    public void setIgnoreHandbookWordsInUsualSearch(boolean ignoreHandbookWordsInUsualSearch) {
        this.ignoreHandbookWordsInUsualSearch = ignoreHandbookWordsInUsualSearch;
    }

    public String getLevenshteinDistance() {
        return levenshteinDistance;
    }

    public void setLevenshteinDistance(String levenshteinDistance) {
        this.levenshteinDistance = levenshteinDistance;
    }

    public int getLowDistance() {
        return lowDistance;
    }

    public void setLowDistance(int lowDistance) {
        this.lowDistance = lowDistance;
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

    public boolean isStrictSearch() {
        return strictSearch;
    }

    public void setStrictSearch(boolean strictSearch) {
        this.strictSearch = strictSearch;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PropertiesDTO{" +
                "attribute='" + attribute + '\'' +
                ", categories=" + categories +
                ", countries=" + countries +
                ", highDistance=" + highDistance +
                ", ignoreHandbookWordsInUsualSearch=" + ignoreHandbookWordsInUsualSearch +
                ", isActive=" + isActive +
                ", isDisableStatistic=" + isDisableStatistic +
                ", levenshteinDistance='" + levenshteinDistance + '\'' +
                ", lowDistance=" + lowDistance +
                ", maxResults=" + maxResults +
                ", method='" + method + '\'' +
                ", strictSearch=" + strictSearch +
                ", value='" + value + '\'' +
                '}';
    }
}
