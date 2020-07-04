package com.kidscademy.apiservice.unit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kidscademy.apiservice.parser.Parser;

import js.dom.Document;
import js.dom.DocumentBuilder;
import js.util.Classes;

public class WikipediaNutrientsParserTest {
    private static DocumentBuilder builder;

    @BeforeClass
    public static void beforeClass() {
	builder = Classes.loadService(DocumentBuilder.class);
    }

    @Test
    public void parse_grape() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-grape.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutrients = parser.parse(document);

	assertThat(nutrients, notNullValue());
	assertThat(nutrients.get("Energy"), equalTo(288.0));
	assertThat(nutrients.get("Calories"), equalTo(68.832));
	
	assertThat(nutrients.get("Carbohydrates"), equalTo(0.0181));
	assertThat(nutrients.get("Sugars"), equalTo(0.01548));
	assertThat(nutrients.get("Dietary Fiber"), equalTo(0.0009));
	assertThat(nutrients.get("Fat"), equalTo(0.00016));
	assertThat(nutrients.get("Protein"), equalTo(0.00072));
	
	assertThat(nutrients.get("Thiamine"), equalTo(0.000000069));
	assertThat(nutrients.get("Riboflavin"), equalTo(0.00000007));
	assertThat(nutrients.get("Niacin"), equalTo(0.000000188));
	assertThat(nutrients.get("Pantothenic Acid"), equalTo(0.00000005));
	assertThat(nutrients.get("Vitamin B6"), equalTo(0.000000086));
	assertThat(nutrients.get("Folate"), equalTo(0.000000002));
	assertThat(nutrients.get("Choline"), equalTo(0.0000056));
	assertThat(nutrients.get("Vitamin C"), equalTo(0.0000032));
	assertThat(nutrients.get("Vitamin E"), equalTo(0.00000019));
	assertThat(nutrients.get("Vitamin K"), equalTo(0.0000000146));
	
