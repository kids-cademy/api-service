package com.kidscademy.apiservice.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kidscademy.apiservice.model.PhysicalTrait;

import js.dom.Document;
import js.dom.EList;
import js.dom.Element;
import js.lang.BugError;
import js.util.Strings;

public class AnimaliaPhysicalTraitsParser implements Parser<List<PhysicalTrait>> {

    @Override
    public List<PhysicalTrait> parse(Document document) {
	List<PhysicalTrait> traits = new ArrayList<>();

	EList traitElements = document.findByCssClass("s-char-char__wrap");
	if (traitElements.isEmpty()) {
	    traitElements = document
		    .findByXPath("//DIV[@class='s-char-char__block']/DIV[@class='row']/DIV[@class='col-6']");
	}

	for (Element traitElement : traitElements) {
	    EList children = traitElement.getChildren();
	    String traitName = name(children.item(0).getText());
	    String traitValue = children.item(1).getText();

	    String[] valueParts = parts(traitValue);
	    Meta meta = null;

	    try {
		switch (valueParts.length) {
		case 1:
		    meta = new Meta("SCALAR", 1.0);
		    traits.add(new PhysicalTrait(traitName, parseDouble(valueParts[0], meta), meta.quantity));
		    break;

		case 2:
		    meta = meta(valueParts[1]);
		    traits.add(new PhysicalTrait(traitName, parseDouble(valueParts[0], meta), meta.quantity));
		    break;

		case 3:
		    meta = meta(valueParts[2]);
		    traits.add(new PhysicalTrait(traitName, parseDouble(valueParts[0], meta),
			    parseDouble(valueParts[1], meta), meta.quantity));
		    break;
		}
	    } catch (NumberFormatException ignore) {
		// on number format error ignore trait element
		// possible cause is 'UNKNOWN' value, for example for population size
	    }
	}

	return traits;
    }

    private static String[] parts(String value) {
	value = value.toUpperCase();
	value = value.replace("BELOW ", "");
	value = value.replace("ABOVE ", "");
	value = value.replace(" TO ", "-");
	return Strings.split(value, '-', ' ').toArray(new String[0]);
    }

    private static Map<String, String> NAME = new HashMap<>();
    static {
	NAME.put("Population size", "population.size");
	NAME.put("Life Span", "lifespan");
	NAME.put("TOP SPEED", "speed.full");
	NAME.put("HEIGHT", "height");
	NAME.put("WEIGHT", "weight");
	NAME.put("LENGTH", "length");
    }

    private static String name(String name) {
	String normalizedName = NAME.get(name);
	if (normalizedName == null) {
	    throw new BugError("Not handled name |%s|.", name);
	}
	return normalizedName;
    }

    private static Map<String, Meta> META = new HashMap<>();
    static {
	META.put("YRS", new Meta("TIME", 31556952.0));
	META.put("YEARS", new Meta("TIME", 31556952.0));
	META.put("KM/H", new Meta("SPEED", 1.0 / 3.6));
	META.put("MPH", new Meta("SPEED", 1.0 / 2.23693629));
	META.put("G", new Meta("MASS", 0.001));
	META.put("KG", new Meta("MASS", 1.0));
	META.put("T", new Meta("MASS", 1000));
	META.put("MM", new Meta("LENGTH", 0.001));
	META.put("CM", new Meta("LENGTH", 0.01));
	META.put("M", new Meta("LENGTH", 1.0));
	META.put("THOU", new Meta("SCALAR", 1000.0));
	META.put("MLN", new Meta("SCALAR", 1000000.0));
    }

    private static Meta meta(String units) {
	Meta meta = META.get(units.toUpperCase());
	if (meta == null) {
	    throw new BugError("Not handled units |%s|.", units);
	}
	return meta;
    }

    private static Double parseDouble(String value, Meta meta) {
	return round(Double.parseDouble(value.replaceAll(",", "")) * meta.factor);
    }

    private static double round(double value) {
	return Math.round(value * 1000.0) / 1000.0;
    }

    private static class Meta {
	final String quantity;
	final double factor;

	public Meta(String quantity, double factor) {
	    this.quantity = quantity;
	    this.factor = factor;
	}
    }
}
