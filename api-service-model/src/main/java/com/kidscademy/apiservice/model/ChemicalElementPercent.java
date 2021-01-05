package com.kidscademy.apiservice.model;

public class ChemicalElementPercent {
    private String name;
    private double percent;

    public ChemicalElementPercent() {

    }

    public ChemicalElementPercent(String name, double percent) {
	this.name = name;
	this.percent = percent;
    }

    public String getName() {
	return name;
    }

    public double getPercent() {
	return percent;
    }
}
