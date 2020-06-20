package com.kidscademy.apiservice.model;

import java.util.Map;

public class EdiblePlant extends LifeForm {
    private Map<String, Double> nutritionalValue;

    public Map<String, Double> getNutritionalValue() {
	return nutritionalValue;
    }

    public void setNutritionalValue(Map<String, Double> nutritionalValue) {
	this.nutritionalValue = nutritionalValue;
    }
}
