package com.kidscademy.apiservice.parser;

import static com.kidscademy.apiservice.util.Strings.breakSentences;
import static com.kidscademy.apiservice.util.Strings.replaceLastChar;
import static js.util.Strings.join;
import static js.util.Strings.split;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kidscademy.apiservice.model.PartOfSpeech;
import com.kidscademy.apiservice.model.WordDefinition;

import js.dom.ChildNode;
import js.dom.Document;
import js.dom.EList;
import js.dom.Element;
import js.lang.BugError;

public class TheAmericanHeritageDictionaryParser implements Parser<List<WordDefinition>> {
    @Override
    public List<WordDefinition> parse(Document document) {
	List<WordDefinition> wordDefinitions = new ArrayList<>();

	EList sections = document.findByXPath("//div[@id='results']/table");
	for (Element section : sections) {
	    SectionHeader sectionHeader = new SectionHeader(section.getByCssClass("rtseg"));
	    EList segments = section.findByCssClass("pseg");
	    for (Element segment : segments) {
		SegmentHeader segmentHeader = new SegmentHeader(segment, sectionHeader.getModifiers());
		if (!segmentHeader.hasPartOfSpeech()) {
		    System.out.println("---" + segment.getText());
		    continue;
		}

		Element singleDefinition = segment.getByCssClass("ds-single");
		if (singleDefinition != null) {
		    wordDefinitions.add(wordDefinition(singleDefinition, segmentHeader));
		    continue;
		}

		EList definitions = segment.findByCssClass("ds-list");
		for (Element definition : definitions) {
		    EList subdefinitions = definition.findByCssClass("sds-list");
		    if (subdefinitions.size() == 0) {
			wordDefinitions.add(wordDefinition(definition, segmentHeader));
			continue;
		    }
		    for (Element subdefinition : subdefinitions) {
			wordDefinitions.add(wordDefinition(subdefinition, segmentHeader));
		    }
		}
	    }
	}

	return wordDefinitions;
    }

    private static WordDefinition wordDefinition(Element definitionElement, SegmentHeader segmentHeader) {
	Modifiers modifiers = segmentHeader.getModifiers().clone();
	List<String> examples = new ArrayList<>();

	String definition = normalizeDefinition(definition(definitionElement, modifiers, examples));
	PartOfSpeech partOfSpeech = segmentHeader.getPartOfSpeech();
	String[] domains = modifiers.getDomainsArray();
	String[] attributes = modifiers.getAttributesArray();

	return new WordDefinition(definition, partOfSpeech, domains, attributes, examples.toArray(new String[0]));
    }

    private enum DefinitionState {
	PRE_TEXT, TEXT, POST_TEXT
    }

    private static String definition(Element definitionElement, final Modifiers modifiers, List<String> examples) {
	List<String> textNodes = new ArrayList<>();
	DefinitionState state = DefinitionState.PRE_TEXT;

	for (ChildNode childNode : definitionElement.getChildNodes()) {
	    switch (state) {
	    case PRE_TEXT:
		if (childNode.isElement()) {
		    switch (childNode.asElement().getTag()) {
		    case "b":
			// list numbering is ignored, for example, 1. or b.
			// speculate that all list numbering are bold
			continue;

		    case "i":
			modifiers.add(childNode.asElement());
			continue;
		    }
		    break;
		}
		// here child node is text and we switch to text processing
		// fall trough next case
		state = DefinitionState.TEXT;

	    case TEXT:
		if (childNode.isText() || !childNode.asElement().getTag().equals("font")) {
		    String text = (childNode.isElement() ? childNode.asElement().getText() : childNode.asText()).trim();
		    if (!text.isEmpty()) {
			textNodes.add(text);
		    }
		    break;
		}

		int lastTextIndex = textNodes.size() - 1;
		String text = textNodes.get(lastTextIndex);
		if (text.endsWith(":")) {
		    textNodes.set(lastTextIndex, replaceLastChar(text, '.'));
		}
		// child node is font element and we switch to post text processing
		// fall through next case
		state = DefinitionState.POST_TEXT;

	    case POST_TEXT:
		if (childNode.isElement() && childNode.asElement().getTag().equals("font")) {
		    String exampleText = childNode.asElement().getText().trim();
		    if (exampleText.startsWith("(") && (exampleText.endsWith(").") || exampleText.endsWith(")"))) {
			// for now ignore quote author
			continue;
		    }
		    for (String example : splitExamples(exampleText)) {
			examples.add(normalizeExample(example));
		    }
		}
		break;
	    }
	}

	return join(textNodes.toArray());
    }

