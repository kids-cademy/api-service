package com.kidscademy.apiservice.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.kidscademy.apiservice.model.PhysicalTrait;
import com.kidscademy.apiservice.parser.Parser;

import js.dom.Document;
import js.dom.DocumentBuilder;
import js.util.Classes;

public class AnimaliaPhysicalTraitsParserTest {
    private static DocumentBuilder builder;

    @BeforeClass
    public static void beforeClass() {
	builder = Classes.loadService(DocumentBuilder.class);
    }

    @Test
    public void parse_african_lion() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("animalia-african-lion.htm"));
	Parser<List<PhysicalTrait>> parser = getParser();
	List<PhysicalTrait> traits = parser.parse(document);

	assertThat(traits, notNullValue());
	assertThat(traits, hasSize(5));

	assertThat(traits.get(0).getName(), equalTo("population.size"));
	assertThat(traits.get(0).getQuantity(), equalTo("SCALAR"));
	assertThat(traits.get(0).getValue(), equalTo(20000.0));
	assertThat(traits.get(0).getMaximum(), nullValue());

	assertThat(traits.get(1).getName(), equalTo("lifespan"));
	assertThat(traits.get(1).getQuantity(), equalTo("TIME"));
	assertThat(traits.get(1).getValue(), equalTo(378683424.0));
	assertThat(traits.get(1).getMaximum(), equalTo(788923800.0));

	assertThat(traits.get(2).getName(), equalTo("speed.full"));
	assertThat(traits.get(2).getQuantity(), equalTo("SPEED"));
	assertThat(traits.get(2).getValue(), equalTo(15.556));
	assertThat(traits.get(2).getMaximum(), nullValue());

	assertThat(traits.get(3).getName(), equalTo("weight"));
	assertThat(traits.get(3).getQuantity(), equalTo("MASS"));
	assertThat(traits.get(3).getValue(), equalTo(120.0));
	assertThat(traits.get(3).getMaximum(), equalTo(249.0));

	assertThat(traits.get(4).getName(), equalTo("length"));
	assertThat(traits.get(4).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(4).getValue(), equalTo(1.4));
	assertThat(traits.get(4).getMaximum(), equalTo(2.5));
    }

    @Test
    public void parse_african_caracal() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("animalia-caracal.htm"));
	Parser<List<PhysicalTrait>> parser = getParser();
	List<PhysicalTrait> traits = parser.parse(document);

	assertThat(traits, notNullValue());
	assertThat(traits, hasSize(5));

	assertThat(traits.get(0).getName(), equalTo("lifespan"));
	assertThat(traits.get(0).getQuantity(), equalTo("TIME"));
	assertThat(traits.get(0).getValue(), equalTo(378683424.0));
	assertThat(traits.get(0).getMaximum(), equalTo(536468184.0));

	assertThat(traits.get(1).getName(), equalTo("speed.full"));
	assertThat(traits.get(1).getQuantity(), equalTo("SPEED"));
	assertThat(traits.get(1).getValue(), equalTo(22.222));
	assertThat(traits.get(1).getMaximum(), nullValue());

	assertThat(traits.get(2).getName(), equalTo("weight"));
	assertThat(traits.get(2).getQuantity(), equalTo("MASS"));
	assertThat(traits.get(2).getValue(), equalTo(8.0));
	assertThat(traits.get(2).getMaximum(), equalTo(18.0));

	assertThat(traits.get(3).getName(), equalTo("height"));
	assertThat(traits.get(3).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(3).getValue(), equalTo(0.4));
	assertThat(traits.get(3).getMaximum(), equalTo(0.5));

	assertThat(traits.get(4).getName(), equalTo("length"));
	assertThat(traits.get(4).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(4).getValue(), equalTo(0.73));
	assertThat(traits.get(4).getMaximum(), equalTo(0.78));
    }

    @Test
    public void parse_tiger() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("animalia-tiger.htm"));
	Parser<List<PhysicalTrait>> parser = getParser();
	List<PhysicalTrait> traits = parser.parse(document);

	assertThat(traits, notNullValue());
	assertThat(traits, hasSize(4));

	assertThat(traits.get(0).getName(), equalTo("lifespan"));
	assertThat(traits.get(0).getQuantity(), equalTo("TIME"));
	assertThat(traits.get(0).getValue(), equalTo(315569520.0));
	assertThat(traits.get(0).getMaximum(), equalTo(473354280.0));

	assertThat(traits.get(1).getName(), equalTo("speed.full"));
	assertThat(traits.get(1).getQuantity(), equalTo("SPEED"));
	assertThat(traits.get(1).getValue(), equalTo(26.667));
	assertThat(traits.get(1).getMaximum(), nullValue());

	assertThat(traits.get(2).getName(), equalTo("weight"));
	assertThat(traits.get(2).getQuantity(), equalTo("MASS"));
	assertThat(traits.get(2).getValue(), equalTo(65.0));
	assertThat(traits.get(2).getMaximum(), equalTo(306.0));

	assertThat(traits.get(3).getName(), equalTo("length"));
	assertThat(traits.get(3).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(3).getValue(), equalTo(2.0));
	assertThat(traits.get(3).getMaximum(), equalTo(3.9));
    }

    @Test
    public void parse_wildcat() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("animalia-wildcat.htm"));
	Parser<List<PhysicalTrait>> parser = getParser();
	List<PhysicalTrait> traits = parser.parse(document);

	assertThat(traits, notNullValue());
	assertThat(traits, hasSize(5));

	assertThat(traits.get(0).getName(), equalTo("lifespan"));
	assertThat(traits.get(0).getQuantity(), equalTo("TIME"));
	assertThat(traits.get(0).getValue(), equalTo(473354280.0));
	assertThat(traits.get(0).getMaximum(), nullValue());

	assertThat(traits.get(1).getName(), equalTo("speed.full"));
	assertThat(traits.get(1).getQuantity(), equalTo("SPEED"));
	assertThat(traits.get(1).getValue(), equalTo(13.411));
	assertThat(traits.get(1).getMaximum(), nullValue());

	assertThat(traits.get(2).getName(), equalTo("weight"));
	assertThat(traits.get(2).getQuantity(), equalTo("MASS"));
	assertThat(traits.get(2).getValue(), equalTo(5.0));
	assertThat(traits.get(2).getMaximum(), equalTo(8.0));

	assertThat(traits.get(3).getName(), equalTo("height"));
	assertThat(traits.get(3).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(3).getValue(), equalTo(0.2));
	assertThat(traits.get(3).getMaximum(), nullValue());

	assertThat(traits.get(4).getName(), equalTo("length"));
	assertThat(traits.get(4).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(4).getValue(), equalTo(0.43));
	assertThat(traits.get(4).getMaximum(), equalTo(0.91));
    }

    @Test
    public void parse_andean_cat() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("animalia-andean-cat.htm"));
	Parser<List<PhysicalTrait>> parser = getParser();
	List<PhysicalTrait> traits = parser.parse(document);

	assertThat(traits, notNullValue());
	assertThat(traits, hasSize(5));

	assertThat(traits.get(0).getName(), equalTo("population.size"));
	assertThat(traits.get(0).getQuantity(), equalTo("SCALAR"));
	assertThat(traits.get(0).getValue(), equalTo(2500.0));
	assertThat(traits.get(0).getMaximum(), nullValue());

	assertThat(traits.get(1).getName(), equalTo("lifespan"));
	assertThat(traits.get(1).getQuantity(), equalTo("TIME"));
	assertThat(traits.get(1).getValue(), equalTo(504911232.0));
	assertThat(traits.get(1).getMaximum(), nullValue());

	assertThat(traits.get(2).getName(), equalTo("weight"));
	assertThat(traits.get(2).getQuantity(), equalTo("MASS"));
	assertThat(traits.get(2).getValue(), equalTo(5.5));
	assertThat(traits.get(2).getMaximum(), nullValue());

	assertThat(traits.get(3).getName(), equalTo("height"));
	assertThat(traits.get(3).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(3).getValue(), equalTo(0.36));
	assertThat(traits.get(3).getMaximum(), nullValue());

	assertThat(traits.get(4).getName(), equalTo("length"));
	assertThat(traits.get(4).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(4).getValue(), equalTo(0.57));
	assertThat(traits.get(4).getMaximum(), equalTo(0.85));
    }

    @Test
    public void parse_bay_cat() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("animalia-bay-cat.htm"));
	Parser<List<PhysicalTrait>> parser = getParser();
	List<PhysicalTrait> traits = parser.parse(document);

	assertThat(traits, notNullValue());
	assertThat(traits, hasSize(4));

	assertThat(traits.get(0).getName(), equalTo("population.size"));
	assertThat(traits.get(0).getQuantity(), equalTo("SCALAR"));
	assertThat(traits.get(0).getValue(), equalTo(2500.0));
	assertThat(traits.get(0).getMaximum(), nullValue());

	assertThat(traits.get(1).getName(), equalTo("lifespan"));
	assertThat(traits.get(1).getQuantity(), equalTo("TIME"));
	assertThat(traits.get(1).getValue(), equalTo(315569520.0));
	assertThat(traits.get(1).getMaximum(), equalTo(536468184.0));

	assertThat(traits.get(2).getName(), equalTo("weight"));
	assertThat(traits.get(2).getQuantity(), equalTo("MASS"));
	assertThat(traits.get(2).getValue(), equalTo(3.0));
	assertThat(traits.get(2).getMaximum(), equalTo(4.0));

	assertThat(traits.get(3).getName(), equalTo("length"));
	assertThat(traits.get(3).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(3).getValue(), equalTo(0.495));
	assertThat(traits.get(3).getMaximum(), equalTo(0.67));
    }

    @Test
    public void parse_eurasian_lynx() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("animalia-eurasian-lynx.htm"));
	Parser<List<PhysicalTrait>> parser = getParser();
	List<PhysicalTrait> traits = parser.parse(document);

	assertThat(traits, notNullValue());
	assertThat(traits, hasSize(6));

	assertThat(traits.get(0).getName(), equalTo("population.size"));
	assertThat(traits.get(0).getQuantity(), equalTo("SCALAR"));
	assertThat(traits.get(0).getValue(), equalTo(70000.0));
	assertThat(traits.get(0).getMaximum(), equalTo(80000.0));

	assertThat(traits.get(1).getName(), equalTo("lifespan"));
	assertThat(traits.get(1).getQuantity(), equalTo("TIME"));
	assertThat(traits.get(1).getValue(), equalTo(410240376.0));
	assertThat(traits.get(1).getMaximum(), equalTo(788923800.0));

	assertThat(traits.get(2).getName(), equalTo("speed.full"));
	assertThat(traits.get(2).getQuantity(), equalTo("SPEED"));
	assertThat(traits.get(2).getValue(), equalTo(22.222));
	assertThat(traits.get(2).getMaximum(), nullValue());

	assertThat(traits.get(3).getName(), equalTo("weight"));
	assertThat(traits.get(3).getQuantity(), equalTo("MASS"));
	assertThat(traits.get(3).getValue(), equalTo(8.0));
	assertThat(traits.get(3).getMaximum(), equalTo(30.0));

	assertThat(traits.get(4).getName(), equalTo("height"));
	assertThat(traits.get(4).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(4).getValue(), equalTo(0.60));
	assertThat(traits.get(4).getMaximum(), equalTo(0.75));

	assertThat(traits.get(5).getName(), equalTo("length"));
	assertThat(traits.get(5).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(5).getValue(), equalTo(0.80));
	assertThat(traits.get(5).getMaximum(), equalTo(1.30));
    }

    @Test
    public void parse_marbled_cat() {
	Document document = builder.loadHTML(Classes.getResourceAsStream("animalia-marbled-cat.htm"));
	Parser<List<PhysicalTrait>> parser = getParser();
	List<PhysicalTrait> traits = parser.parse(document);

	assertThat(traits, notNullValue());
	assertThat(traits, hasSize(4));

	assertThat(traits.get(0).getName(), equalTo("population.size"));
	assertThat(traits.get(0).getQuantity(), equalTo("SCALAR"));
	assertThat(traits.get(0).getValue(), equalTo(10000.0));
	assertThat(traits.get(0).getMaximum(), nullValue());

	assertThat(traits.get(1).getName(), equalTo("lifespan"));
	assertThat(traits.get(1).getQuantity(), equalTo("TIME"));
	assertThat(traits.get(1).getValue(), equalTo(378683424.0));
	assertThat(traits.get(1).getMaximum(), nullValue());

	assertThat(traits.get(2).getName(), equalTo("weight"));
	assertThat(traits.get(2).getQuantity(), equalTo("MASS"));
	assertThat(traits.get(2).getValue(), equalTo(2.0));
	assertThat(traits.get(2).getMaximum(), equalTo(5.0));

	assertThat(traits.get(3).getName(), equalTo("length"));
	assertThat(traits.get(3).getQuantity(), equalTo("LENGTH"));
	assertThat(traits.get(3).getValue(), equalTo(0.45));
	assertThat(traits.get(3).getMaximum(), equalTo(0.62));
    }

    private static Parser<List<PhysicalTrait>> getParser() {
	return Classes.newInstance("com.kidscademy.apiservice.parser.AnimaliaPhysicalTraitsParser");
    }
}
