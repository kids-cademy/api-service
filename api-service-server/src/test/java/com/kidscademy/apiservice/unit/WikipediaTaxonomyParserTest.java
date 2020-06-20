package com.kidscademy.apiservice.unit;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kidscademy.apiservice.parser.Parser;

import js.dom.Document;
import js.dom.DocumentBuilder;
import js.util.Classes;

public class WikipediaTaxonomyParserTest {
    private static DocumentBuilder builder;

    @BeforeClass
    public static void beforeClass() {
	builder = Classes.loadService(DocumentBuilder.class);
    }

    @Test
    public void parse_dog() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-dog.htm"));
	Parser<LinkedHashMap<String, String>> parser = getParser();
	LinkedHashMap<String, String> taxonomy = parser.parse(document);

	assertThat(taxonomy, notNullValue());
	assertThat(taxonomy, aMapWithSize(8));
	assertThat(taxonomy.get("kingdom"), equalTo("Animalia"));
	assertThat(taxonomy.get("phylum"), equalTo("Chordata"));
	assertThat(taxonomy.get("class"), equalTo("Mammalia"));
	assertThat(taxonomy.get("order"), equalTo("Carnivora"));
	assertThat(taxonomy.get("family"), equalTo("Canidae"));
	assertThat(taxonomy.get("genus"), equalTo("Canis"));
	assertThat(taxonomy.get("species"), equalTo("Lupus"));
	assertThat(taxonomy.get("subspecies"), equalTo("Familiaris"));

	Iterator<String> keys = taxonomy.keySet().iterator();
	assertThat(keys.next(), equalTo("kingdom"));
	assertThat(keys.next(), equalTo("phylum"));
	assertThat(keys.next(), equalTo("class"));
	assertThat(keys.next(), equalTo("order"));
	assertThat(keys.next(), equalTo("family"));
	assertThat(keys.next(), equalTo("genus"));
	assertThat(keys.next(), equalTo("species"));
	assertThat(keys.next(), equalTo("subspecies"));
    }

    @Test
    public void parse_greater_rhea() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-greater-rhea.htm"));
	Parser<LinkedHashMap<String, String>> parser = getParser();
	LinkedHashMap<String, String> taxonomy = parser.parse(document);

	assertThat(taxonomy, notNullValue());
	assertThat(taxonomy, aMapWithSize(7));
	assertThat(taxonomy.get("kingdom"), equalTo("Animalia"));
	assertThat(taxonomy.get("phylum"), equalTo("Chordata"));
	assertThat(taxonomy.get("class"), equalTo("Aves"));
	assertThat(taxonomy.get("order"), equalTo("Rheiformes"));
	assertThat(taxonomy.get("family"), equalTo("Rheidae"));
	assertThat(taxonomy.get("genus"), equalTo("Rhea"));
	assertThat(taxonomy.get("species"), equalTo("Americana"));

	Iterator<String> keys = taxonomy.keySet().iterator();
	assertThat(keys.next(), equalTo("kingdom"));
	assertThat(keys.next(), equalTo("phylum"));
	assertThat(keys.next(), equalTo("class"));
	assertThat(keys.next(), equalTo("order"));
	assertThat(keys.next(), equalTo("family"));
	assertThat(keys.next(), equalTo("genus"));
	assertThat(keys.next(), equalTo("species"));
    }

    @Test
    public void parse_stegosaurus() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-stegosaurus.htm"));
	Parser<LinkedHashMap<String, String>> parser = getParser();
	LinkedHashMap<String, String> taxonomy = parser.parse(document);

	assertThat(taxonomy, notNullValue());
	assertThat(taxonomy, aMapWithSize(8));
	assertThat(taxonomy.get("kingdom"), equalTo("Animalia"));
	assertThat(taxonomy.get("phylum"), equalTo("Chordata"));
	assertThat(taxonomy.get("order"), equalTo("Ornithischia"));
	assertThat(taxonomy.get("suborder"), equalTo("Stegosauria"));
	assertThat(taxonomy.get("family"), equalTo("Stegosauridae"));
	assertThat(taxonomy.get("subfamily"), equalTo("Stegosaurinae"));
	assertThat(taxonomy.get("genus"), equalTo("Stegosaurus"));
	assertThat(taxonomy.get("species"), equalTo("Stenops"));

	Iterator<String> keys = taxonomy.keySet().iterator();
	assertThat(keys.next(), equalTo("kingdom"));
	assertThat(keys.next(), equalTo("phylum"));
	assertThat(keys.next(), equalTo("order"));
	assertThat(keys.next(), equalTo("suborder"));
	assertThat(keys.next(), equalTo("family"));
	assertThat(keys.next(), equalTo("subfamily"));
	assertThat(keys.next(), equalTo("genus"));
	assertThat(keys.next(), equalTo("species"));
    }

    private static Parser<LinkedHashMap<String, String>> getParser() {
	return Classes.newInstance("com.kidscademy.apiservice.parser.WikipediaTaxonomyParser");
    }
}
