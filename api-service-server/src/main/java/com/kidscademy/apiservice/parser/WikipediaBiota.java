package com.kidscademy.apiservice.parser;

import java.util.LinkedHashMap;

public class WikipediaBiota {
    private String scientificName;
    /** Optional start date expressed in YA - years ago. Default to null. */
    private Double startDate;
    /** Optional end date expressed in YA - years ago. Default to null. */
    private Double endDate;
    private LinkedHashMap<String, String> taxonomy = new LinkedHashMap<>();
    private String conservationStatus;

    public String getScientificName() {
	return scientificName;
    }

    public void setScientificName(String scientificName) {
	this.scientificName = scientificName;
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

    public LinkedHashMap<String, String> getTaxonomy() {
	return taxonomy;
    }

    public void setTaxonomy(LinkedHashMap<String, String> taxonomy) {
	this.taxonomy = taxonomy;
    }

    public String getConservationStatus() {
	return conservationStatus;
    }

    public void setConservationStatus(String conservationStatus) {
	this.conservationStatus = conservationStatus;
    }
}
