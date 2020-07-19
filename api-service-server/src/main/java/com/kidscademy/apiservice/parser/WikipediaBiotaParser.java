package com.kidscademy.apiservice.parser;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kidscademy.apiservice.util.Strings;

import js.dom.Document;
import js.dom.EList;
import js.dom.Element;

class WikipediaBiotaParser implements Parser<WikipediaBiota> {
    @Override
    public WikipediaBiota parse(Document document) {
	WikipediaBiota biota = new WikipediaBiota();

	EList biotaTable = document.findByXPath("//TABLE[contains(@class,'biota')]/TBODY/TR");

	BiotaSection section = BiotaSection.NONE;
	String scientificName = null;
	boolean trinomialName = false;
	LinkedHashMap<String, String> taxonomy = new LinkedHashMap<>();

	for (Element element : biotaTable) {
	    String text = element.getText().trim();

	    Element header = element.getByTag("TH");
	    if (header == null) {
		// if section header is null we are in section body

		switch (section) {
		case CONSERVATION:
		    biota.setConservationStatus(conservation(element));
		    break;

		case TAXONOMY:
		    Map.Entry<String, String> taxon = taxon(element);
		    if (taxon != null && taxon.getValue() != null) {
			taxonomy.put(taxon.getKey(), taxon.getValue());
		    }
		    break;

		case TRINOMIAL:
		    trinomialName = true;
		    scientificName = Strings.text(element.getByTag("B"));
		    break;

		case BINOMIAL:
		case SPECIES:
		    // is possible to have distribution map mixed with name section
		    // keep first not null text value
		    if (scientificName == null) {
			scientificName = Strings.text(element.getByTag("I"));
		    }
		    break;

		default:
		    break;
		}

		continue;
	    }

	    section = BiotaSection.NONE;
	    if (text.contains("Temporal range")) {
		// temporal range is included on header and need to be handled here
		updateTemporalRange(header, biota);
		continue;
	    }

	    // detect current section
	    // note that biota table sections order is not defined
	    if (text.contains("Conservation status")) {
		section = BiotaSection.CONSERVATION;
	    } else if (text.contains("Scientific classification")) {
		section = BiotaSection.TAXONOMY;
	    } else if (text.contains("Type species") || text.contains("Species")) {
		section = BiotaSection.SPECIES;
	    } else if (text.contains("Trinomial name")) {
		section = BiotaSection.TRINOMIAL;
	    } else if (text.contains("Binomial name")) {
		section = BiotaSection.BINOMIAL;
	    }
	}

	biota.setScientificName(normalizeScientificName(taxonomy, scientificName, trinomialName));
	// set taxonomy here; normalizeScientificName has side effect on taxonomy
	biota.setTaxonomy(taxonomy);

	return biota;
    }

    private static void updateTemporalRange(Element header, WikipediaBiota biota) {
	String text = header.getText();
	int separatorIndex = text.indexOf(':') + 2;
	if (separatorIndex == 1) {
	    return;
	}
	text = text.substring(separatorIndex);

	Matcher matcher = null;
	boolean found = false;
	for (Pattern pattern : TEMPORAL_RANGE_PATTERNS) {
	    matcher = pattern.matcher(text);
	    if (matcher.find()) {
		found = true;
		break;
	    }
	}
	if (!found) {
	    return;
	}

	if (matcher.groupCount() == 2) {
	    Double value = number(matcher.group(1));
	    if (value == null) {
		return;
	    }
	    String unit = matcher.group(2);
	    if ("years ago".equals(unit)) {
		value = value / 1000;
		unit = "ka";
	    }
	    biota.setStartDate(date(value, unit));
	    return;
	}

	Double startValue = number(matcher.group(1));
	if (startValue == null) {
	    return;
	}
	Double endValue = number(matcher.group(2));
	if (endValue == null) {
	    return;
	}
	String unit = matcher.group(3).toLowerCase();
	if ("years ago".equals(unit)) {
	    startValue = startValue / 1000;
	    endValue = endValue / 1000;
	    unit = "ka";
	}
	biota.setStartDate(date(startValue, unit));
	biota.setEndDate(date(endValue, unit));
    }

    private static String normalizeScientificName(Map<String, String> taxonomy, String scientificName,
	    boolean trinomialName) {
	if (scientificName == null) {
	    return null;
	}

	// scientific name contains variety
	if (scientificName.contains("var.")) {
	    String[] parts = scientificName.split(" ");
	    parts[1] = Strings.toTitleCase(parts[1]);
	    parts[3] = Strings.toTitleCase(parts[3]);
	    updateTaxon(taxonomy, "species", parts[1]);
	    updateTaxon(taxonomy, "variety", parts[3]);
	    return Strings.join(parts);
	}

	// normalize scientific name
	// it can happen to have scientific name using shorthand for genus
	if (scientificName.contains(".")) {
	    String[] parts = scientificName.split(" ");
	    Map.Entry<String, String> genus = taxon(taxonomy, "genus");
	    if (genus != null) {
		parts[0] = genus.getValue();
	    }
	    scientificName = Strings.join(parts);
	}

	// normalize taxonomy
	// update species and subspecies since Wikipedia uses shorthand genus

	String speciesValue = scientificName;
	String subspeciesValue = null;
	if (trinomialName) {
	    int lastSeparator = scientificName.lastIndexOf(' ');
	    if (lastSeparator == -1) {
		return scientificName;
	    }
	    speciesValue = scientificName.substring(0, lastSeparator).trim();
	    subspeciesValue = scientificName;
	}

	// it seems there is convention to use both genus and species for species taxon
	// this implementation uses only simple species name and uses qualified name for
	// binomial and trinomial names, aka scientific name
	// the same for sub-species

	speciesValue = Strings.toTitleCase(Strings.last(speciesValue, ' '));
	updateTaxon(taxonomy, "species", speciesValue);

	if (subspeciesValue == null) {
	    return scientificName;
	}
	subspeciesValue = Strings.toTitleCase(Strings.last(subspeciesValue, ' '));
	updateTaxon(taxonomy, "subspecies", subspeciesValue);

	return scientificName;
    }

