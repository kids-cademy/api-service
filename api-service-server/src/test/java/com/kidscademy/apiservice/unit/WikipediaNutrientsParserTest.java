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
	Map<String, Double> nutritionalValue = parser.parse(document);

	assertThat(nutritionalValue, notNullValue());
	assertThat(nutritionalValue.get("Energy"), equalTo(288.0));
	assertThat(nutritionalValue.get("Calories"), equalTo(68.832));
	assertThat(nutritionalValue.get("Carbohydrates"), equalTo(0.0181));
	assertThat(nutritionalValue.get("Sugars"), equalTo(0.01548));
	assertThat(nutritionalValue.get("Dietary Fiber"), equalTo(0.0009));
	assertThat(nutritionalValue.get("Fat"), equalTo(0.00016));
	assertThat(nutritionalValue.get("Protein"), equalTo(0.00072));
	assertThat(nutritionalValue.get("Thiamine"), equalTo(0.000000069));
	assertThat(nutritionalValue.get("Riboflavin"), equalTo(0.00000007));
	assertThat(nutritionalValue.get("Niacin"), equalTo(0.000000188));
	assertThat(nutritionalValue.get("Pantothenic Acid"), equalTo(0.00000005));
	assertThat(nutritionalValue.get("Vitamin B6"), equalTo(0.000000086));
	assertThat(nutritionalValue.get("Folate"), equalTo(0.000000002));
	assertThat(nutritionalValue.get("Choline"), equalTo(0.0000056));
	assertThat(nutritionalValue.get("Vitamin C"), equalTo(0.0000032));
	assertThat(nutritionalValue.get("Vitamin E"), equalTo(0.00000019));
	assertThat(nutritionalValue.get("Vitamin K"), equalTo(0.0000000146));
	assertThat(nutritionalValue.get("Calcium"), equalTo(0.00001));
	assertThat(nutritionalValue.get("Iron"), equalTo(0.00000036));
	assertThat(nutritionalValue.get("Magnesium"), equalTo(0.000007));
	assertThat(nutritionalValue.get("Manganese"), equalTo(0.000000071));
	assertThat(nutritionalValue.get("Phosphorus"), equalTo(0.00002));
	assertThat(nutritionalValue.get("Potassium"), equalTo(0.000191));
	assertThat(nutritionalValue.get("Sodium"), equalTo(0.000002));
	assertThat(nutritionalValue.get("Zinc"), equalTo(0.00000007));
	assertThat(nutritionalValue.get("Water"), equalTo(0.081));
    }

    @Test
    public void parse_tomato() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-tomato.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutritionalValue = parser.parse(document);

	assertThat(nutritionalValue, notNullValue());
	assertThat(nutritionalValue.get("Energy"), equalTo(74.0));
	assertThat(nutritionalValue.get("Calories"), equalTo(17.686));
	assertThat(nutritionalValue.get("Carbohydrates"), equalTo(0.0039));
	assertThat(nutritionalValue.get("Sugars"), equalTo(0.0026));
	assertThat(nutritionalValue.get("Dietary Fiber"), equalTo(0.0012));
	assertThat(nutritionalValue.get("Fat"), equalTo(0.0002));
	assertThat(nutritionalValue.get("Protein"), equalTo(0.0009));
	assertThat(nutritionalValue.get("Vitamin A"), equalTo(0.000000042));
	assertThat(nutritionalValue.get("Thiamine"), equalTo(0.000000037));
	assertThat(nutritionalValue.get("Niacin"), equalTo(0.000000594));
	assertThat(nutritionalValue.get("Vitamin B6"), equalTo(0.00000008));
	assertThat(nutritionalValue.get("Vitamin C"), equalTo(0.000014));
	assertThat(nutritionalValue.get("Vitamin E"), equalTo(0.00000054));
	assertThat(nutritionalValue.get("Vitamin K"), equalTo(0.0000000079));
	assertThat(nutritionalValue.get("Magnesium"), equalTo(0.000011));
	assertThat(nutritionalValue.get("Manganese"), equalTo(0.000000114));
	assertThat(nutritionalValue.get("Phosphorus"), equalTo(0.000024));
	assertThat(nutritionalValue.get("Potassium"), equalTo(0.000237));
	assertThat(nutritionalValue.get("Water"), equalTo(0.0945));
	assertThat(nutritionalValue.get("Lycopene"), equalTo(0.000002573));
    }

    @Test
    public void parse_kiwifruit() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-kiwifruit.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutritionalValue = parser.parse(document);

	assertThat(nutritionalValue, notNullValue());
	assertThat(nutritionalValue.get("Energy"), equalTo(262.0));
	assertThat(nutritionalValue.get("Calories"), equalTo(62.618));
	assertThat(nutritionalValue.get("Carbohydrates"), equalTo(0.0158));
	assertThat(nutritionalValue.get("Sugars"), equalTo(0.0123));
	assertThat(nutritionalValue.get("Dietary Fiber"), equalTo(0.0014));
	assertThat(nutritionalValue.get("Fat"), equalTo(0.00028));
	assertThat(nutritionalValue.get("Protein"), equalTo(0.00102));
	assertThat(nutritionalValue.get("Vitamin A"), equalTo(0.000000024));
	assertThat(nutritionalValue.get("Thiamine"), equalTo(0.0000));
	assertThat(nutritionalValue.get("Riboflavin"), equalTo(0.000000074));
	assertThat(nutritionalValue.get("Niacin"), equalTo(0.000000231));
	assertThat(nutritionalValue.get("Pantothenic Acid"), equalTo(0.00000012));
	assertThat(nutritionalValue.get("Vitamin B6"), equalTo(0.000000079));
	assertThat(nutritionalValue.get("Folate"), equalTo(0.000000031));
	assertThat(nutritionalValue.get("Vitamin B12"), equalTo(0.00000000008));
	assertThat(nutritionalValue.get("Choline"), equalTo(0.0000019));
	assertThat(nutritionalValue.get("Vitamin C"), equalTo(0.0001613));
	assertThat(nutritionalValue.get("Vitamin E"), equalTo(0.0000014));
	assertThat(nutritionalValue.get("Vitamin K"), equalTo(0.0000000061));
	assertThat(nutritionalValue.get("Calcium"), equalTo(0.000017));
	assertThat(nutritionalValue.get("Copper"), equalTo(0.000000151));
	assertThat(nutritionalValue.get("Iron"), equalTo(0.00000021));
	assertThat(nutritionalValue.get("Magnesium"), equalTo(0.000012));
	assertThat(nutritionalValue.get("Manganese"), equalTo(0.000000048));
	assertThat(nutritionalValue.get("Phosphorus"), equalTo(0.000025));
	assertThat(nutritionalValue.get("Potassium"), equalTo(0.000315));
	assertThat(nutritionalValue.get("Selenium"), equalTo(0.0000000004));
	assertThat(nutritionalValue.get("Sodium"), equalTo(0.000003));
	assertThat(nutritionalValue.get("Water"), equalTo(0.082));
    }

    @Test
    public void parse_asparagus() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-asparagus.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutritionalValue = parser.parse(document);

	assertThat(nutritionalValue, notNullValue());
	assertThat(nutritionalValue.get("Energy"), equalTo(85.0));
	assertThat(nutritionalValue.get("Calories"), equalTo(20.315));
	assertThat(nutritionalValue.get("Carbohydrates"), equalTo(0.004));
	assertThat(nutritionalValue.get("Sugars"), equalTo(0.00188));
	assertThat(nutritionalValue.get("Dietary Fiber"), equalTo(0.0021));
	assertThat(nutritionalValue.get("Fat"), equalTo(0.00012));
	assertThat(nutritionalValue.get("Protein"), equalTo(0.0022));
	assertThat(nutritionalValue.get("Vitamin A"), equalTo(0.000000038));
	assertThat(nutritionalValue.get("Thiamine"), equalTo(0.000000143));
	assertThat(nutritionalValue.get("Riboflavin"), equalTo(0.000000141));
	assertThat(nutritionalValue.get("Niacin"), equalTo(0.000000978));
	assertThat(nutritionalValue.get("Pantothenic Acid"), equalTo(0.000000274));
	assertThat(nutritionalValue.get("Vitamin B6"), equalTo(0.000000091));
	assertThat(nutritionalValue.get("Folate"), equalTo(0.000000052));
	assertThat(nutritionalValue.get("Choline"), equalTo(0.000016));
	assertThat(nutritionalValue.get("Vitamin C"), equalTo(0.0000056));
	assertThat(nutritionalValue.get("Vitamin E"), equalTo(0.0000011));
	assertThat(nutritionalValue.get("Vitamin K"), equalTo(0.0000000416));
	assertThat(nutritionalValue.get("Calcium"), equalTo(0.000024));
	assertThat(nutritionalValue.get("Iron"), equalTo(0.00000214));
	assertThat(nutritionalValue.get("Magnesium"), equalTo(0.000014));
	assertThat(nutritionalValue.get("Manganese"), equalTo(0.000000158));
	assertThat(nutritionalValue.get("Phosphorus"), equalTo(0.000052));
	assertThat(nutritionalValue.get("Potassium"), equalTo(0.000202));
	assertThat(nutritionalValue.get("Sodium"), equalTo(0.000002));
	assertThat(nutritionalValue.get("Zinc"), equalTo(0.00000054));
    }
    
    private static Parser<Map<String, Double>> getParser() {
	return Classes.newInstance("com.kidscademy.apiservice.parser.WikipediaNutrientsParser");
    }
}
