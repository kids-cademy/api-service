package com.kidscademy.apiservice.model;

public class PhysicalTrait {
    private String name;
    private Double value;
    private Double maximum;
    private String quantity;

    public PhysicalTrait() {
    }

    public PhysicalTrait(String name, Double value) {
	this(name, value, "SCALAR");
    }

    public PhysicalTrait(String name, Double value, String quantity) {
	this(name, value, null, quantity);
    }

    public PhysicalTrait(String name, Double value, Double maximum, String quantity) {
	this.name = name;
	this.value = value;
	this.maximum = maximum;
	this.quantity = quantity;
    }

    public String getName() {
	return name;
    }

    public Double getValue() {
	return value;
    }

    public Double getMaximum() {
	return maximum;
    }

    public String getQuantity() {
	return quantity;
    }

    @Override
    public String toString() {
	return "PhysicalTrait [name=" + name + ", value=" + value + ", maximum=" + maximum + ", quantity=" + quantity
		+ "]";
    }
}
