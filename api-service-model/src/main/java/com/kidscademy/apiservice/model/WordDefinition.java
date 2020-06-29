package com.kidscademy.apiservice.model;

import java.util.Arrays;

public class WordDefinition {
    private String definition;

    /** Optional part of speech, null if unknown. */
    private PartOfSpeech partOfSpeech;

    private String[] domains;
    /**
     * Optional list of attributes, e.g. 'slang,vulgar'. Null if no attribute
     * present.
     */
    private String[] attributes;

    /**
     * Optional usage examples, be it phrases, expressions, complete statements or
     * quotes.
     */
    private String[] examples;

    public WordDefinition() {
    }

    public WordDefinition(String definition) {
	this(definition, PartOfSpeech.NOUN);
    }

    public WordDefinition(String definition, PartOfSpeech partOfSpeech) {
	this.definition = definition;
	this.partOfSpeech = partOfSpeech;
	this.domains = EMPTY_ARRAY;
	this.attributes = EMPTY_ARRAY;
	this.examples = EMPTY_ARRAY;
    }

    public WordDefinition(String definition, PartOfSpeech partOfSpeech, String[] domains, String[] attributes,
	    String[] examples) {
	this.definition = definition;
	this.partOfSpeech = partOfSpeech;
	this.domains = domains;
	this.attributes = attributes;
	this.examples = examples;
    }

    public String getDefinition() {
	return definition;
    }

    public PartOfSpeech getPartOfSpeech() {
	return partOfSpeech;
    }

    public String[] getDomains() {
	return domains;
    }

    public String[] getAttributes() {
	return attributes;
    }

    public String[] getExamples() {
	return examples;
    }

    @Override
    public String toString() {
	return "WordDefinition [definition=" + definition + ", partOfSpeech=" + partOfSpeech + ", domains="
		+ Arrays.toString(domains) + ", attributes=" + Arrays.toString(attributes) + ", examples="
		+ Arrays.toString(examples) + "]";
    }

    private static final String[] EMPTY_ARRAY = new String[0];
}