    private static List<String> splitExamples(String text) {
	List<String> examples = split(text, ';');
	if (examples.size() == 1) {
	    examples = breakSentences(examples.get(0));
	}
	return examples;
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
	// if example starts with lower case it should not have full stop
	if (Character.isLowerCase(example.charAt(0)) && example.endsWith(".")) {
	    return example.substring(0, example.length() - 1);
	}
	// ensure there is full stop inside quotation
	if (example.endsWith("\"") && !example.endsWith(".\"")) {
	    return example.substring(0, example.length() - 1) + ".\"";
	}
	return example;
    }

    private static class SectionHeader {
	private String word;
	private String[] syllables;
	private Modifiers modifiers;

	public SectionHeader(Element headerElement) {
	    Element titleElement = headerElement.getByXPath("child::b");
	    String title = titleElement.getText();
	    Element titleIndexElement = titleElement.getByXPath("descendant::sup");
	    if (titleIndexElement != null) {
		title = title.substring(0, title.length() - titleIndexElement.getText().length());
	    }

	    this.word = title.replaceAll("\\W", "");
	    this.syllables = title.split("\\W");

	    this.modifiers = new Modifiers(headerElement.getByXPath("child::i"));
	}

	public Modifiers getModifiers() {
	    return modifiers;
	}

	@Override
	public String toString() {
	    return "SectionHeader [word=" + word + ", syllables=" + Arrays.toString(syllables) + ", modifiers="
		    + modifiers + "]";
	}
    }

    private static class SegmentHeader {
	private PartOfSpeech partOfSpeech;
	private Modifiers modifiers;

	public SegmentHeader(Element headerElement, Modifiers parentModifiers) {
	    int i = 1;
	    for (;; ++i) {
		Element partOfSpeechElement = headerElement.getByXPath("child::i[%d]", i);
		if (partOfSpeechElement == null) {
		    this.modifiers = new Modifiers(parentModifiers);
		    return;
		}
		PartOfSpeech partOfSpeech = PART_OF_SPEECH.get(partOfSpeechElement.getText());
		if (partOfSpeech == null) {
		    break;
		}
		this.partOfSpeech = partOfSpeech;
	    }

	    this.modifiers = new Modifiers(headerElement.getByXPath("child::i[%d]", i));
	    this.modifiers.getDomains().addAll(parentModifiers.getDomains());
	    this.modifiers.getAttributes().addAll(parentModifiers.getAttributes());
	}

	public boolean hasPartOfSpeech() {
	    return partOfSpeech != null;
	}

	public PartOfSpeech getPartOfSpeech() {
	    return partOfSpeech;
	}

	public Modifiers getModifiers() {
	    return modifiers;
	}

	@Override
	public String toString() {
	    return "SegmentHeader [partOfSpeech=" + partOfSpeech + ", modifiers=" + modifiers + "]";
	}

	private static Map<String, PartOfSpeech> PART_OF_SPEECH = new HashMap<>();
	static {
	    PART_OF_SPEECH.put("n.", PartOfSpeech.NOUN);
	    PART_OF_SPEECH.put("pl.", PartOfSpeech.NOUN);
	    PART_OF_SPEECH.put("v.", PartOfSpeech.VERB);
	    PART_OF_SPEECH.put("tr.v.", PartOfSpeech.VERB);
	    PART_OF_SPEECH.put("tr. & intr.v.", PartOfSpeech.VERB);
	    PART_OF_SPEECH.put("tr.", PartOfSpeech.VERB);
	    PART_OF_SPEECH.put("intr.", PartOfSpeech.VERB);
	    PART_OF_SPEECH.put("v. tr.", PartOfSpeech.VERB);
	    PART_OF_SPEECH.put("v. intr.", PartOfSpeech.VERB);
	    PART_OF_SPEECH.put("adj.", PartOfSpeech.ADJECTIVE);
	    PART_OF_SPEECH.put("adv.", PartOfSpeech.ADVERB);
	    PART_OF_SPEECH.put("interj.", PartOfSpeech.INTERJECTION);
	}
    }

