package com.kidscademy.apiservice.parser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kidscademy.apiservice.util.Strings;

import js.dom.Document;
import js.dom.EList;
import js.dom.Element;

public class WikipediaNutrientsParser implements Parser<LinkedHashMap<String, Double>> {

    @Override
    public LinkedHashMap<String, Double> parse(Document document) {
	LinkedHashMap<String, Double> nutritionalValues = new LinkedHashMap<>();

	EList infoboxes = document.findByXPath("//TABLE[contains(@class,'infobox')]");
	for (Element infobox : infoboxes) {
	    Element heading = infobox.getByTag("th");
	    if (heading == null) {
		continue;
	    }
	    if (heading.getText().startsWith("Nutritional value")) {
		loadNutritionalValues(nutritionalValues, infobox);
		break;
	    }
	}

	return nutritionalValues;
    }

    private static void loadNutritionalValues(Map<String, Double> nutritionalValues, Element infobox) {
	EList rows = infobox.findByTag("tr");
	for (int i = 1; i < rows.size(); ++i) {
	    Element row = rows.item(i);
	    String label = label(row.getByTag("th"));
	    if (label != null) {
		Double value = value(row.getByTag("td").getText());
		if (value != null) {
		    nutritionalValues.put(label, value);
		}
	    }
	}

	Double energy = nutritionalValues.get("Energy");
	if (energy != null) {
	    nutritionalValues.put("Calories", Math.round(energy * 239) / 1000.0);
	}
    }

    private static Map<String, String> ALIASES = new HashMap<>();
    {
	ALIASES.put("Dietary Fibre", "Dietary Fiber");
    }

    private static String label(Element th) {
	if (th == null) {
	    return null;
	}
	String text = th.hasChildren() ? th.getFirstChild().getText() : th.getText();

	// it can happen to have 'equiv.' label at the end; remove it
	int subvitaminsPosition = text.indexOf("equiv.");
	if (subvitaminsPosition != -1) {
	    text = text.substring(0, subvitaminsPosition);
	}

	String label = Strings.toTitleCase(Strings.removeParentheses(text).trim());
	String alias = ALIASES.get(label);
	return alias != null ? alias : label;
    }

    // there are two spellings for micro grams
    // even if in source code seems the same, UTF codes are different
    private static final Pattern VALUE = Pattern.compile("(\\d+(?:\\.\\d+)?)[\\s\\u00A0]+(kj|g|mg|μg|µg)",
	    Pattern.CASE_INSENSITIVE);

    private static Double value(String text) {
	Matcher matcher = VALUE.matcher(text);
	if (!matcher.find()) {
	    return null;
	}
	double value = Double.parseDouble(matcher.group(1));
	// value is a scalar
	// it represents quantity in grams measured on 100 grams of food
	// for this we need to convert milli-, micro- and grams to kilograms
	// value should be in standard units
	switch (matcher.group(2).toLowerCase()) {
	case "g":
	    value = round(value, 1000000.0);
	    break;
	    
	case "mg":
	    value = round(value, 1000000000.0);
	    break;

	// there are two spellings for micro grams
	// even if in source code seems the same, UTF codes are different
	case "μg":
	case "µg":
	    value = round(value, 1000000000000.0);
	    break;
	}
	return value;
    }

    private static double round(double value, double factor) {
	return Math.round(value * 1000.0) / factor;
    }
}
