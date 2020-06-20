package com.kidscademy.apiservice.parser;

import java.util.LinkedHashMap;

import js.dom.Document;

public class WikipediaTaxonomyParser implements Parser<LinkedHashMap<String, String>> {
    private final WikipediaBiotaParser biotaParser = new WikipediaBiotaParser();

    @Override
    public LinkedHashMap<String, String> parse(Document document) {
	WikipediaBiota biota = biotaParser.parse(document);
	return biota.getTaxonomy();
    }
}