    private static void updateTaxon(Map<String, String> taxonomy, String taxonName, String taxonValue) {
	Map.Entry<String, String> taxon = taxon(taxonomy, taxonName);
	if (taxon != null) {
	    taxon.setValue(taxonValue);
	} else {
	    taxon = new AbstractMap.SimpleEntry<>(taxonName, taxonValue);
	    taxonomy.put(taxon.getKey(), taxon.getValue());
	}
    }

    private static Map.Entry<String, String> taxon(Map<String, String> taxonomy, String taxonName) {
	for (Map.Entry<String, String> taxon : taxonomy.entrySet()) {
	    if (taxon.getKey().equals(taxonName)) {
		return taxon;
	    }
	}
	return null;
    }

    private static Map.Entry<String, String> taxon(Element row) {
	String name = row.getByXPath("TD[1]").getTextContent().trim();
	if (name.charAt(name.length() - 1) == ':') {
	    name = name.substring(0, name.length() - 1);
	}

	// it seems Wikipedia author(s) mix two taxonomic systems:
	// Linnaean taxonomy and Cladistic method

	// anyway clade name seems to have italic with XPath TD[1]/I, in which
	// case current name variable is empty; return null to reject clades
	if (name.isEmpty()) {
	    return null;
	}

	return new AbstractMap.SimpleEntry<>(name.toLowerCase(), text(row, TAXON_XPATHS));
    }

    private static String conservation(Element row) {
	return text(row, CONSERVATION_XPATHS);
    }

    private static String text(Element element, String[] xpaths) {
	for (String xpath : xpaths) {
	    boolean strict = true;
	    if (xpath.charAt(0) == '~') {
		strict = false;
		xpath = xpath.substring(1);
	    }

	    Element textElement = element.getByXPath(xpath);
	    if (textElement != null) {
		String text = strict ? textElement.getTextContent() : textElement.getText();
		text = text.trim();
		if (!text.isEmpty()) {
		    return text;
		}
	    }
	}
	return null;
    }

    private static Double number(String value) {
	NumberFormat format = NumberFormat.getNumberInstance(Locale.ENGLISH);
	try {
	    return format.parse(value).doubleValue();
	} catch (ParseException e) {
	    return null;
	}
    }

    private static Double date(Double value, String units) {
	switch (units) {
	case "years ago":
	    return value;

	case "ka":
	    return value * 1000;

	case "ma":
	    return value * 1000000;

	case "ga":
	    return value * 1000000000;
	}
	return null;
    }

    /**
     * Biota infobox is a table with multiple sections. A section starts with a
     * table row containing a TH element. Section order is not defined.
     * 
     * @author Iulian Rotaru
     */
    private static enum BiotaSection {
	NONE, CONSERVATION, TAXONOMY, SPECIES, TRINOMIAL, BINOMIAL
    }

    /**
     * Regular expression patterns for temporal range values. Temporal range value
     * contains numerical value(s) and related measurement unit, mixed with some
     * text - not relevant for this case.
     * <p>
     * Currently there are two major types: range with both start and end date, or
     * only start date till present. First pattern has two numeric values followed
     * by unit; the second pattern has only one numeric value and units.
     * <p>
     * Numeric value is English formatted and may have grouping and decimals. Unit
     * names are also English for years ago and multiples.
     * <p>
     * Both patterns uses special - non ASCII, characters for units space and dash
     * range separator.
     */
    private static final Pattern[] TEMPORAL_RANGE_PATTERNS = new Pattern[] {
	    // sample: Late Jurassic, 155-150 Ma...
	    // range values separator is not dash, is a UTF-8 character - not very clear
	    // what code
	    // identify it as one or many non numeric characters
	    // for white space before units see below pattern
	    Pattern.compile(
		    "[^\\(](\\d+(?:,\\d+)*(?:\\.\\d+)?)[^\\d,\\.]+(\\d+(?:,\\d+)*(?:\\.\\d+)?)[^\\d](years ago|ka|ma|ga)[^\\)]",
		    Pattern.CASE_INSENSITIVE),

	    // sample: At least 14,200 years ago – present[2]
	    // space before units is a single white space character with code 160
	    // uses dot (.) for any
	    Pattern.compile("(\\d+(?:,\\d+)*(?:\\.\\d+)?).(years ago|ka|ma|ga)", Pattern.CASE_INSENSITIVE)

    };

    /**
     * XPath variants of descendant of the biota table row containing conservation
     * status.
     */
    private static final String[] CONSERVATION_XPATHS = new String[] { //
	    "TD/DIV/A", //
	    "TD/DIV" //
    };

    /**
     * Possible XPaths of descendant of the biota table row that contains taxon
     * value. Text value is extracted exactly, that is, uses
     * {@link Element#getTextContent()}. Anyway, if XPath starts with '~' uses more
     * generic {@link Element#getText()} text getter.
     * <p>
     * This values are depends on article source page structure and should be
     * maintained.
     * <p>
     * Also, this list cannot be exhaustive. Is quite possible to upgrade it when
     * new variants will be discovered.
     */
    private static final String[] TAXON_XPATHS = new String[] { //
	    "TD[2]/A", //
	    "TD[2]/A/I", //
	    "TD[2]/DIV/I/A", //
	    "TD[2]/DIV/I/B", //
	    "~TD[2]/DIV/B" //
    };
}
