package com.kidscademy.apiservice.parser;

import java.util.ArrayList;
import java.util.List;

import com.kidscademy.apiservice.model.WordDefinition;
import com.kidscademy.apiservice.util.Strings;

import js.dom.Document;
import js.dom.EList;
import js.dom.Element;

public class WikipediaDefinitionParser implements Parser<List<WordDefinition>> {
    @Override
    public List<WordDefinition> parse(Document document) {
	List<WordDefinition> definitions = new ArrayList<>();

	EList paragraphs = document
		.findByXPath("//DIV[@id='mw-content-text']/DIV[contains(@class,'mw-parser-output')]/P");
	for (Element p : paragraphs) {
	    String text = p.getText().trim();
	    if (!text.isEmpty()) {
		definitions.add(new WordDefinition(
			Strings.firstSentence(Strings.removeParentheses(Strings.removeReferences(text)))));
		break;
	    }
	}

	return definitions;
    }
}