    private static class Modifiers {
	private Set<String> domains = new HashSet<>();
	private Set<String> attributes = new HashSet<>();

	public Modifiers() {
	}

	public Modifiers(Element modifiersElement) {
	    add(modifiersElement);
	}

	public Modifiers(Modifiers parentModifiers) {
	    this.domains.addAll(parentModifiers.getDomains());
	    this.attributes.addAll(parentModifiers.getAttributes());
	}

	public void add(Element modifiersElement) {
	    if (modifiersElement == null) {
		// check for null element here to simplify creator logic
		return;
	    }
	    StringBuilder modifiers = new StringBuilder(modifiersElement.getText());
	    scan(modifiers, EXCLUDES, null);
	    scan(modifiers, DOMAINS, domains);
	    scan(modifiers, ATTRIBUTES, attributes);

	    String unusedModifiers = modifiers.toString().trim();
	    if (!unusedModifiers.isEmpty()) {
		throw new BugError("Unused modifiers |%s|.", unusedModifiers);
	    }
	}

	public Modifiers clone() {
	    Modifiers clone = new Modifiers();
	    clone.domains = new HashSet<>();
	    clone.domains.addAll(this.domains);
	    clone.attributes = new HashSet<>();
	    clone.attributes.addAll(this.attributes);
	    return clone;
	}

	public Set<String> getDomains() {
	    return domains;
	}

	public String[] getDomainsArray() {
	    return domains.toArray(new String[0]);
	}

	public Set<String> getAttributes() {
	    return attributes;
	}

	public String[] getAttributesArray() {
	    return attributes.toArray(new String[0]);
	}

	@Override
	public String toString() {
	    return "Modifiers [domains=" + domains + ", attributes=" + attributes + "]";
	}

	/**
	 * Scan <code>source</code> strings and copy to <code>target</code>, if found on
	 * given <code>modifiers</code> string, then remove source from modifiers. If
	 * target is not provided copy is of course not performed.
	 * <p>
	 * This method has side effect: modifiers string is updated for every found
	 * source string.
	 * 
	 * @param modifiers
	 *            modifiers string from parent element text,
	 * @param source
	 *            source strings,
	 * @param target
	 *            optional target strings, null if not provided.
	 */
	private static void scan(StringBuilder modifiers, List<String> source, Set<String> target) {
	    for (String domain : source) {
		int startIndex = modifiers.indexOf(domain);
		if (startIndex == -1) {
		    continue;
		}
		int endIndex = startIndex + domain.length();
		modifiers.delete(startIndex, endIndex);
		if (target != null) {
		    target.add(domain);
		}
	    }
	}

	private static List<String> EXCLUDES = new ArrayList<>();
	static {
	    EXCLUDES.add("abbr.");
	}

	private static List<String> DOMAINS = new ArrayList<>();
	static {
	    DOMAINS.add("Baseball");
	    DOMAINS.add("Biology");
	    DOMAINS.add("Computers");
	    DOMAINS.add("Nautical");
	    DOMAINS.add("Sports");
	    DOMAINS.add("Sports & Games");
	}

	private static List<String> ATTRIBUTES = new ArrayList<>();
	static {
	    ATTRIBUTES.add("Archaic");
	    ATTRIBUTES.add("Chiefly British");
	    ATTRIBUTES.add("Informal");
	    ATTRIBUTES.add("Obsolete");
	    ATTRIBUTES.add("Scots");
	    ATTRIBUTES.add("Slang");
	    ATTRIBUTES.add("Vulgar");
	}
    }
}