	assertThat(nutrients.get("Calcium"), equalTo(0.00001));
	assertThat(nutrients.get("Iron"), equalTo(0.00000036));
	assertThat(nutrients.get("Magnesium"), equalTo(0.000007));
	assertThat(nutrients.get("Manganese"), equalTo(0.000000071));
	assertThat(nutrients.get("Phosphorus"), equalTo(0.00002));
	assertThat(nutrients.get("Potassium"), equalTo(0.000191));
	assertThat(nutrients.get("Sodium"), equalTo(0.000002));
	assertThat(nutrients.get("Zinc"), equalTo(0.00000007));
	assertThat(nutrients.get("Water"), equalTo(0.081));
    }

    @Test
    public void parse_tomato() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-tomato.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutrients = parser.parse(document);

	assertThat(nutrients, notNullValue());
	assertThat(nutrients.get("Energy"), equalTo(74.0));
	assertThat(nutrients.get("Calories"), equalTo(17.686));
	
	assertThat(nutrients.get("Carbohydrates"), equalTo(0.0039));
	assertThat(nutrients.get("Sugars"), equalTo(0.0026));
	assertThat(nutrients.get("Dietary Fiber"), equalTo(0.0012));
	assertThat(nutrients.get("Fat"), equalTo(0.0002));
	assertThat(nutrients.get("Protein"), equalTo(0.0009));
	
	assertThat(nutrients.get("Vitamin A"), equalTo(0.000000042));
	assertThat(nutrients.get("Thiamine"), equalTo(0.000000037));
	assertThat(nutrients.get("Niacin"), equalTo(0.000000594));
	assertThat(nutrients.get("Vitamin B6"), equalTo(0.00000008));
	assertThat(nutrients.get("Vitamin C"), equalTo(0.000014));
	assertThat(nutrients.get("Vitamin E"), equalTo(0.00000054));
	assertThat(nutrients.get("Vitamin K"), equalTo(0.0000000079));
	
	assertThat(nutrients.get("Magnesium"), equalTo(0.000011));
	assertThat(nutrients.get("Manganese"), equalTo(0.000000114));
	assertThat(nutrients.get("Phosphorus"), equalTo(0.000024));
	assertThat(nutrients.get("Potassium"), equalTo(0.000237));
	assertThat(nutrients.get("Water"), equalTo(0.0945));
	assertThat(nutrients.get("Lycopene"), equalTo(0.000002573));
    }

    @Test
    public void parse_kiwifruit() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-kiwifruit.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutrients = parser.parse(document);

	assertThat(nutrients, notNullValue());
	assertThat(nutrients.get("Energy"), equalTo(262.0));
	assertThat(nutrients.get("Calories"), equalTo(62.618));
	
	assertThat(nutrients.get("Carbohydrates"), equalTo(0.0158));
	assertThat(nutrients.get("Sugars"), equalTo(0.0123));
	assertThat(nutrients.get("Dietary Fiber"), equalTo(0.0014));
	assertThat(nutrients.get("Fat"), equalTo(0.00028));
	assertThat(nutrients.get("Protein"), equalTo(0.00102));
	
	assertThat(nutrients.get("Vitamin A"), equalTo(0.000000024));
	assertThat(nutrients.get("Thiamine"), equalTo(0.0000));
	assertThat(nutrients.get("Riboflavin"), equalTo(0.000000074));
	assertThat(nutrients.get("Niacin"), equalTo(0.000000231));
	assertThat(nutrients.get("Pantothenic Acid"), equalTo(0.00000012));
	assertThat(nutrients.get("Vitamin B6"), equalTo(0.000000079));
	assertThat(nutrients.get("Folate"), equalTo(0.000000031));
	assertThat(nutrients.get("Vitamin B12"), equalTo(0.00000000008));
	assertThat(nutrients.get("Choline"), equalTo(0.0000019));
	assertThat(nutrients.get("Vitamin C"), equalTo(0.0001613));
	assertThat(nutrients.get("Vitamin E"), equalTo(0.0000014));
	assertThat(nutrients.get("Vitamin K"), equalTo(0.0000000061));
	
	assertThat(nutrients.get("Calcium"), equalTo(0.000017));
	assertThat(nutrients.get("Copper"), equalTo(0.000000151));
	assertThat(nutrients.get("Iron"), equalTo(0.00000021));
	assertThat(nutrients.get("Magnesium"), equalTo(0.000012));
	assertThat(nutrients.get("Manganese"), equalTo(0.000000048));
	assertThat(nutrients.get("Phosphorus"), equalTo(0.000025));
	assertThat(nutrients.get("Potassium"), equalTo(0.000315));
	assertThat(nutrients.get("Selenium"), equalTo(0.0000000004));
	assertThat(nutrients.get("Sodium"), equalTo(0.000003));
	assertThat(nutrients.get("Water"), equalTo(0.082));
    }

    @Test
    public void parse_asparagus() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-asparagus.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutrients = parser.parse(document);

	assertThat(nutrients, notNullValue());
	assertThat(nutrients.get("Energy"), equalTo(85.0));
	assertThat(nutrients.get("Calories"), equalTo(20.315));
	
	assertThat(nutrients.get("Carbohydrates"), equalTo(0.004));
	assertThat(nutrients.get("Sugars"), equalTo(0.00188));
	assertThat(nutrients.get("Dietary Fiber"), equalTo(0.0021));
	assertThat(nutrients.get("Fat"), equalTo(0.00012));
	assertThat(nutrients.get("Protein"), equalTo(0.0022));
	
	assertThat(nutrients.get("Vitamin A"), equalTo(0.000000038));
	assertThat(nutrients.get("Thiamine"), equalTo(0.000000143));
	assertThat(nutrients.get("Riboflavin"), equalTo(0.000000141));
	assertThat(nutrients.get("Niacin"), equalTo(0.000000978));
	assertThat(nutrients.get("Pantothenic Acid"), equalTo(0.000000274));
	assertThat(nutrients.get("Vitamin B6"), equalTo(0.000000091));
	assertThat(nutrients.get("Folate"), equalTo(0.000000052));
	assertThat(nutrients.get("Choline"), equalTo(0.000016));
	assertThat(nutrients.get("Vitamin C"), equalTo(0.0000056));
	assertThat(nutrients.get("Vitamin E"), equalTo(0.0000011));
	assertThat(nutrients.get("Vitamin K"), equalTo(0.0000000416));
	
	assertThat(nutrients.get("Calcium"), equalTo(0.000024));
	assertThat(nutrients.get("Iron"), equalTo(0.00000214));
	assertThat(nutrients.get("Magnesium"), equalTo(0.000014));
	assertThat(nutrients.get("Manganese"), equalTo(0.000000158));
	assertThat(nutrients.get("Phosphorus"), equalTo(0.000052));
	assertThat(nutrients.get("Potassium"), equalTo(0.000202));
	assertThat(nutrients.get("Sodium"), equalTo(0.000002));
	assertThat(nutrients.get("Zinc"), equalTo(0.00000054));
    }

    @Test
    public void parse_pork() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-pork.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutrients = parser.parse(document);

	assertThat(nutrients, notNullValue());
	assertThat(nutrients.get("Energy"), equalTo(1013.0));
	assertThat(nutrients.get("Calories"), equalTo(242.107));
	
	assertThat(nutrients.get("Carbohydrates"), equalTo(0.0));
	assertThat(nutrients.get("Sugars"), equalTo(0.0));
	assertThat(nutrients.get("Dietary Fiber"), equalTo(0.0));
	assertThat(nutrients.get("Fat"), equalTo(0.02792));
	assertThat(nutrients.get("Saturated"), equalTo(0.00523));
	assertThat(nutrients.get("Monounsaturated"), equalTo(0.00619));
	assertThat(nutrients.get("Polyunsaturated"), equalTo(0.0012));
	assertThat(nutrients.get("Protein"), equalTo(0.01332));

	assertThat(nutrients.get("Tryptophan"), equalTo(0.000338));
	assertThat(nutrients.get("Threonine"), equalTo(0.001234));
	assertThat(nutrients.get("Isoleucine"), equalTo(0.00126));
	assertThat(nutrients.get("Leucine"), equalTo(0.002177));
	assertThat(nutrients.get("Lysine"), equalTo(0.002446));
	assertThat(nutrients.get("Methionine"), equalTo(0.000712));
	assertThat(nutrients.get("Cystine"), equalTo(0.000344));
	assertThat(nutrients.get("Phenylalanine"), equalTo(0.001086));
	assertThat(nutrients.get("Tyrosine"), equalTo(0.000936));
	assertThat(nutrients.get("Valine"), equalTo(0.001473));
	assertThat(nutrients.get("Arginine"), equalTo(0.001723));
	assertThat(nutrients.get("Histidine"), equalTo(0.001067));
	assertThat(nutrients.get("Alanine"), equalTo(0.001603));
	assertThat(nutrients.get("Aspartic Acid"), equalTo(0.002512));
	assertThat(nutrients.get("Glutamic Acid"), equalTo(0.004215));
	assertThat(nutrients.get("Glycine"), equalTo(0.001409));
	assertThat(nutrients.get("Proline"), equalTo(0.001158));
	assertThat(nutrients.get("Serine"), equalTo(0.001128));

	assertThat(nutrients.get("Vitamin B6"), equalTo(0.000000464));
	assertThat(nutrients.get("Vitamin B12"), equalTo(0.0000000007));
	assertThat(nutrients.get("Choline"), equalTo(0.0000939));
	assertThat(nutrients.get("Vitamin C"), equalTo(0.0000006));
	assertThat(nutrients.get("Vitamin D"), equalTo(0.000000001325));

	assertThat(nutrients.get("Calcium"), equalTo(0.000019));
	assertThat(nutrients.get("Copper"), equalTo(0.000000073));
	assertThat(nutrients.get("Iron"), equalTo(0.00000087));
	assertThat(nutrients.get("Magnesium"), equalTo(0.000028));
	assertThat(nutrients.get("Phosphorus"), equalTo(0.000246));
	assertThat(nutrients.get("Potassium"), equalTo(0.000423));
	assertThat(nutrients.get("Sodium"), equalTo(0.000062));
	assertThat(nutrients.get("Zinc"), equalTo(0.00000239));
    }

    private static Parser<Map<String, Double>> getParser() {
	return Classes.newInstance("com.kidscademy.apiservice.parser.WikipediaNutrientsParser");
    }
}
