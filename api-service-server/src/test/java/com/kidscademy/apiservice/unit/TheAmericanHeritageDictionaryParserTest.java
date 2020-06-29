package com.kidscademy.apiservice.unit;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kidscademy.apiservice.model.WordDefinition;
import com.kidscademy.apiservice.parser.Parser;

import js.dom.Document;
import js.dom.DocumentBuilder;
import js.util.Classes;

public class TheAmericanHeritageDictionaryParserTest {
    private static DocumentBuilder builder;

    @BeforeClass
    public static void beforeClass() {
	builder = Classes.loadService(DocumentBuilder.class);
    }

    @Test
    public void parse_carambola() {
	Document document = builder.loadXML(Classes.getResourceAsStream("ahdictionary-carambola.htm"));
	Parser<List<WordDefinition>> parser = getParser();
	List<WordDefinition> wordDefinitions = parser.parse(document);
	for (WordDefinition definition : wordDefinitions) {
	    System.out.println(definition);
	}
    }

    @Test
    public void parse_port() {
	Document document = builder.loadXML(Classes.getResourceAsStream("ahdictionary-port.htm"));
	Parser<List<WordDefinition>> parser = getParser();
	List<WordDefinition> wordDefinitions = parser.parse(document);
	for (WordDefinition definition : wordDefinitions) {
	    System.out.println(definition);
	}
    }

    @Test
    public void parse_structure() {
	Document document = builder.loadXML(Classes.getResourceAsStream("ahdictionary-structure.htm"));
	Parser<List<WordDefinition>> parser = getParser();
	List<WordDefinition> wordDefinitions = parser.parse(document);
	for (WordDefinition definition : wordDefinitions) {
	    System.out.println(definition);
	}
    }

    @Test
    public void parse_accordion() {
	Document document = builder.loadXML(Classes.getResourceAsStream("ahdictionary-accordion.htm"));
	Parser<List<WordDefinition>> parser = getParser();
	List<WordDefinition> wordDefinitions = parser.parse(document);
	for (WordDefinition definition : wordDefinitions) {
	    System.out.println(definition);
	}
    }

    @Test
    public void parse_duck() {
	Document document = builder.loadXML(Classes.getResourceAsStream("ahdictionary-duck.htm"));
	Parser<List<WordDefinition>> parser = getParser();
	List<WordDefinition> wordDefinitions = parser.parse(document);
	for (WordDefinition definition : wordDefinitions) {
	    System.out.println(definition);
	}
    }

    @Test
    public void parse_want() {
	Document document = builder.loadXML(Classes.getResourceAsStream("ahdictionary-want.htm"));
	Parser<List<WordDefinition>> parser = getParser();
	List<WordDefinition> wordDefinitions = parser.parse(document);
	for (WordDefinition definition : wordDefinitions) {
	    System.out.println(definition);
	}
    }

    @Test
    public void parse_dick() {
	Document document = builder.loadXML(Classes.getResourceAsStream("ahdictionary-dick.htm"));
	Parser<List<WordDefinition>> parser = getParser();
	List<WordDefinition> wordDefinitions = parser.parse(document);
	for (WordDefinition definition : wordDefinitions) {
	    System.out.println(definition);
	}
    }

    private static Parser<List<WordDefinition>> getParser() {
	return Classes.newInstance("com.kidscademy.apiservice.parser.TheAmericanHeritageDictionaryParser");
    }
}
