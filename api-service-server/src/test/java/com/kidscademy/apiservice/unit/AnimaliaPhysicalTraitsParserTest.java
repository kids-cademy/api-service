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
    public void parse_african_tiger() {
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

    private static Parser<List<PhysicalTrait>> getParser() {
	return Classes.newInstance("com.kidscademy.apiservice.parser.AnimaliaPhysicalTraitsParser");
    }
}