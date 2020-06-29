package com.kidscademy.apiservice.parser;

import static com.kidscademy.apiservice.util.Strings.removeLastChar;
import static com.kidscademy.apiservice.util.Strings.replaceLastChar;
import static com.kidscademy.apiservice.util.Strings.breakSentences;
import static js.util.Strings.join;
import static js.util.Strings.split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kidscademy.apiservice.model.PartOfSpeech;
import com.kidscademy.apiservice.model.WordDefinition;

import js.dom.ChildNode;
import js.dom.Document;
import js.dom.EList;
import js.dom.Element;

public class TheAmericanHeritageDictionaryParserBKP implements Parser<List<WordDefinition>> {
    @Override
    public List<WordDefinition> parse(Document document) {
	List<WordDefinition> wordDefinitions = new ArrayList<>();

	EList sections = document.findByXPath("//DIV[@id='results']/TABLE");
	for (Element section : sections) {
	    // System.out.println(section.getText().substring(0, 20));

	    EList segments = section.findByCssClass("pseg");
	    for (Element segment : segments) {
		// System.out.println(segment.getText());

		PartOfSpeech partOfSpeech = PART_OF_SPEECH.get(segment.getFirstChild().getText());
		if (partOfSpeech == null) {
		    System.out.println("---" + segment.getText());
		    continue;
		}

		Element singleDefinition = segment.getByCssClass("ds-single");
		if (singleDefinition != null) {
		    wordDefinitions.add(wordDefinition(singleDefinition, partOfSpeech));
		    continue;
		}

		EList definitions = segment.findByCssClass("ds-list");
		for (Element definition : definitions) {
		    EList subdefinitions = definition.findByCssClass("sds-list");
		    if (subdefinitions.size() == 0) {
			wordDefinitions.add(wordDefinition(definition, partOfSpeech));
			continue;
		    }
		    for (Element subdefinition : subdefinitions) {
			wordDefinitions.add(wordDefinition(subdefinition, partOfSpeech));
		    }
		}
	    }
	}

	return wordDefinitions;
    }

    private static WordDefinition wordDefinition(Element definitionElement, PartOfSpeech partOfSpeech) {
	List<String> domains = new ArrayList<>();
	List<String> attributes = new ArrayList<>();
	List<String> examples = new ArrayList<>();
	String sense = sense(definitionElement, attributes, examples);
	return new WordDefinition(normalizeDefinition(sense), partOfSpeech, domains.toArray(new String[0]),
		attributes.toArray(new String[0]), examples.toArray(new String[0]));
    }

    private static String sense(Element definition, List<String> attributes, List<String> examples) {
	List<String> textNodes = new ArrayList<>();
	boolean textProcessing = false;

	for (ChildNode childNode : definition.getChildNodes()) {
	    if (isValidDefinitionStart(childNode)) {
		// text processing starts when found first text node
		textProcessing = true;
	    }

	    String text = (childNode.isElement() ? childNode.asElement().getText() : childNode.asText()).trim();
	    if (textProcessing) {
		if (isSynomymsStart(text)) {
		    break;
		}
		if (isValidDefinitionText(text)) {
		    textNodes.add(text);
		}
		continue;
	    }

	    if (text.endsWith(".")) {
		// list numbering is ignored, for example, 1. or b.
		// speculate that all list numbering ends with dot
		continue;
	    }
	    if (isValidAttribute(text)) {
		// attributes are separated by space
		// exception is 'Chiefly British' that has space in it
		if (text.equals("Chiefly British")) {
		    attributes.add(text);
		} else {
		    attributes.addAll(split(text));
		}
	    }
	}

	if (textNodes.size() > 1 && textNodes.get(textNodes.size() - 2).endsWith(":")) {
	    // take care to remove last full stop
	    String paragraphs = removeLastChar(textNodes.remove(textNodes.size() - 1));
	    for (String example : splitExamples(paragraphs)) {
		examples.add(normalizeExample(example));
	    }
	    String text = textNodes.remove(textNodes.size() - 1);
	    // replace colon with full stop to properly end the sentence
	    textNodes.add(replaceLastChar(text, '.'));
	}

	return join(textNodes.toArray());
    }

    private static boolean isValidDefinitionStart(ChildNode node) {
	if (!node.isText()) {
	    return false;
	}
	String text = node.asText();
	// a valid definition should start with capital letter
	return !text.isEmpty() && Character.isUpperCase(text.charAt(0));
    }

    private static boolean isValidDefinitionText(String text) {
	if (text.isEmpty()) {
	    return false;
	}
	return true;
    }

    private static boolean isSynomymsStart(String text) {
	if (text.startsWith("See Synonyms")) {
	    return true;
	}
	return false;
    }

    private static List<String> splitExamples(String text) {
	List<String> examples = split(text, ';');
	if (examples.size() == 1) {
	    examples = breakSentences(examples.get(0));
	}
	return examples;
    }

    private static boolean isValidAttribute(String text) {
	// a valid attributes expression should start with capital letter
	return !text.isEmpty() && Character.isUpperCase(text.charAt(0));
    }

    private static String normalizeDefinition(String text) {
	// currently the only normalization is to remove space before full stop
	if (text.endsWith(" .")) {
	    return text.substring(0, text.length() - 2) + '.';
	}
	return text;
    }

    private static String normalizeExample(String example) {
	// if example starts with capital letter it should be ended with full stop
	if (Character.isUpperCase(example.charAt(0)) && !example.endsWith(".")) {
	    return example + '.';
	}
	return example;
    }

    private static Map<String, PartOfSpeech> PART_OF_SPEECH = new HashMap<>();
    static {
	PART_OF_SPEECH.put("n.", PartOfSpeech.NOUN);
	PART_OF_SPEECH.put("v.", PartOfSpeech.VERB);
	PART_OF_SPEECH.put("tr.v.", PartOfSpeech.VERB);
	PART_OF_SPEECH.put("tr. & intr.v.", PartOfSpeech.VERB);
	PART_OF_SPEECH.put("v. tr.", PartOfSpeech.VERB);
	PART_OF_SPEECH.put("v. intr.", PartOfSpeech.VERB);
	PART_OF_SPEECH.put("adj.", PartOfSpeech.ADJECTIVE);
    }
}
