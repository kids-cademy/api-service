package com.kidscademy.apiservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kidscademy.apiservice.model.PhysicalTrait;
import com.kidscademy.apiservice.parser.Parser;
import com.kidscademy.apiservice.parser.ParserFactory;

import js.dom.DocumentBuilder;
import js.lang.BugError;
import js.util.Classes;
import js.util.Params;

@Path("animalia")
@Produces(MediaType.APPLICATION_JSON)
public class Animalia {
    private final ParserFactory factory;
    private final DocumentBuilder builder;

    public Animalia() {
	this.factory = new ParserFactory();
	this.builder = Classes.loadService(DocumentBuilder.class);
    }

    public Animalia(ParserFactory factory, DocumentBuilder builder) {
	this.factory = factory;
	this.builder = builder;
    }

    @GET
    @Path("life/taxonomy/{name}")
    public LinkedHashMap<String, String> getLifeTaxonomy(@PathParam("name") String name) {
	Params.notNullOrEmpty(name, "Object name");
	URL url = URL("http://animalia.bio/", name);
	Parser<LinkedHashMap<String, String>> parser = factory.getParser(url, "taxonomy");
	return parser.parse(builder.loadHTML(url));
    }

    @GET
    @Path("life/traits/{name}")
    public List<PhysicalTrait> getPhysicalTraits(@PathParam("name") String name) {
	Params.notNullOrEmpty(name, "Object name");
	URL url = URL("http://animalia.bio/", name);
	Parser<List<PhysicalTrait>> parser = factory.getParser(url, "traits");
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
