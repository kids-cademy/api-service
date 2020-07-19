package com.kidscademy.apiservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kidscademy.apiservice.model.WordDefinition;
import com.kidscademy.apiservice.parser.Parser;
import com.kidscademy.apiservice.parser.ParserFactory;

import js.dom.DocumentBuilder;
import js.lang.BugError;
import js.util.Classes;
import js.util.Params;

@Path("free-dictionary")
@Produces(MediaType.APPLICATION_JSON)
public class TheFreeDictionary {
    private final ParserFactory factory;
    private final DocumentBuilder builder;

    public TheFreeDictionary() {
	this.factory = new ParserFactory();
	this.builder = Classes.loadService(DocumentBuilder.class);
    }

    public TheFreeDictionary(ParserFactory factory, DocumentBuilder builder) {
	this.factory = factory;
	this.builder = builder;
    }

    @GET
    @Path("{word}")
    public List<WordDefinition> getDefinitions(@PathParam("word") String word) {
	Params.notNullOrEmpty(word, "Word");
	URL url = URL("https://www.thefreedictionary.com/", word);
	Parser<List<WordDefinition>> parser = factory.getParser(url, "definition");
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
