package com.kidscademy.apiservice.model;

import java.util.LinkedHashMap;

public class EdiblePlant extends LifeForm {
    private LinkedHashMap<String, Double> nutrients;

    public LinkedHashMap<String, Double> getNutrients() {
	return nutrients;
    }

    public void setNutrients(LinkedHashMap<String, Double> nutritionalValue) {
	this.nutrients = nutritionalValue;
    }
}
