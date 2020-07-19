package com.kidscademy.apiservice.unit;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kidscademy.apiservice.model.LifeForm;
import com.kidscademy.apiservice.parser.Parser;

import js.dom.Document;
import js.dom.DocumentBuilder;
import js.util.Classes;

public class WikipediaLifeFormParserTest {
    private static DocumentBuilder builder;

    @BeforeClass
    public static void beforeClass() {
	builder = Classes.loadService(DocumentBuilder.class);
    }

    @Test
    public void parse_dog() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-dog.htm"));
	Parser<LifeForm> parser = getParser();
	LifeForm lifeForm = parser.parse(document);

	assertThat(lifeForm, notNullValue());
	assertThat(lifeForm.getCommonName(), equalTo("Dog"));
	assertThat(lifeForm.getScientificName(), equalTo("Canis lupus familiaris"));
	assertThat(lifeForm.getDefinition(), startsWith(
		"The domestic dog is a member of the genus Canis, which forms part of the wolf-like canids"));
	assertThat(lifeForm.getDescription().get(0), startsWith(
		"The domestic dog (Canis lupus familiaris when considered a subspecies of the wolf or Canis familiaris when considered a distinct species) is a member of"));
	assertThat(lifeForm.getStartDate(), equalTo(14200.0));
	assertThat(lifeForm.getEndDate(), nullValue());
	assertThat(lifeForm.getConservationStatus(), equalTo("Domesticated"));

	Map<String, String> taxonomy = lifeForm.getTaxonomy();
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
	Parser<LifeForm> parser = getParser();
	LifeForm lifeForm = parser.parse(document);

	assertThat(lifeForm, notNullValue());
	assertThat(lifeForm.getCommonName(), equalTo("Greater rhea"));
	assertThat(lifeForm.getScientificName(), equalTo("Rhea americana"));
	assertThat(lifeForm.getDefinition(),
		startsWith("The greater rhea is a species of flightless bird native to eastern South America."));
	assertThat(lifeForm.getDescription().get(0), startsWith(
		"The greater rhea (Rhea americana) is a species of flightless bird native to eastern South America."));
	assertThat(lifeForm.getStartDate(), nullValue());
	assertThat(lifeForm.getEndDate(), nullValue());
	assertThat(lifeForm.getConservationStatus(), equalTo("Near Threatened"));

	Map<String, String> taxonomy = lifeForm.getTaxonomy();
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
	Parser<LifeForm> parser = getParser();
	LifeForm lifeForm = parser.parse(document);

	assertThat(lifeForm, notNullValue());
	assertThat(lifeForm.getCommonName(), equalTo("Stegosaurus"));
	assertThat(lifeForm.getScientificName(), equalTo("Stegosaurus stenops"));
	assertThat(lifeForm.getDefinition(),
		startsWith("Stegosaurus, from Greek stegos which means roof and sauros which means lizard"));
	assertThat(lifeForm.getDescription().get(0), startsWith(
		"Stegosaurus (/ˌstɛɡəˈsɔːrəs/), from Greek stegos (στέγος) which means roof and sauros (σαῦρος) which means lizard"));
	assertThat(lifeForm.getStartDate(), equalTo(155000000.0));
	assertThat(lifeForm.getEndDate(), equalTo(150000000.0));
	assertThat(lifeForm.getConservationStatus(), nullValue());

	Map<String, String> taxonomy = lifeForm.getTaxonomy();
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

    @Test
    public void parse_artichoke() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-artichoke.htm"));
	Parser<LifeForm> parser = getParser();
	LifeForm lifeForm = parser.parse(document);

	assertThat(lifeForm, notNullValue());
	assertThat(lifeForm.getCommonName(), equalTo("Artichoke"));
	assertThat(lifeForm.getScientificName(), equalTo("Cynara Cardunculus var. Scolymus"));
	assertThat(lifeForm.getDefinition(),
		startsWith("The globe artichoke, also known by the names French artichoke and green artichoke"));
	assertThat(lifeForm.getDescription().get(0), startsWith(
		"The globe artichoke (Cynara cardunculus var. scolymus), also known by the names French artichoke and green artichoke"));
	assertThat(lifeForm.getStartDate(), nullValue());
	assertThat(lifeForm.getEndDate(), nullValue());
	assertThat(lifeForm.getConservationStatus(), nullValue());

	Map<String, String> taxonomy = lifeForm.getTaxonomy();
	assertThat(taxonomy, notNullValue());
	assertThat(taxonomy, aMapWithSize(6));
	assertThat(taxonomy.get("kingdom"), equalTo("Plantae"));
	assertThat(taxonomy.get("order"), equalTo("Asterales"));
	assertThat(taxonomy.get("family"), equalTo("Asteraceae"));
	assertThat(taxonomy.get("genus"), equalTo("Cynara"));
	assertThat(taxonomy.get("species"), equalTo("Cardunculus"));
	assertThat(taxonomy.get("variety"), equalTo("Scolymus"));

	Iterator<String> keys = taxonomy.keySet().iterator();
	assertThat(keys.next(), equalTo("kingdom"));
	assertThat(keys.next(), equalTo("order"));
	assertThat(keys.next(), equalTo("family"));
	assertThat(keys.next(), equalTo("genus"));
	assertThat(keys.next(), equalTo("species"));
	assertThat(keys.next(), equalTo("variety"));
    }

    private static Parser<LifeForm> getParser() {
	return Classes.newInstance("com.kidscademy.apiservice.parser.WikipediaLifeFormParser");
    }
}
