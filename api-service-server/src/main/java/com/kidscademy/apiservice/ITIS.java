package com.kidscademy.apiservice;

import java.util.LinkedHashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import js.util.Params;

@Path("itis")
@Produces(MediaType.APPLICATION_JSON)
public class ITIS {
    @GET
    @Path("taxonomy/{name}")
    public LinkedHashMap<String, String> getTaxonomy(@PathParam("name") String name) {
	Params.notNullOrEmpty(name, "Object name");
	// TODO Auto-generated method stub
	return null;
    }
}
