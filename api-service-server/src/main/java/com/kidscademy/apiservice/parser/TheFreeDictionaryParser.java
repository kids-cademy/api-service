package com.kidscademy.apiservice.parser;

import java.util.Arrays;
import java.util.List;

import com.kidscademy.apiservice.model.WordDefinition;

import js.dom.Document;

public class TheFreeDictionaryParser implements Parser<List<WordDefinition>> {
    @Override
    public List<WordDefinition> parse(Document document) {
	String definition = document.getByXPath("//*[@id='Definition']/SECTION[1]/DIV[1]/DIV").getText();
	return Arrays.asList(new WordDefinition(definition));
    }
}
