package com.kidscademy.apiservice.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LifeForm {
    private String commonName;
    private String scientificName;
    private String definition;
    /** Optional start date expressed in YA - years ago. Default to null. */
    private Double startDate;
    /** Optional end date expressed in YA - years ago. Default to null. */
    private Double endDate;
    private LinkedHashMap<String, String> taxonomy = new LinkedHashMap<>();
    private String conservationStatus;
    private List<String> description = new ArrayList<>();

    public String getCommonName() {
	return commonName;
    }

    public void setCommonName(String commonName) {
	this.commonName = commonName;
    }

    public String getScientificName() {
	return scientificName;
    }

    public void setScientificName(String scientificName) {
	this.scientificName = scientificName;
    }

    public String getDefinition() {
	return definition;
    }

    public void setDefinition(String definition) {
	this.definition = definition;
    }

    public List<String> getDescription() {
	return description;
    }

    public void setDescription(List<String> description) {
	this.description = description;
    }

    public Double getStartDate() {
	return startDate;
    }

    public void setStartDate(Double startDate) {
	this.startDate = startDate;
    }

    public Double getEndDate() {
	return endDate;
    }

    public void setEndDate(Double endDate) {
	this.endDate = endDate;
    }

    public String getConservationStatus() {
	return conservationStatus;
    }

    public void setConservationStatus(String conservationStatus) {
	this.conservationStatus = conservationStatus;
    }

    public void setTaxonomy(LinkedHashMap<String, String> taxonomy) {
	this.taxonomy = taxonomy;
    }

    public Map<String, String> getTaxonomy() {
	return taxonomy;
    }
}
