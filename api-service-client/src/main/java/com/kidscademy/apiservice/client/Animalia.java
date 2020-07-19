package com.kidscademy.apiservice.client;

import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.kidscademy.apiservice.model.PhysicalTrait;

@Path("animalia")
public interface Animalia {
    @GET
    @Path("life/taxonomy/{name}")
    LinkedHashMap<String, String> getLifeTaxonomy(@PathParam("name") String name);

    @GET
    @Path("life/traits/{name}")
    List<PhysicalTrait> getPhysicalTraits(@PathParam("name") String name);
}
