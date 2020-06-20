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

public class WikipediaNutritionalValueParserTest {
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
	assertThat(nutritionalValue.get("Carbohydrates"), equalTo(18.1));
	assertThat(nutritionalValue.get("Sugars"), equalTo(15.48));
	assertThat(nutritionalValue.get("Dietary Fiber"), equalTo(0.9));
	assertThat(nutritionalValue.get("Fat"), equalTo(0.16));
	assertThat(nutritionalValue.get("Protein"), equalTo(0.72));
	assertThat(nutritionalValue.get("Thiamine"), equalTo(0.000069));
	assertThat(nutritionalValue.get("Riboflavin"), equalTo(0.00007));
	assertThat(nutritionalValue.get("Niacin"), equalTo(0.000188));
	assertThat(nutritionalValue.get("Pantothenic Acid"), equalTo(0.00005));
	assertThat(nutritionalValue.get("Vitamin B6"), equalTo(0.000086));
	assertThat(nutritionalValue.get("Folate"), equalTo(0.000002));
	assertThat(nutritionalValue.get("Choline"), equalTo(0.0056));
	assertThat(nutritionalValue.get("Vitamin C"), equalTo(0.0032));
	assertThat(nutritionalValue.get("Vitamin E"), equalTo(0.00019));
	assertThat(nutritionalValue.get("Vitamin K"), equalTo(0.0000146));
	assertThat(nutritionalValue.get("Calcium"), equalTo(0.01));
	assertThat(nutritionalValue.get("Iron"), equalTo(0.00036));
	assertThat(nutritionalValue.get("Magnesium"), equalTo(0.007));
	assertThat(nutritionalValue.get("Manganese"), equalTo(0.000071));
	assertThat(nutritionalValue.get("Phosphorus"), equalTo(0.02));
	assertThat(nutritionalValue.get("Potassium"), equalTo(0.191));
	assertThat(nutritionalValue.get("Sodium"), equalTo(0.002));
	assertThat(nutritionalValue.get("Zinc"), equalTo(0.00007));
	assertThat(nutritionalValue.get("Water"), equalTo(81.0));
    }

    @Test
    public void parse_tomato() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-tomato.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutritionalValue = parser.parse(document);

	assertThat(nutritionalValue, notNullValue());
	assertThat(nutritionalValue.get("Energy"), equalTo(74.0));
	assertThat(nutritionalValue.get("Calories"), equalTo(17.686));
	assertThat(nutritionalValue.get("Carbohydrates"), equalTo(3.9));
	assertThat(nutritionalValue.get("Sugars"), equalTo(2.6));
	assertThat(nutritionalValue.get("Dietary Fiber"), equalTo(1.2));
	assertThat(nutritionalValue.get("Fat"), equalTo(0.2));
	assertThat(nutritionalValue.get("Protein"), equalTo(0.9));
	assertThat(nutritionalValue.get("Vitamin A"), equalTo(0.000042));
	assertThat(nutritionalValue.get("Thiamine"), equalTo(0.000037));
	assertThat(nutritionalValue.get("Niacin"), equalTo(0.000594));
	assertThat(nutritionalValue.get("Vitamin B6"), equalTo(0.00008));
	assertThat(nutritionalValue.get("Vitamin C"), equalTo(0.014));
	assertThat(nutritionalValue.get("Vitamin E"), equalTo(0.00054));
	assertThat(nutritionalValue.get("Vitamin K"), equalTo(0.0000079));
	assertThat(nutritionalValue.get("Magnesium"), equalTo(0.011));
	assertThat(nutritionalValue.get("Manganese"), equalTo(0.000114));
	assertThat(nutritionalValue.get("Phosphorus"), equalTo(0.024));
	assertThat(nutritionalValue.get("Potassium"), equalTo(0.237));
	assertThat(nutritionalValue.get("Water"), equalTo(94.5));
	assertThat(nutritionalValue.get("Lycopene"), equalTo(0.002573));
    }

    @Test
    public void parse_kiwifruit() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-kiwifruit.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutritionalValue = parser.parse(document);

	assertThat(nutritionalValue, notNullValue());
	assertThat(nutritionalValue.get("Energy"), equalTo(262.0));
	assertThat(nutritionalValue.get("Calories"), equalTo(62.618));
	assertThat(nutritionalValue.get("Carbohydrates"), equalTo(15.8));
	assertThat(nutritionalValue.get("Sugars"), equalTo(12.3));
	assertThat(nutritionalValue.get("Dietary Fiber"), equalTo(1.4));
	assertThat(nutritionalValue.get("Fat"), equalTo(0.28));
	assertThat(nutritionalValue.get("Protein"), equalTo(1.02));
	assertThat(nutritionalValue.get("Vitamin A"), equalTo(0.000024));
	assertThat(nutritionalValue.get("Thiamine"), equalTo(0.0));
	assertThat(nutritionalValue.get("Riboflavin"), equalTo(0.000074));
	assertThat(nutritionalValue.get("Niacin"), equalTo(0.000231));
	assertThat(nutritionalValue.get("Pantothenic Acid"), equalTo(0.00012));
	assertThat(nutritionalValue.get("Vitamin B6"), equalTo(0.000079));
	assertThat(nutritionalValue.get("Folate"), equalTo(0.000031));
	assertThat(nutritionalValue.get("Vitamin B12"), equalTo(0.00000008));
	assertThat(nutritionalValue.get("Choline"), equalTo(0.0019));
	assertThat(nutritionalValue.get("Vitamin C"), equalTo(0.1613));
	assertThat(nutritionalValue.get("Vitamin E"), equalTo(0.0014));
	assertThat(nutritionalValue.get("Vitamin K"), equalTo(0.0000061));
	assertThat(nutritionalValue.get("Calcium"), equalTo(0.017));
	assertThat(nutritionalValue.get("Copper"), equalTo(0.000151));
	assertThat(nutritionalValue.get("Iron"), equalTo(0.00021));
	assertThat(nutritionalValue.get("Magnesium"), equalTo(0.012));
	assertThat(nutritionalValue.get("Manganese"), equalTo(0.000048));
	assertThat(nutritionalValue.get("Phosphorus"), equalTo(0.025));
	assertThat(nutritionalValue.get("Potassium"), equalTo(0.315));
	assertThat(nutritionalValue.get("Selenium"), equalTo(0.0000004));
	assertThat(nutritionalValue.get("Sodium"), equalTo(0.003));
	assertThat(nutritionalValue.get("Water"), equalTo(82.0));
    }

    @Test
    public void parse_asparagus() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("wikipedia-asparagus.htm"));
	Parser<Map<String, Double>> parser = getParser();
	Map<String, Double> nutritionalValue = parser.parse(document);

	assertThat(nutritionalValue, notNullValue());
	assertThat(nutritionalValue.get("Energy"), equalTo(85.0));
	assertThat(nutritionalValue.get("Calories"), equalTo(20.315));
	assertThat(nutritionalValue.get("Carbohydrates"), equalTo(4.0));
	assertThat(nutritionalValue.get("Sugars"), equalTo(1.88));
	assertThat(nutritionalValue.get("Dietary Fiber"), equalTo(2.1));
	assertThat(nutritionalValue.get("Fat"), equalTo(0.12));
	assertThat(nutritionalValue.get("Protein"), equalTo(2.2));
	assertThat(nutritionalValue.get("Vitamin A"), equalTo(0.000038));
	assertThat(nutritionalValue.get("Thiamine"), equalTo(0.000143));
	assertThat(nutritionalValue.get("Riboflavin"), equalTo(0.000141));
	assertThat(nutritionalValue.get("Niacin"), equalTo(0.000978));
	assertThat(nutritionalValue.get("Pantothenic Acid"), equalTo(0.000274));
	assertThat(nutritionalValue.get("Vitamin B6"), equalTo(0.000091));
	assertThat(nutritionalValue.get("Folate"), equalTo(0.000052));
	assertThat(nutritionalValue.get("Choline"), equalTo(0.016));
	assertThat(nutritionalValue.get("Vitamin C"), equalTo(0.0056));
	assertThat(nutritionalValue.get("Vitamin E"), equalTo(0.0011));
	assertThat(nutritionalValue.get("Vitamin K"), equalTo(0.0000416));
	assertThat(nutritionalValue.get("Calcium"), equalTo(0.024));
	assertThat(nutritionalValue.get("Iron"), equalTo(0.00214));
	assertThat(nutritionalValue.get("Magnesium"), equalTo(0.014));
	assertThat(nutritionalValue.get("Manganese"), equalTo(0.000158));
	assertThat(nutritionalValue.get("Phosphorus"), equalTo(0.052));
	assertThat(nutritionalValue.get("Potassium"), equalTo(0.202));
	assertThat(nutritionalValue.get("Sodium"), equalTo(0.002));
	assertThat(nutritionalValue.get("Zinc"), equalTo(0.00054));
    }
    
    private static Parser<Map<String, Double>> getParser() {
	return Classes.newInstance("com.kidscademy.apiservice.parser.WikipediaNutritionalValueParser");
    }
}
