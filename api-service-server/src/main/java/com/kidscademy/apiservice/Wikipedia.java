package com.kidscademy.apiservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kidscademy.apiservice.model.EdiblePlant;
import com.kidscademy.apiservice.model.LifeForm;
import com.kidscademy.apiservice.parser.Parser;
import com.kidscademy.apiservice.parser.ParserFactory;

import js.dom.DocumentBuilder;
import js.lang.BugError;
import js.util.Classes;
import js.util.Params;

@Path("wikipedia")
@Produces(MediaType.APPLICATION_JSON)
public class Wikipedia {
    private final ParserFactory factory;
    private final DocumentBuilder builder;

    public Wikipedia() {
	this.factory = new ParserFactory();
	this.builder = Classes.loadService(DocumentBuilder.class);
    }

    public Wikipedia(ParserFactory factory, DocumentBuilder builder) {
	this.factory = factory;
	this.builder = builder;
    }

    @GET
    @Path("life-form/{name}")
    public LifeForm getLifeForm(@PathParam("name") String name) {
	Params.notNullOrEmpty(name, "Life form name");
	URL url = URL("https://en.wikipedia.org/wiki/", name);
	Parser<LifeForm> parser = factory.getParser(url, "life-form");
	return parser.parse(builder.loadHTML(url));
    }

    @GET
    @Path("edible-plant/{name}")
    public EdiblePlant getEdiblePlant(@PathParam("name") String name) {
	Params.notNullOrEmpty(name, "Life form name");
	URL url = URL("https://en.wikipedia.org/wiki/", name);
	Parser<EdiblePlant> parser = factory.getParser(url, "edible-plant");
	return parser.parse(builder.loadHTML(url));
    }

    @GET
    @Path("taxonomy/{name}")
    public LinkedHashMap<String, String> getTaxonomy(@PathParam("name") String name) {
	Params.notNullOrEmpty(name, "Object name");
	URL url = URL("https://en.wikipedia.org/wiki/", name);
	Parser<LinkedHashMap<String, String>> parser = factory.getParser(url, "taxonomy");
	return parser.parse(builder.loadHTML(url));
    }

    /**
     * Get nutritional value for a particular item of food identified by its unique
     * name. Returned value is a measure of a well-balanced ratio of the essential
     * nutrients carbohydrates, fat, protein, minerals, and vitamins.
     * 
     * @param name
     *            unique name of requested item of food.
     * @return nutritional value.
     */
    @GET
    @Path("nutritional-value/{name}")
    public Map<String, Double> getNutritionalValue(@PathParam("name") String name) {
	Params.notNullOrEmpty(name, "Edible plant name");
	URL url = URL("https://en.wikipedia.org/wiki/", name);
	Parser<Map<String, Double>> parser = factory.getParser(url, "nutritional-value");
	return parser.parse(builder.loadHTML(url));
    }

    private static URL URL(String baseURL, String path) {
	try {
	    return new URL(baseURL + path);
	} catch (MalformedURLException e) {
	    throw new BugError("Bad hardcoded URL |%s|.", baseURL);
	}
    }
}
