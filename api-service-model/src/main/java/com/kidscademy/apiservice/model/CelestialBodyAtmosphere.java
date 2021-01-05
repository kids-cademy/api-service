package com.kidscademy.apiservice.model;

import java.util.List;

public class CelestialBodyAtmosphere {
    private double surfacePressure;
    private List<ChemicalElementPercent> composition;

    public CelestialBodyAtmosphere() {

    }

    public CelestialBodyAtmosphere(double surfacePressure, List<ChemicalElementPercent> composition) {
	this.surfacePressure = surfacePressure;
	this.composition = composition;
    }

    public double getSurfacePressure() {
	return surfacePressure;
    }

    public List<ChemicalElementPercent> getComposition() {
	return composition;
    }
}
