package com.kidscademy.apiservice.parser;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.kidscademy.apiservice.util.Strings;

import js.lang.BugError;

public class ParserFactory {
    private static final Map<Key, Parser<?>> PARSERS = new HashMap<>();

    static {
	PARSERS.put(new Key("wikipedia.org", "definition"), new WikipediaDefinitionParser());
	PARSERS.put(new Key("wikipedia.org", "life-form"), new WikipediaLifeFormParser());
	PARSERS.put(new Key("wikipedia.org", "edible-plant"), new WikipediaEdiblePlantParser());
	PARSERS.put(new Key("wikipedia.org", "taxonomy"), new WikipediaTaxonomyParser());
	PARSERS.put(new Key("wikipedia.org", "nutrients"), new WikipediaNutrientsParser());
	PARSERS.put(new Key("thefreedictionary.com", "definition"), new TheFreeDictionaryParser());
	PARSERS.put(new Key("ahdictionary.com", "definition"), new TheAmericanHeritageDictionaryParser());
    }

    public <T> Parser<T> getParser(URL document, String type) {
	Key key = new Key(document, type);
	@SuppressWarnings("unchecked")
	Parser<T> parser = (Parser<T>) PARSERS.get(key);
	if (parser == null) {
	    throw new BugError("Not parser for |%s|.", key);
	}
	return parser;
    }

    private static final class Key {
	private final String basedomain;
	private final String type;

	public Key(URL document, String type) {
	    this(Strings.basedomain(document), type);
	}

	public Key(String basedomain, String type) {
	    this.basedomain = basedomain;
	    this.type = type;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((basedomain == null) ? 0 : basedomain.hashCode());
	    result = prime * result + ((type == null) ? 0 : type.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Key other = (Key) obj;
	    if (basedomain == null) {
		if (other.basedomain != null)
		    return false;
	    } else if (!basedomain.equals(other.basedomain))
		return false;
	    if (type == null) {
		if (other.type != null)
		    return false;
	    } else if (!type.equals(other.type))
		return false;
	    return true;
	}
    }
}
